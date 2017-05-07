package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = "Users.findByUsername", query = "SELECT user from Users user WHERE user.userName = :username"),
		@NamedQuery(name = "Users.findByEmail", query = "SELECT user from Users user WHERE user.email = :email"), })
@Table(name = "users")
public class Users implements Serializable{

	private static final long serialVersionUID = -1081477993726958418L;
	
	public static final String FIND_USER_BY_USERNAME = "Users.findByUsername";
	public static final String FIND_USER_BY_EMAIL = "Users.findByEmail";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	@Column(name = "LASTNAME", nullable = false)
	private String lastName;

	@Column(name = "USERNAME", nullable = false)
	private String userName;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ADMIN")
	private boolean admin;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Items> items;

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
