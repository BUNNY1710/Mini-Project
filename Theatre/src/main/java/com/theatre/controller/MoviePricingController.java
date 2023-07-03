package com.theatre.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.dto.MoviesDetailsDTO;
import com.theatre.dto.TicketDTO;
import com.theatre.model.Movie;
import com.theatre.model.MoviePricing;
import com.theatre.repository.MoviePricingRepository;
import com.theatre.repository.MovieRepository;
import com.theatre.repository.TheatreRepository;

@RestController
@RequestMapping("/pricing-api/")
public class MoviePricingController {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	TheatreRepository theatreRepository;
	
	@Autowired
	MoviePricingRepository moviePricingRepository;
	
	@GetMapping("/price/{theatreId}/{movieId}")
	public MoviePricing getPricingInfo(@PathVariable("theatreId") int theatreId, @PathVariable("movieId") int movieId)
	{
		MoviePricing moviePricing = moviePricingRepository.findMovieDetails(theatreId, movieId);
		return moviePricing;
	}
	
	
	
	
	
	
	//Need this method when user books ticket
	@PutMapping("/bookMovie")
	public boolean bookMovie(@RequestBody TicketDTO ticketDTO) {

		MoviePricing pricing = moviePricingRepository.findMovieDetails(ticketDTO.getTheatreId(), ticketDTO.getMovieId());
		
		if(pricing.getQuantity()<ticketDTO.getQuantity()) {
			return false;
		}
		pricing.setQuantity(pricing.getQuantity()-ticketDTO.getQuantity());
		moviePricingRepository.save(pricing);
		return true;

	}
	
	
	// Need for UI
	@GetMapping("/checkAvailability/{movieId}")
	public List<MoviesDetailsDTO> checkAvailability(@PathVariable("movieId") int movieId){
		
		Movie movie = movieRepository.findById(movieId).get();
		Set<MoviePricing> pricing = movie.getPricing();
		
		List<MoviesDetailsDTO> movieInfo = new ArrayList<>();
		
		for(MoviePricing p : pricing) {
			MoviesDetailsDTO movieDetails = new MoviesDetailsDTO();

			movieDetails.setDate(p.getDate());
			movieDetails.setPrice(p.getPrice());
			movieDetails.setQuantity(p.getQuantity());
			
			movieDetails.setTheatreName(p.getTheatre().getName());

			
			movieDetails.setCity(p.getMovie().getCity());
			movieDetails.setGenres(p.getMovie().getGenres());
			movieDetails.setLanguage(p.getMovie().getLanguage());
			movieDetails.setMovieId(p.getMovie().getMovieId());
			movieDetails.setMovieName(p.getMovie().getMovieName());
			
			movieInfo.add(movieDetails);
		}
		
		
		return movieInfo;
	}
	
}
