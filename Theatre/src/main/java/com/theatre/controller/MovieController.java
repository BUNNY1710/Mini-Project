package com.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.dto.MovieDTO;
import com.theatre.dto.MoviePricingDTO;
import com.theatre.model.Movie;
import com.theatre.model.Theatre;
import com.theatre.service.MovieService;

@RestController	
@RequestMapping("/movie-api/")
public class MovieController
{
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/addMovie")
	private Movie addMovie(@RequestBody Movie movie)
	{
		return movieService.addMovie(movie);
	}
	
	@GetMapping("/movie/{id}")
	private ResponseEntity<Movie> getMovie(@PathVariable("id") int id)
	{
		return movieService.getMovie(id);
	}
	
	@PutMapping("/editMovie/{id}")
	private Movie editMovie(
			@PathVariable("id") int id,
			@RequestBody MovieDTO movieDTO
			)
	{
		return movieService.editMovie(movieDTO, id);
	}
	
	@DeleteMapping("/deleteMovie/{id}")
	private String deleteMovie(@PathVariable("id") int id)
	{
		movieService.deleteMovie(id);
		return "Movie deleted successfully!!";
	}
	
	@GetMapping("/allMovies")
	private List<Movie> getAllMovie()
	{
		return movieService.getAllMovie();
	}
	
	@GetMapping("/movies/{city}")
	private List<Movie> getMoviebyCity(@PathVariable("city") String city)
	{
		return movieService.getMovieByCity(city);
	}
	
	@PutMapping("/{theatreId}/movie/{movieId}")
	public Theatre addMovietoTheatre(
			@PathVariable("theatreId") int theatreId,
			@PathVariable("movieId") int movieId,
			@RequestBody MoviePricingDTO movieDTO
			)
	{
		return movieService.addMovietoTheatre(theatreId, movieId, movieDTO);
	}
	
}
