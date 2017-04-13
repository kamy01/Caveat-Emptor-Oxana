package beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import persistence.UserDto;
import services.model.UserService;

@ManagedBean(name = "userLogin")
@ViewScoped
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
	private String message = "Enter username and password";
	private String username = null;
	private String password = null;
	private boolean loginEnabled = false; 

	@EJB
	UserService usrService;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLoginEnabled() {
		return loginEnabled;
	}

	public void setLoginEnabled(boolean loginEnabled) {
		this.loginEnabled = loginEnabled;
	}

	public String getPassword() {
		return password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {

		UserDto usrDto = usrService.getUser(username);

		if (usrDto != null && password.equals(usrDto.getPassword())) {
			message = "Successfully logged-in.";
			return "templates/template";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The password is incorrect", "The password is incorrect"));
			return "index";
		}
	}
	
	public void doSomething(){
		
	}
	
	public void validateForm(){
		
		if(!username.isEmpty())
			loginEnabled = true;
		
	}

}
