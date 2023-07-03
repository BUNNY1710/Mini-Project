package com.user.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	private String movieName;
	private String theatreName;
	private LocalDate date;
	private int quantity;
	private double price;
	private String city;
	private String bookedBy;
	

	
	public MovieTicket(int ticketId, String movieName, String theatreName, LocalDate date, int quantity, double price,
			String city, String bookedBy) {
		super();
		this.ticketId = ticketId;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.date = date;
		this.quantity = quantity;
		this.price = price;
		this.city = city;
		this.bookedBy = bookedBy;
	}

	public MovieTicket() {
		super();
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}



	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	@Override
	public String toString() {
		return "MovieTicket [ticketId=" + ticketId + ", movieName=" + movieName + ", theatreName=" + theatreName + ", quantity="
				+ quantity + ", price=" + price + ", city=" + city + ", bookedBy=" + bookedBy + "]";
	}

	
	
	
}
