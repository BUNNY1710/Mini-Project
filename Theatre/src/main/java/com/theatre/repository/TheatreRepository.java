package com.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theatre.model.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Integer>
{
	public Theatre findByName(String theatreName);
}
