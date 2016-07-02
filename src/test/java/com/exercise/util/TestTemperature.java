package com.exercise.util;

import org.junit.Assert;
import org.junit.Test;

public class TestTemperature {

	@Test
	public void testCelsiusToFahrenheit() {

		Assert.assertEquals(Temperature.celsiusToFahrenheity(new Double(10)), new Double(50));

	}

	@Test
	public void testKelvinToCelsius() {

		Assert.assertEquals(Temperature.kelvinToCelsius(new Double(283.75)), new Double(10));

	}

}
