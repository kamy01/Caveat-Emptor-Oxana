package model;

import java.sql.Timestamp;

public class RegisterDto {

	private Long _registerId;
	private Timestamp _confirmationDate;
	
	public Long getRegisterId() {
		return _registerId;
	}
	public void setRegisterId(Long registerId) {
		this._registerId = registerId;
	}
	public Timestamp getConfirmationDate() {
		return _confirmationDate;
	}
	public void setConfirmationDate(Timestamp confirmationDate) {
		this._confirmationDate = confirmationDate;
	}
	
}
