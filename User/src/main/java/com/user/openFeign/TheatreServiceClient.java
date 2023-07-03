package com.user.openFeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.DTO.MovieDTO;
import com.user.DTO.MoviePricingDTO;
import com.user.DTO.MoviesDetailsDTO;
import com.user.DTO.TicketDTO;

@FeignClient(name = "THEATRE-SERVICE", url = "localhost:8080")
public interface TheatreServiceClient {
	
	@GetMapping("/movie-api/allMovies")
	List<MovieDTO> getAllMovies();
	
	@PutMapping("/pricing-api/bookMovie")
	boolean bookTicket(@RequestBody TicketDTO ticketDTO);
	
	@GetMapping("/movie-api/movie/{movieId}")
	MovieDTO getMovieDetails(@PathVariable("movieId") int movieId);
	
	@GetMapping("/theatre-api/getTheatreName/{theatreId}")
	String getTheatreName(@PathVariable("theatreId") int theatreId);
	
	@GetMapping("/theatre-api/getTheatreId/{theatreName}")
	int getTheatreId (@PathVariable("theatreName") String theatreName);
	
	@GetMapping("/pricing-api/price/{theatreId}/{movieId}")
	MoviePricingDTO getMoviePricing(@PathVariable("theatreId") int theatreId, @PathVariable("movieId") int movieId);

	@GetMapping("/pricing-api/checkAvailability/{movieId}")
	List<MoviesDetailsDTO> checkAvailability(@PathVariable("movieId") int id);
	
}
