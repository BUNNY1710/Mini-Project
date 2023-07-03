package com.theatre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre.model.Theatre;
import com.theatre.service.TheatreService;

@RestController
@RequestMapping("/theatre-api/")
public class TheatreController
{
	@Autowired
	private TheatreService theatreService;
	
	@PostMapping("/register-theatre")
	public Theatre registerTheatre(@RequestBody Theatre theatre)
	
	{
		theatre.setPassword(new BCryptPasswordEncoder().encode(theatre.getPassword()));
		theatreService.registerTheatre(theatre);
		return theatre;
	}
	
	@GetMapping("/theatre/{id}")
	public ResponseEntity<Theatre> getTheatreDetails(@PathVariable("id") int id)
	{
		return theatreService.getTheatreDetails(id);
	}

	@PutMapping("/editTheatre/{id}")
	public void editTheatre(@PathVariable("id") int id, @RequestBody Theatre theatre)
	{
		theatreService.editTheatre(theatre, id);
	}
	
	@DeleteMapping("/deleteTheatre/{id}")
	public String deleteTheatre(@PathVariable("id") int id)
	{
		theatreService.deleteTheatre(id);
		return "Theatre deleted Successfully!!";
	}
	
	@GetMapping("/allTheatres")
	public List<Theatre> getAllTheatres()
	{
		return theatreService.getAllTheatre();
	}
	
	
	//needed for UI
	
	
	@GetMapping("/getTheatreName/{id}")
	public String getTheatreName(@PathVariable int id) {
		return theatreService.getTheatreDetails(id).getBody().getName();
	}
	
	@GetMapping("/getTheatreId/{theatreName}")
	public int getTheatreId(@PathVariable("theatreName") String name){
		return theatreService.getTheatreByName(name).getTheatreId();
	}
}
