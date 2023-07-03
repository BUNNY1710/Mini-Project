package com.theatre.uiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.theatre.dto.TicketDTO;
import com.theatre.model.MoviePricing;
import com.theatre.repository.MoviePricingRepository;
import com.theatre.repository.MovieRepository;
import com.theatre.repository.TheatreRepository;

@Controller
public class MoviePricingUIController {
	
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
	
}
