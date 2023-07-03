package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.MovieTicket;

public interface TicketRepository extends JpaRepository<MovieTicket, Integer> {

}
