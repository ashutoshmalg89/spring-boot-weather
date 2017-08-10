package io.api.Repository;

import java.util.List;

import io.api.Entity.Weather;

public interface WeatherRepositoryComplex {
	
	public double getWeatherForCityByProperty(String city, String prop);
	public List<Weather> findAvgHourlyWeatherforCity(String city);
	public List<Weather> findAvgDaillyWeatherforCity(String city);
}
