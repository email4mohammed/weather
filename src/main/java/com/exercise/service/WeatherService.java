package com.exercise.service;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.exercise.dao.WeatherDaoXml;
import com.exercise.entity.Weather;
import com.exercise.util.Temperature;

@Service
public class WeatherService implements WeatherServiceI {
	
	final static Logger LOGGER = Logger.getLogger(WeatherService.class);
	
	@Autowired
	private WeatherDaoXml weatherDao;
	
	@Autowired
	private HashMap<String, String> cityTimezones;
	
	public WeatherDaoXml getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDaoXml weatherDao) {
		this.weatherDao = weatherDao;
	}
	
	public HashMap<String, String> getCityTimezones() {
		return cityTimezones;
	}

	public void setCityTimezones(HashMap<String, String> cityTimezones) {
		this.cityTimezones = cityTimezones;
	}

	public Weather getCurrentWeather(String city) throws Exception {
	
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(weatherDao.getWeatherForCity(city)));
	    Document document = db.parse(is);
		

		return xmlToEntity(document);
		
	}
	
	private Weather xmlToEntity(Document document) throws Exception {
		Weather weather = new Weather();
		
		Node nodeWeather = document.getElementsByTagName("weather").item(0);
		String description = nodeWeather.getAttributes().getNamedItem("value").getTextContent();
		
		Node nodeCity = document.getElementsByTagName("city").item(0);
		String city = nodeCity.getAttributes().getNamedItem("name").getTextContent();
		
		//temperature
		Node nodeTemperature = document.getElementsByTagName("temperature").item(0);
		String kelvin = nodeTemperature.getAttributes().getNamedItem("value").getTextContent();
		DecimalFormat df = new DecimalFormat("####0.00");
		Double celsius = Temperature.kelvinToCelsius(new Double(kelvin));
		weather.setTempC(df.format(celsius));
		Double fahrenheit = Temperature.celsiusToFahrenheity(new Double(celsius));


		//timings
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
		Node nodeSun = document.getElementsByTagName("sun").item(0);
		
		String sunrise = nodeSun.getAttributes().getNamedItem("rise").getTextContent().concat("Z");
		Instant instant = Instant.parse(sunrise);
		ZonedDateTime zonedTime = instant.atZone(ZoneId.of(cityTimezones.get(city)));
		weather.setTimeSunrise(zonedTime.format(formatter));
				
		String sunset = nodeSun.getAttributes().getNamedItem("set").getTextContent().concat("Z");
		instant = Instant.parse(sunset);
		zonedTime = instant.atZone(ZoneId.of(cityTimezones.get(city)));
		weather.setTimeSunset(zonedTime.format(formatter));
		
		weather.setDescription(description);
		weather.setCity(city);
		weather.setTempF(df.format(fahrenheit));
		return weather;
	}
	

}
