package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.UserDto;
import services.user.UserService;

@ManagedBean(name = "userLogin")
@ViewScoped
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;
	private String username;
	private String password;
	private boolean loginEnabled; 

	@EJB
	UserService userService;
	
	@PostConstruct
	public void init(){
		loginEnabled = false;
	}
	

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

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		UserDto userDto = userService.getUser(username);

		if (userDto != null && password.equals(userDto.getPassword())) {
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username));
			return "templates/template";
			
		} else {
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials"));
			return "index";
			
		}
	}
	
	public void validateForm(){
		
		if(!username.isEmpty())
			loginEnabled = true;
		
	}

}
