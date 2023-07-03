package com.theatre.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String city;
	private String movieName;
	private String genres;
	private String language;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "movies")
	private Set<Theatre> theatres = new HashSet<>();
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<MoviePricing> pricing = new HashSet<>();
	
	
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

	public Set<Theatre> getTheatres() {
		return theatres;
	}

	public void setTheatres(Set<Theatre> theatres) {
		this.theatres = theatres;
	}
	

	public Set<MoviePricing> getPricing() {
		return pricing;
	}

	public void setPricing(Set<MoviePricing> pricing) {
		this.pricing = pricing;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", city=" + city + ", movieName=" + movieName + ", genres=" + genres
				+ ", language=" + language + ", theatres=" + theatres + ", pricing=" + pricing + "]";
	}
}
