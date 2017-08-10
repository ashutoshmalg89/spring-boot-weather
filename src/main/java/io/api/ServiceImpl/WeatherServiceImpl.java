package io.api.ServiceImpl;

import java.util.List;

import javax.persistence.Column;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.core.result.Field;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import io.api.Entity.Weather;
import io.api.Exception.BadRequestException;
import io.api.Exception.NotFoundException;
import io.api.Repository.WeatherRepository;
import io.api.Repository.WeatherRepositoryComplex;
import io.api.Service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	public enum wProperties{
		TEMPERATURE("temperature"), 
		HUMIDITY("humidity"), 
		PRESSURE("pressure"), 
		DEGREE("degree"),
		SPEED("speed");
		
		private String prop;
		
		private wProperties(String prop) {
			this.prop = prop;
		}
	}
	
	private WeatherRepository weatherRepo;
	private WeatherRepositoryComplex weatherRepositoryComplex;
	
	public WeatherServiceImpl(WeatherRepository weatherRepo, WeatherRepositoryComplex weatherRepositoryComplex) {
		this.weatherRepo = weatherRepo;
		this.weatherRepositoryComplex = weatherRepositoryComplex;
	}


	@Override
	@Transactional
	public Weather create(Weather weather) {
		
		return weatherRepo.save(weather);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Weather> findAll() {
		// TODO Auto-generated method stub
		return weatherRepo.findAll();
	}

	
	@Override
	public List<Weather> getCities() {
		List<Weather> result = weatherRepo.findDistinctCity();
		if(result==null) {
			throw new NotFoundException("No Record Exists");
		}
		return result;
	}

	
	@Override
	public List<Weather> getWeatherByCity(String city) {
		// TODO Auto-generated method stub
		List<Weather> result = weatherRepo.findTop1ByCityOrderByIdDesc(city);
		if(result==null) {
			throw new BadRequestException("No Record Exist for City :"+city);
		}
		return result; 
	}


	@Override
	public double getWeatherForCityByProperty(String city, String prop) {
		double result = weatherRepositoryComplex.getWeatherForCityByProperty(city, prop);
		if(result==0) {
			throw new BadRequestException("No record for "+prop+" exist for city :"+city);
		} 
		
		return result;
	}

	
	@Override
	public List<Weather> findAvgHourlyWeatherforCity(String city) {
		return weatherRepositoryComplex.findAvgHourlyWeatherforCity(city);
	}


	@Override
	public List<Weather> findAvgDaillyWeatherforCity(String city) {
		// TODO Auto-generated method stub
		return weatherRepositoryComplex.findAvgDaillyWeatherforCity(city);
	}

}
