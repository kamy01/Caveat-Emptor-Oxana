package model;

import java.sql.Timestamp;

public class RegisterDto {

	private Long Id;
	private Timestamp confirmationDate;
	
	public Long getRegisterId() {
		return Id;
	}
	public void setRegisterId(Long registerId) {
		this.Id = registerId;
	}
	public Timestamp getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(Timestamp confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	
}
