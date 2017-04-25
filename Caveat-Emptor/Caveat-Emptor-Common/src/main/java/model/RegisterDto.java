package model;

import java.sql.Timestamp;

public class RegisterDto {

	private Long id;
	private Timestamp confirmationDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getConfirmationDate() {
		return (Timestamp)confirmationDate.clone();
	}
	public void setConfirmationDate(Timestamp confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	
}
