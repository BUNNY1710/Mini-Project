package com.user.DTO;

import java.util.HashSet;
import java.util.Set;


public class MovieDTO {
	
	private int movieId;
	private String city;
	private String movieName;
	private String genres;
	private String language;
	
	
	private Set<MoviePricingDTO> pricing = new HashSet<>();


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
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


	public Set<MoviePricingDTO> getPricing() {
		return pricing;
	}


	public void setPricing(Set<MoviePricingDTO> pricing) {
		this.pricing = pricing;
	}


	public MovieDTO(int movieId, String city, String movieName, String genres, String language,
			Set<MoviePricingDTO> pricing) {
		super();
		this.movieId = movieId;
		this.city = city;
		this.movieName = movieName;
		this.genres = genres;
		this.language = language;
		this.pricing = pricing;
	}

	public MovieDTO() {
		
	}


	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", city=" + city + ", movieName=" + movieName + ", genres=" + genres
				+ ", language=" + language + ", pricing=" + pricing + "]";
	}
	

}
