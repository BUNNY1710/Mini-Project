package com.theatre.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.theatre.repository.TheatreRepository;
import com.theatre.service.TheatreService;

class TheatreServiceTest {

	@Mock
	private TheatreRepository theatreRepository;
	
	@Autowired
	private TheatreService theatreService;
	
	@Test
	void testRegisterTheatre() {
	}

	@Test
	void testGetTheatreDetails() {
	}

	@Test
	void testEditTheatre() {
	}

	@Test
	void testDeleteTheatre() {
	}

	@Test
	void testGetAllTheatre() {
		
		theatreService.getAllTheatre();
		
		verify(theatreRepository).findAll();
	}

	@Test
	void testGetTheatreByName() {
	}

}
