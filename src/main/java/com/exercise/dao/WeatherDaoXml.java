package com.exercise.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.log4j.Logger;


public class WeatherDaoXml implements WeatherDaoI {
	
	final static Logger LOGGER = Logger.getLogger(WeatherDaoXml.class);
	
	private String url;
	private String appId;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getWeatherForCity(String city) throws Exception {
		
		city = city.replaceAll(" ", "%20");

		StringBuilder weatherDetails = new StringBuilder();
		
		try {
			URL location = new URL(url+city+"&APPID="+appId+"&mode=XML");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(location.openStream()));
			String weatherLine = "";
			while (null != (weatherLine = reader.readLine())) {
				weatherDetails.append(weatherLine);
			}
			
		} catch (Exception e) {
			LOGGER.error("There was a problem in getting the weather details from OpenWeatherMap.org");
			LOGGER.error(e.getMessage());
			throw e;
		}

		return weatherDetails.toString();
	}

}
