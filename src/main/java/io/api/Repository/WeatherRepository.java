package io.api.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.annotations.Parameter;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import io.api.Entity.Weather;

public interface WeatherRepository extends Repository<Weather, Long>{
	
	@PersistenceContext
	EntityManager eManager = null;
	
	public List<Weather> findAll();
	
	public Weather save(Weather weather);
	
	@Query("SELECT DISTINCT w.city from Weather w")
	public List<Weather> findDistinctCity();
	
	public List<Weather> findTop1ByCityOrderByIdDesc(@Param("wCity") String city);
	
	
	
}
