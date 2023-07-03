package com.theatre.dto;

import java.time.LocalDate;
import java.util.Date;

public class MoviesDetailsDTO {

	private int movieId;
	private String movieName;
	private String theatreName;
	private double price;
	private String city;
	private String genres;
	private String language;
	private LocalDate date;
	private int quantity;

	public MoviesDetailsDTO(int movieId, String movieName, String theatreName, double price, String city, String genres,
			String language, LocalDate date, int quantity) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.price = price;
		this.city = city;
		this.genres = genres;
		this.language = language;
		this.date = date;
		this.quantity = quantity;
	}

	public MoviesDetailsDTO() {
		super();
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
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

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "MoviesDetailsDTO [movieId=" + movieId + ", movieName=" + movieName + ", theatreName=" + theatreName
				+ ", price=" + price + ", city=" + city + ", genres=" + genres + ", language=" + language + ", date="
				+ date + ", quantity=" + quantity + "]";
	}

}
