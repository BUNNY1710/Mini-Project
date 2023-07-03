package com.theatre.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreId;
	private String name;
	private String password;

	@ManyToMany
	@JoinTable(name = "theatre_movie",
			joinColumns = @JoinColumn(name = "theatre_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id")
	)
	private Set<Movie> movies = new HashSet<>();

	public Theatre(int theatreId, String name, String password, Set<Movie> movies) {
		super();
		this.theatreId = theatreId;
		this.name = name;
		this.password = password;
		this.movies = movies;
	}

	public Theatre() {
		super();
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", name=" + name + ", password=" + password + ", movies=" + movies
				+ "]";
	}
}
