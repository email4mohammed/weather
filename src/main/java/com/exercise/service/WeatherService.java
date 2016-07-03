package com.exercise.service;

import com.exercise.dao.WeatherDaoXml;
import com.exercise.entity.Weather;
import com.exercise.util.Temperature;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


@Service
public class WeatherService implements WeatherServiceI {
	
	static final Logger LOGGER = Logger.getLogger(WeatherService.class);
	
	@Autowired
	private WeatherDaoXml weatherDao;
	
	@Autowired
	private HashMap<String, String> cityTimezones;
	
	public WeatherDaoXml getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(final WeatherDaoXml weatherDao) {
		this.weatherDao = weatherDao;
	}
	
	public HashMap<String, String> getCityTimezones() {
		return cityTimezones;
	}

	public void setCityTimezones(final HashMap<String, String> cityTimezones) {
		this.cityTimezones = cityTimezones;
	}

	public Weather getCurrentWeather(final String city) throws Exception {
	
		final DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    final InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(weatherDao.getWeatherForCity(city)));
	    final Document document = db.parse(is);
		

		return xmlToEntity(document);
		
	}
	
	private Weather xmlToEntity(final Document document) throws Exception {
		final Weather weather = new Weather();
		
		final Node nodeWeather = document.getElementsByTagName("weather").item(0);
		final String description = nodeWeather.getAttributes().getNamedItem("value").getTextContent();
		
		final Node nodeCity = document.getElementsByTagName("city").item(0);
		final String city = nodeCity.getAttributes().getNamedItem("name").getTextContent();
		
		//temperature
		final Node nodeTemperature = document.getElementsByTagName("temperature").item(0);
		final String kelvin = nodeTemperature.getAttributes().getNamedItem("value").getTextContent();
		final DecimalFormat df = new DecimalFormat("####0.00");
		final Double celsius = Temperature.kelvinToCelsius(new Double(kelvin));
		weather.setTempC(df.format(celsius));
		final Double fahrenheit = Temperature.celsiusToFahrenheity(new Double(celsius));


		//timings
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
		final Node nodeSun = document.getElementsByTagName("sun").item(0);
		
		final String sunrise = nodeSun.getAttributes().getNamedItem("rise").getTextContent().concat("Z");
		Instant instant = Instant.parse(sunrise);
		ZonedDateTime zonedTime = instant.atZone(ZoneId.of(cityTimezones.get(city)));
		weather.setTimeSunrise(zonedTime.format(formatter));
				
		final String sunset = nodeSun.getAttributes().getNamedItem("set").getTextContent().concat("Z");
		instant = Instant.parse(sunset);
		zonedTime = instant.atZone(ZoneId.of(cityTimezones.get(city)));
		weather.setTimeSunset(zonedTime.format(formatter));
		
		weather.setDescription(description);
		weather.setCity(city);
		weather.setTempF(df.format(fahrenheit));
		
		
		//date
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		weather.setDate(LocalDateTime.now().format(formatter));
		
		return weather;
	}
	

}
