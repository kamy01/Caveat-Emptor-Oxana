package entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "Register.findByUserId", query = "SELECT user from Register user WHERE user.ID = :userId"),
		@NamedQuery(name = "Register.findByKeyValue", query = "SELECT user from Register user WHERE user.key = :key"),})
@Table(name = "register")
public class Register implements Serializable {

	private static final long serialVersionUID = -5579189702475104527L;
	
	public static final String FIND_USER_REGISER_BY_USER_ID = "Register.findByUserId";
	public static final String FIND_USER_REGISER_BY_KEY_VALUE = "Register.findByKeyValue";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REGISTER_ID")
	private Long ID;

	@Column(name = "CONFIRMATION_DATE")
	private Timestamp confirmationDate;

	@Column(name = "REGISTER_KEY")
	private String key;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private Users user;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getRegisterId() {
		return ID;
	}

	public void setRegisterId(Long registerId) {
		this.ID = registerId;
	}

	public Timestamp getConfirmationDate() {
		return (Timestamp)confirmationDate.clone();
	}

	public void setConfirmationDate(Timestamp confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
