package com.user.DTO;

import java.time.LocalDate;
import java.util.Date;


public class MoviePricingDTO {

	private int id;
	private double price;
	private LocalDate date;
	private int quantity;
	
	
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
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MoviePricingDTO(int id, double price, LocalDate date, int quantity) {
		super();
		this.id = id;
		this.price = price;
		this.date = date;
		this.quantity = quantity;
	}
	
	public MoviePricingDTO() {
	}
	
	@Override
	public String toString() {
		return "MoviePricing [id=" + id + ", price=" + price + ", date=" + date + ", quantity=" + quantity +"]";
	}
	
}
