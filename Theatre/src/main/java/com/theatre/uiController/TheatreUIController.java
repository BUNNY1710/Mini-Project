package com.theatre.uiController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.theatre.dto.MoviePricingDTO;
import com.theatre.dto.MoviesDetailsDTO;
import com.theatre.model.Movie;
import com.theatre.model.MoviePricing;
import com.theatre.model.Theatre;
import com.theatre.repository.MoviePricingRepository;
import com.theatre.repository.MovieRepository;
import com.theatre.repository.TheatreRepository;
import com.theatre.securityConfig.LoggedInTheatre;
import com.theatre.securityConfig.RegisteredTheatre;
import com.theatre.service.TheatreService;

@Controller
//@RequestMapping("/theatre-api/")
public class TheatreUIController {
	@Autowired
	private TheatreService theatreService;

	@Autowired
	private MoviePricingRepository moviePricingRepository;

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/register-theatre")
	public String register(Model model) {
		
		Theatre newTheatre = new Theatre();
		model.addAttribute("theatre", newTheatre);
		return "registerTheatre";
	}

	@PostMapping("/register-theatre")
	public String registerTheatre(@ModelAttribute Theatre theatre, Model model) {
		Theatre t = theatreService.getTheatreByName(theatre.getName());
		if (t != null) {
			String errorMessage = "Theatre " + t.getName() + " already Exists";
			model.addAttribute("theatreExistsError", errorMessage);
			return "registerTheatre";
		}

		theatre.setPassword(new BCryptPasswordEncoder().encode(theatre.getPassword()));
		theatreService.registerTheatre(theatre);
		return "redirect:/login";
	}

	@GetMapping("/theatre/{id}")
	public ResponseEntity<Theatre> getTheatreDetails(@PathVariable("id") int id) {
		return theatreService.getTheatreDetails(id);
	}

	@GetMapping("/editTheatre")
	public String editTheatreInfo(@LoggedInTheatre RegisteredTheatre theatre, Model model) {
		Theatre newTheatre =  theatreService.getTheatreByName(theatre.getUsername());
		model.addAttribute("theatre", newTheatre);
		return "editTheatre";
	}

	@PostMapping("/editTheatre")
	public String editTheatre(@LoggedInTheatre RegisteredTheatre theatre, @ModelAttribute Theatre theatreDTO, Model model) {
		
		int theatreId = theatreService.getTheatreByName(theatre.getUsername()).getTheatreId();
		Theatre dbTheatre = theatreService.getTheatreByName(theatreDTO.getName());
	
		if (dbTheatre != null) {
			String errorMessage = "Theatre " + dbTheatre.getName() + " already Exists";
			model.addAttribute("theatreExistsError", errorMessage);
			return "editTheatre";
		}

		theatreService.editTheatre(theatreDTO, theatreId);
		return "redirect:/logout";
	}

	@DeleteMapping("/deleteTheatre/{id}")
	public void deleteTheatre(@PathVariable("id") int id) {
		theatreService.deleteTheatre(id);
	}

	@GetMapping("/allTheatres")
	public List<Theatre> getAllTheatres() {
		return theatreService.getAllTheatre();
	}

	@GetMapping("/index")
	public String Home(Model model, @LoggedInTheatre RegisteredTheatre theatre) {
		Theatre loggedInTheatre = theatreService.getTheatreByName(theatre.getUsername());

		List<MoviesDetailsDTO> movieDetailsDTOList = new ArrayList<>();
		Set<Movie> theatreMovies = loggedInTheatre.getMovies();
		for (Movie m : theatreMovies) {
			MoviesDetailsDTO movieDetail = new MoviesDetailsDTO();
			MoviePricing pricing = moviePricingRepository.findMovieDetails(loggedInTheatre.getTheatreId(),
					m.getMovieId());
			movieDetail.setMovieId(m.getMovieId());
			movieDetail.setMovieName(m.getMovieName());
			movieDetail.setCity(m.getCity());
			movieDetail.setGenres(m.getGenres());
			movieDetail.setLanguage(m.getLanguage());
			movieDetail.setDate(pricing.getDate());
			movieDetail.setPrice(pricing.getPrice());
			movieDetail.setQuantity(pricing.getQuantity());
			movieDetailsDTOList.add(movieDetail);
		}

		model.addAttribute("movieDetailsList", movieDetailsDTOList);
		model.addAttribute("theatre", loggedInTheatre.getName());
		return "index";
	}

	@GetMapping("/movies")
	public String showMovies(@LoggedInTheatre RegisteredTheatre theatre, Model model) {
		List<Movie> movies = movieRepository.findAll();
		model.addAttribute("theatre", theatre.getUsername());
		model.addAttribute("movies", movies);
		return "movies";
	}

}
