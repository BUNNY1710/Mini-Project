package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.DTO.MovieDTO;
import com.user.DTO.TicketDTO;
import com.user.model.MovieTicket;
import com.user.model.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/user-api/")
public class UserController {
	
	@Autowired
	public UserService userService;
	

	@PostMapping("/user")
	public User createUser(@RequestBody User user)
	{
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userService.createUser(user);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id)
	{
		return userService.getUser(id);
	}
	
	@PutMapping("/editUser/{id}")
	public User editUser(@PathVariable("id") int id, @RequestBody User user)
	{
		return userService.editUser(id, user);
	}
	
	@DeleteMapping("/delete-user/{id}")
	public void deleteUser(@PathVariable("id") int id)
	{
		userService.deleteUser(id);
	}
	
	@GetMapping("/allUsers")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/getMovies")
	public List<MovieDTO> getMovies() {
		return userService.getAllMovies();
	}
	
	@GetMapping("/{userId}/bookTicket")
	public MovieTicket bookMovie(@PathVariable("userId") int userId, @RequestBody TicketDTO ticketDTO) {
		return userService.bookTicket(userId, ticketDTO);
	}
	
	@GetMapping("/ticket-info/{id}")
	public MovieTicket ticketInfo(@PathVariable("id") int id) {
		return userService.getTicket(id);
	}
}
