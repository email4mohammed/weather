package com.exercise.service;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exercise.dao.WeatherDaoXml;
import com.exercise.entity.Weather;

public class TestWeatherService {
	
	private WeatherService service; 
	
	@Before
	public void setUp() {

		HashMap<String, String> cityTimezones = new HashMap<String, String>();
		
		cityTimezones.put("London","Europe/London");
		cityTimezones.put("Hong Kong","Asia/Hong_Kong");
		
		WeatherDaoXml dao = new WeatherDaoXml();
		dao.setUrl("http://api.openweathermap.org/data/2.5/weather?q=");
		dao.setAppId("840e8c16f7f491627561e53162a06215");
		
		service = new WeatherService();
		service.setWeatherDao(dao);
		service.setCityTimezones(cityTimezones);
		
	}
	
	
	@Test
	public void testValidCity() throws Exception {

		Weather weather = service.getCurrentWeather("London");
		
		Assert.assertEquals("London", weather.getCity());
		

	}
	
	@Test(expected=NullPointerException.class)
	public void testInvalidCity() throws Exception{
		
			Weather weather = service.getCurrentWeather("Timbuctu");

	}
	

}
