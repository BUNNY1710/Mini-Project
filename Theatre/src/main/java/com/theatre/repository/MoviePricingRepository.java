package com.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.theatre.model.MoviePricing;

public interface MoviePricingRepository extends JpaRepository<MoviePricing, Integer>
{
	@Query(value = "SELECT * from movie_pricing WHERE theatre_id = :theatreId AND movie_id = :movieId", nativeQuery = true)
	public MoviePricing findMovieDetails(@Param("theatreId") int theatreId, @Param("movieId") int movieId);	
}
