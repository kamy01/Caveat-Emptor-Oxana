package model;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 6204073745775254673L;

	private Long _ID;
	private String _firstName;
	private String _lastName;
	private String _userName;
	private String _password;
	private String _email;
	private String _status;
	private boolean _isAdmin;

	
	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		this._status = status;
	}

	public Long getUserId() {
		return _ID;
	}

	public void setUserId(Long userId) {
		this._ID = userId;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		this._userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public boolean isAdmin() {
		return _isAdmin;
	}

	public void setAdmin(boolean _isAdmin) {
		this._isAdmin = _isAdmin;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		this._email = email;
	}

}
