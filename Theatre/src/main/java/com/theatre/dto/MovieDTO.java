package com.theatre.dto;


public class MovieDTO {
	private String movieName;
	private String city;
	private String genres;
	private String language;
	
	public MovieDTO(String movieName, String city, String genres, String language) {
		super();
		this.movieName = movieName;
		this.city = city;
		this.genres = genres;
		this.language = language;
	}
	
	public MovieDTO() {
		super();
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String name) {
		this.movieName = name;
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
	@Override
	public String toString() {
		return "MovieDTO [name=" + movieName + ", city=" + city + ", genres=" + genres + ", language=" + language + "]";
	}
}
