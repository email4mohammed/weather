package com.exercise.controller;

import com.exercise.entity.Weather;
import com.exercise.service.WeatherService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@ControllerAdvice
public class WeatherController {

	static final Logger LOGGER = Logger.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private ArrayList<String> listOfCities = new ArrayList<String>();
	


	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(final WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	public ArrayList<String> getListOfCities() {
		return listOfCities;
	}

	public void setListOfCities(final ArrayList<String> listOfCities) {
		this.listOfCities = listOfCities;
	}	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView weather() {
		
		final ModelAndView mav = new ModelAndView("searchForm", "command", new Weather());
		mav.addObject("listOfCities", listOfCities);
		
		return mav;
	}

	@RequestMapping(value = "/getWeather", method = RequestMethod.POST)
	public String getWeather(@ModelAttribute("SpringWeb") Weather weather,
			ModelMap model) throws Exception {

		final Weather weatherResponse = weatherService.getCurrentWeather(weather.getCity());

		model.addAttribute("description", weatherResponse.getDescription());
		model.addAttribute("city", weatherResponse.getCity());
		model.addAttribute("sunrise", weatherResponse.getTimeSunrise());
		model.addAttribute("sunset", weatherResponse.getTimeSunset());
		model.addAttribute("tempC", weatherResponse.getTempC());
		model.addAttribute("tempF", weatherResponse.getTempF());
		model.addAttribute("date", weatherResponse.getDate());

		return "response";
	}
	
	@ExceptionHandler(Exception.class)
	public String exception(final Exception e) {
		LOGGER.error("Error",e);
		return "error";
	}



}