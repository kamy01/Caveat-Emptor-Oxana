package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import constants.Constant;
import model.UserDto;
import services.common.Utils;
import services.email.SendEmailService;
import services.user.IRegisterService;
import services.user.IUserService;

@ManagedBean(name = "userRegister")
@ViewScoped
public class UserRegister {

	private static final long serialVersionUID = 5443351151396868724L;
	
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private boolean isLoginEnabled;

	@EJB
	IRegisterService iRegisterService;

	@EJB
	IUserService iUserService;

	@PostConstruct
	public void init() {
		isLoginEnabled = false;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isLoginEnabled() {
		return isLoginEnabled;
	}

	public void setLoginEnabled(boolean loginEnabled) {
		this.isLoginEnabled = loginEnabled;
	}

	public void checkExistingUsername() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (iUserService.getUserByUsername(username) != null) {

			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, Constant.REGISTER_ERROR, Constant.USERNAME_EXISTS));

		}

	}

	public void checkExistingEmail() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (iUserService.getUserByEmail(email) != null) {

			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, Constant.REGISTER_ERROR, Constant.EMAIL_EXISTS));

		}

	}

	public String register() {

		Utils util = new Utils();
		
		UserDto userDto = util.createUserDto(firstName, lastName, username, email, password);
		
		String key = SendEmailService.sendEmail(userDto);
		
		if(iRegisterService.isUserRegistered(userDto, key))
			return Constant.REGISTERED_SUCCESS_PAGE + "?faces-redirect=true";
		
		return Constant.REGISER_PAGE + "?faces-redirect=true";
		
	}

	public void validateForm() {

		if (!firstName.isEmpty())
			isLoginEnabled = true;

	}

}
