package com.theatre.dto;

import java.time.LocalDate;
import java.util.Date;

public class MoviePricingDTO
{
	private double price;
	private String date;
	private int quantity;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "MoviePricingDTO [price=" + price + ", date=" + date + ", quantity=" + quantity + "]";
	}
	public MoviePricingDTO(double price, String date, int quantity) {
		super();
		this.price = price;
		this.date = date;
		this.quantity = quantity;
	}
	public MoviePricingDTO() {
		super();
	}
}
