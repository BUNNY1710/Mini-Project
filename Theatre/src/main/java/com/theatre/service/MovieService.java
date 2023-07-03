package com.theatre.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.theatre.Exception.MovieNotFoundException;
import com.theatre.dto.MovieDTO;
import com.theatre.dto.MoviePricingDTO;
import com.theatre.model.Movie;
import com.theatre.model.MoviePricing;
import com.theatre.model.Theatre;
import com.theatre.repository.MoviePricingRepository;
import com.theatre.repository.MovieRepository;
import com.theatre.repository.TheatreRepository;


@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
//	Logger logger = LoggerFactory.getLogger(MovieService.class);

	public Movie addMovie(Movie movie)
	{
		return movieRepository.save(movie);
	}
	
	public Theatre addMovietoTheatre(int theatreId, int movieId, MoviePricingDTO moviePricingDTO)
	{
		Theatre theatre = theatreRepository.findById(theatreId).get();
		Movie movie = movieRepository.findById(movieId).get();
		
		MoviePricing moviePricing = new MoviePricing();
		
		moviePricing.setPrice(moviePricingDTO.getPrice());
		moviePricing.setDate( LocalDate.parse(moviePricingDTO.getDate()));
		moviePricing.setQuantity(moviePricingDTO.getQuantity());
		
		moviePricing.setMovie(movie);
		moviePricing.setTheatre(theatre);
		
		movie.getPricing().add(moviePricing);
		movieRepository.save(movie);
		
		theatre.getMovies().add(movie);		
		return theatreRepository.save(theatre);
	}
	
	public ResponseEntity<Movie> getMovie(int id)
	{
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		if(optionalMovie.isEmpty())
		{
			throw new MovieNotFoundException("Movie not found with Id: "+ id);
		}
		return ResponseEntity.ok().body(optionalMovie.get());
	}
	
	public Movie editMovie(MovieDTO movieDTO, int id)
	{
		Movie savedMovie = movieRepository.findById(id).get();
		
		savedMovie.setMovieName(movieDTO.getMovieName());
		savedMovie.setGenres(movieDTO.getGenres());
		savedMovie.setLanguage(movieDTO.getLanguage());
		savedMovie.setCity(movieDTO.getCity());
		return movieRepository.save(savedMovie);
	}
	
	public void deleteMovie(int movieId)
	{		
		movieRepository.deleteById(movieId);
	}
	
	public List<Movie> getAllMovie()
	{
		return movieRepository.findAll();
	}

	public List<Movie> getMovieByCity(String city) {
		
		return movieRepository.findByCity(city);
		
	}
}
