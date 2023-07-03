package com.user.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.DTO.MovieDTO;
import com.user.DTO.MoviePricingDTO;
import com.user.DTO.TicketDTO;
import com.user.Exception.MovieNotAvailableException;
import com.user.Exception.UserNotFoundException;
import com.user.model.MovieTicket;
import com.user.model.User;
import com.user.openFeign.TheatreServiceClient;
import com.user.repository.TicketRepository;
import com.user.repository.UserRepository;
import com.user.securityConfig.LoggedInUser;
import com.user.securityConfig.RegisteredUser;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TheatreServiceClient theatreServiceClient;
	
	@Autowired
	TicketRepository ticketRepository;
	
	public User createUser(User user)
	{
		return userRepository.save(user);
	}
	
	public ResponseEntity<User> getUser(int id)
	{
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isEmpty())
		{
			throw new UserNotFoundException("Movie not found with Id: "+ id);
		}
		return ResponseEntity.ok().body(optionalUser.get());
	}
	
	public User editUser(int id, User user)
	{
		User savedUser = userRepository.findById(id).get();
		
		savedUser.setName(user.getName());
		savedUser.setEmail(user.getEmail());
		savedUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		savedUser.setPhone(user.getPhone());
		
		return userRepository.save(savedUser);
	}
	
	public void deleteUser(int id)
	{
		userRepository.deleteById(id);
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public List<MovieDTO> getAllMovies()
	{
		return theatreServiceClient.getAllMovies();
	}
	
	public MovieTicket bookTicket(int userId ,TicketDTO ticketDTO)
	{
		
		MoviePricingDTO pricing = theatreServiceClient.getMoviePricing(ticketDTO.getTheatreId(), ticketDTO.getMovieId());
	
		User user = userRepository.findById(userId).get();
		if(user == null) {
			throw new UserNotFoundException("User not available");
		}
		
		MovieTicket ticket = new MovieTicket();
		
		boolean isMovieAvailable = theatreServiceClient.bookTicket(ticketDTO);
		
		if(!isMovieAvailable) {
			throw new MovieNotAvailableException("Either Movie is not avalable for the given quantity or some error occured");
		}
		
		ticket.setCity(theatreServiceClient.getMovieDetails(ticketDTO.getMovieId()).getCity());
		ticket.setMovieName( theatreServiceClient.getMovieDetails(ticketDTO.getMovieId()).getMovieName());
		ticket.setTheatreName( theatreServiceClient.getTheatreName(ticketDTO.getTheatreId()));
		
		ticket.setDate(pricing.getDate());
		ticket.setQuantity(ticketDTO.getQuantity());
		ticket.setPrice(pricing.getPrice() * ticketDTO.getQuantity());
		ticket.setBookedBy(user.getEmail());
		
//		System.out.println("TIcket created + " + ticket.toString());
		
		ticket = ticketRepository.save(ticket);
		

		List<MovieTicket> userTickets = user.getTickets();
		userTickets.add(ticket);
		user.setTickets(userTickets);
		userRepository.save(user);
		
		return ticket;
	}

	public MovieTicket getTicket(int id) {

		return ticketRepository.findById(id).get();
	}
	
	public User getByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
}
