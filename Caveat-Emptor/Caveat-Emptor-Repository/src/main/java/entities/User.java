package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "User.findByUsername", query = "SELECT user from User user WHERE user.username = :username"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT user from User user WHERE user.email = :email"), })
@Table(name = "users")
public class User {
	
	public static final String FIND_USER_BY_USERNAME = "User.findByUsername";
	public static final String FIND_USER_BY_EMAIL = "User.findByEmail";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID", nullable=false)
	private Long userId;
	@Column(name="FIRSTNAME", nullable=false)
	private String firstName;
	@Column(name="LASTNAME", nullable=false)
	private String lastName;
	@Column(name="USERNAME", nullable=false)
	private String userName;
	@Column(name="PASSWORD", nullable=false)
	private String password;
	@Column(name="EMAIL", nullable=false)
	private String email;
	@Column(name="ADMIN", nullable=false)
	private boolean admin;

	@Column(name = "CONFIRMATION_DATE")
	private Date confirmationDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

}
