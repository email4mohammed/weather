package com.exercise.service;

import com.exercise.entity.Weather;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-weather-servlet.xml"})
public class TestSpringWeatherService {
	
	@Autowired
	private WeatherService service; 
	
	
	@Test
	public void testValidCity() throws Exception {

		final Weather weather = service.getCurrentWeather("London");
		
		Assert.assertEquals("London", weather.getCity());
		

	}
	
	@Test(expected=NullPointerException.class)
	public void testInvalidCity() throws Exception{
		
			service.getCurrentWeather("Timbuctu");

	}
	

}
