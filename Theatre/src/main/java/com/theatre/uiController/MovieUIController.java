package com.theatre.uiController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.theatre.repository.MovieRepository;
import com.theatre.repository.TheatreRepository;
import com.theatre.securityConfig.LoggedInTheatre;
import com.theatre.securityConfig.RegisteredTheatre;
import com.theatre.service.MovieService;

@Controller
public class MovieUIController
{
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	
	
	@GetMapping("/movie/{id}")
	private ResponseEntity<Movie> getMovie(@PathVariable("id") int id)
	{
		return movieService.getMovie(id);
	}
	
	@PostMapping("/addMovie")
	private Movie addMovie(@RequestBody Movie movie)
	{
		return movieService.addMovie(movie);
	}
	
	@GetMapping("/addToTheatre/{movieId}")
	public String addMovie(@LoggedInTheatre RegisteredTheatre theatre, @PathVariable("movieId") int id, Model model) {

		Movie movie = movieRepository.findById(id).get();
		
		MoviePricingDTO moviePricingDTO = new MoviePricingDTO();
		
		model.addAttribute("movieId", movie.getMovieId());
		model.addAttribute("theatre", theatre.getUsername());
		model.addAttribute("movieName", movie.getMovieName() );
		model.addAttribute("moviePricingDTO", moviePricingDTO);
		return "addPricingInfo";
		
	}
	
	@PostMapping("/addMovie/{movieId}")
	public String addMovietoTheatre(@LoggedInTheatre RegisteredTheatre theatre,
			@PathVariable("movieId") int movieId,
			@ModelAttribute MoviePricingDTO movieDTO
			)
	{
		System.out.println(movieDTO.getDate());
		
		Theatre dbTheatre = theatreRepository.findByName(theatre.getUsername());
		movieService.addMovietoTheatre(dbTheatre.getTheatreId(), movieId, movieDTO);
		return "redirect:/index";
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
	private void deleteMovie(@PathVariable("id") int id)
	{
		movieService.deleteMovie(id);
	}
	
	@GetMapping("/allMovies")
	private String getAllMovie(Model model)
	{
		model.addAttribute("listMovies",  movieService.getAllMovie());
		return "AllMovies";
	}
	
//	@GetMapping("/movies/{city}")
//	private List<Movie> getMoviebyCity(@PathVariable("city") String city)
//	{
//		return movieService.getMovieByCity(city);
//	}
	
	
}
