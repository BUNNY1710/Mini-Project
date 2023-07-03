package com.user.uiController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.user.DTO.MovieDTO;
import com.user.DTO.MoviePricingDTO;
import com.user.DTO.MoviesDetailsDTO;
import com.user.DTO.TicketDTO;
import com.user.model.MovieTicket;
import com.user.model.User;
import com.user.openFeign.TheatreServiceClient;
import com.user.repository.TicketRepository;
import com.user.repository.UserRepository;
import com.user.securityConfig.LoggedInUser;
import com.user.securityConfig.RegisteredUser;
import com.user.service.UserService;

@Controller
public class UserUiController {

	@Autowired
	public UserService userService;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private TheatreServiceClient theatreServiceClient;

	@GetMapping("/register-user")
	public String registerUser(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "registerUser";
	}

	@PostMapping("/register-user")
	public String createUser(@ModelAttribute User user, Model model) {
		User u = userService.getByEmail(user.getEmail());
		if (u != null) {
			String errorMessage = "User " + u.getEmail() + " already Exists";
			model.addAttribute("userExistsError", errorMessage);
			return "registerUser";
		}
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.createUser(user);
		return "redirect:/login";
	}

	@GetMapping("/index")
	public String index(@LoggedInUser RegisteredUser user, Model model, @RequestParam(required = false, name = "movieName") String movieName) {

		model.addAttribute("user", user.getUsername());		
		
		List<MovieDTO> allMovies = theatreServiceClient.getAllMovies();
		List<MovieDTO> availableMovies = allMovies.stream().filter(movie -> movie.getPricing().size() > 0)
				.collect(Collectors.toList());
		
		if(movieName != null) {
			List<MovieDTO> availableMoviesByName = availableMovies.stream().filter(movie -> movie.getMovieName().toLowerCase().contains(movieName.toLowerCase())).toList();
			model.addAttribute("searchResult", "Search result for " + "\"" + movieName + "\"");
			model.addAttribute("movies", availableMoviesByName);
			return "index";
		}
		
		model.addAttribute("movies", availableMovies);
		return "index";
	}

	@GetMapping("/buyTickets/{movieId}")
	public String buyTickets(@LoggedInUser RegisteredUser user, @PathVariable("movieId") int movieId, Model model, @RequestParam(required = false, name = "error") String error ) {

		List<MoviesDetailsDTO> movieDetails = theatreServiceClient.checkAvailability(movieId);

		System.out.println("Movie details  = " + movieDetails);

		model.addAttribute("quantityError", error);
		model.addAttribute("user", user.getUsername());
		model.addAttribute("movieName", movieDetails.get(0).getMovieName());
		model.addAttribute("movieDetails", movieDetails);

		return "checkTicket";
	}

	@GetMapping("/confirm/{movieId}/{theatreName}")
	public String confirmTicket(@LoggedInUser RegisteredUser user, @PathVariable("theatreName") String theatreName,
			@PathVariable("movieId") int movieId, @RequestParam("quantity") int quantity, Model model) {

		User loggedInUser = userService.getByEmail(user.getUsername());
		MovieDTO movieToBook = theatreServiceClient.getMovieDetails(movieId);
	

		TicketDTO ticketDTO = new TicketDTO(theatreServiceClient.getTheatreId(theatreName), movieToBook.getMovieId(), quantity);

		boolean availability = theatreServiceClient.bookTicket(ticketDTO);
		
		if(availability == false) {
			String errorMessage = "Quantity excceds the theatre limit";
			System.out.println("In if condition : " + errorMessage);
			return "redirect:/buyTickets/" + movieToBook.getMovieId()+"?error="+errorMessage;
		}

		MoviePricingDTO pricing = theatreServiceClient.getMoviePricing(ticketDTO.getTheatreId(), ticketDTO.getMovieId());
		MovieTicket ticket = new MovieTicket();

		ticket.setCity(theatreServiceClient.getMovieDetails(ticketDTO.getMovieId()).getCity());
		ticket.setMovieName( theatreServiceClient.getMovieDetails(ticketDTO.getMovieId()).getMovieName());
		ticket.setTheatreName( theatreServiceClient.getTheatreName(ticketDTO.getTheatreId()));
		ticket.setQuantity(ticketDTO.getQuantity());
		ticket.setPrice(pricing.getPrice() * ticketDTO.getQuantity());
		ticket.setBookedBy(loggedInUser.getEmail());
		ticket.setDate(pricing.getDate());
		System.out.println("TIcket created + " + ticket.toString());
		
		ticket = ticketRepository.save(ticket);
		

		List<MovieTicket> userTickets = loggedInUser.getTickets();
		userTickets.add(ticket);
		loggedInUser.setTickets(userTickets);
		userRepository.save(loggedInUser);
		

		
		List<MovieTicket> userBookings = loggedInUser.getTickets();
		model.addAttribute("user", user.getUsername());
		model.addAttribute("bookings", userBookings);
		
		return "redirect:/myBookings";
	}
	
	@GetMapping("/myBookings")
	public String userBookings(@LoggedInUser RegisteredUser user, Model model) {
		
		User loggedInUser = userService.getByEmail(user.getUsername());
		List<MovieTicket> userBookings = loggedInUser.getTickets();
		
		model.addAttribute("user", user.getUsername());
		model.addAttribute("bookings", userBookings);

		return "myBookings";
	}

	@GetMapping("/editUser")
	public String editTheatreInfo(@LoggedInUser RegisteredUser user, Model model) {
		User userNew = userService.getByEmail(user.getUsername());
		model.addAttribute("user", userNew);
		return "editUser";
	}

	@PostMapping("/editUser")
	public String editUser(@LoggedInUser RegisteredUser user, @ModelAttribute User userDTO, Model model) {

		int userId = userService.getByEmail(user.getUsername()).getUserId();
		User dbUser = userService.getByEmail(userDTO.getName());

		if (dbUser != null) {
			String errorMessage = "User  " + dbUser.getName() + " already Exists";
			model.addAttribute("userExitsError", errorMessage);
			return "editUser";
		}
		userDTO.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));

		userService.editUser(userId, userDTO);
		return "redirect:/logout";
	}


//	@GetMapping("/deleteBooking/{bookingId}")
//	public String deleteBooking(@PathVariable("bookingId") int bookingId, @LoggedInUser RegisteredUser user) {
//		int  userId = userRepository.findByEmail(user.getUsername()).getUserId();
//		userService.deleteBooking(userId,bookingId);
//		return "myBookings";
//	}

}
