package com.theatre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.theatre.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{

	List<Movie> findByCity(String city);

}
