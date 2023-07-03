package com.theatre.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class MoviePricing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double price;
	private LocalDate date;
	private int quantity;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;
	
	

	public MoviePricing() {
		super();
	}

	public MoviePricing(int id, double price, LocalDate date, int quantity, Movie movie, Theatre theatre) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.quantity = quantity;
		this.movie = movie;
		this.theatre = theatre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int d) {
		this.quantity = d;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	@Override
	public String toString() {
		return "MoviePricing [id=" + id + ", price=" + price + ", date=" + date + ", quantity=" + quantity + ", movie="
				+ movie + ", theatre=" + theatre + "]";
	}
}
