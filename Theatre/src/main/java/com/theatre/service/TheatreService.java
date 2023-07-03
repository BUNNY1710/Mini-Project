package com.theatre.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.theatre.Exception.TheatreNotFoundException;
import com.theatre.model.Movie;
import com.theatre.model.Theatre;
import com.theatre.repository.TheatreRepository;

@Service
public class TheatreService {
	@Autowired
	private TheatreRepository theatreRepository;
	
	public Theatre registerTheatre(Theatre theatre)
	{
		return theatreRepository.save(theatre);
	}
	
	public ResponseEntity<Theatre> getTheatreDetails(int id)
	{
		
		Optional<Theatre> optionalTheatre = theatreRepository.findById(id);
		if(optionalTheatre.isEmpty())
		{
			throw new TheatreNotFoundException("Theatre not found with Id: "+ id);
		}
		return ResponseEntity.ok().body(optionalTheatre.get());
	}
	
	public Theatre editTheatre(Theatre theatre, int id)
	{
		Theatre savedTheatre = theatreRepository.findById(id).get();
		savedTheatre.setName(theatre.getName());
		savedTheatre.setPassword(new BCryptPasswordEncoder().encode(theatre.getPassword()));
		return theatreRepository.save(savedTheatre);
	}
	
	public void deleteTheatre(int id)
	{
		theatreRepository.deleteById(id);
	}
	
	public List<Theatre> getAllTheatre()
	{
		return theatreRepository.findAll();
	}
	
	
	// UI
	public Theatre getTheatreByName(String theatreName) {
		return theatreRepository.findByName(theatreName);
	}
}
