package com.exercise.util;

public class Temperature {
	
	public static Double kelvinToCelsius(final Double kelvin) {
		return kelvin - 273.75;
	}
	
	public static Double celsiusToFahrenheity(final Double celsius) {
		return ( celsius * 9.0 / 5.0 ) + 32.0;
	}

}
