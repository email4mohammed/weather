package com.exercise.service;

import com.exercise.entity.Weather;

public interface WeatherServiceI {

		Weather getCurrentWeather(String city) throws Exception;
}
