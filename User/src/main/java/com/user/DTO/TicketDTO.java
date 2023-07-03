package com.user.DTO;

public class TicketDTO {
	
    private int theatreId;
    private int movieId;
    private int quantity;
    
    
	public TicketDTO() {
		super();
	}
	public TicketDTO(int theatreId, int movieId, int quantity) {
		super();
		this.theatreId = theatreId	;
		this.movieId = movieId;
		this.quantity = quantity;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "TicketDTO [theatreId=" + theatreId + ", movieId=" + movieId + ", quantity="
				+ quantity + "]";
	}
    
    

}
