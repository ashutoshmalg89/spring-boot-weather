package io.api.RepositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import io.api.Entity.Weather;
import io.api.Repository.WeatherRepositoryComplex;

@Repository
public class WeatherRepositoryImpl implements WeatherRepositoryComplex{
	
	@PersistenceContext
	EntityManager em;
	
	Logger log;
	@Override
	public double getWeatherForCityByProperty(String city, String prop) {
		//TypedQuery<String> query = em.createNamedQuery("Weather.weatherForCityByProp", String.class);
		TypedQuery<Double> query = em.createQuery("SELECT w."+prop+" from Weather w where w.city= :wCity ORDER BY w.timestamp DESC", Double.class);
		query.setParameter("wCity", city);
		query.setMaxResults(1);
		List<Double> result = query.getResultList();
		
		return result.get(0);
	}

	@Override
	public List<Weather> findAvgHourlyWeatherforCity(String city) {
		
		TypedQuery<Weather> query = em.createNamedQuery("Weather.avgHourlyWeather", Weather.class);
		query.setParameter("wCity", city);
		
		return query.getResultList();
	}

	@Override
	public List<Weather> findAvgDaillyWeatherforCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.avgDailyWeather", Weather.class);
		query.setParameter("wCity", city);
		
		return query.getResultList();
	}
	

}
