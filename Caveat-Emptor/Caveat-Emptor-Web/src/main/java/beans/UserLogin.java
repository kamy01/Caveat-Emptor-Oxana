package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import constants.Constant;
import model.UserDto;
import repository.user.Status;
import services.user.IUserService;

@ManagedBean(name = "userLogin")
@ViewScoped
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;

	private String username;
	private String password;
	private boolean isLoginEnabled;

	@EJB
	IUserService userService;

	@PostConstruct
	public void init() {
		isLoginEnabled = false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isLoginEnabled() {
		return isLoginEnabled;
	}

	public void setLoginEnabled(boolean loginEnabled) {
		this.isLoginEnabled = loginEnabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {

		FacesContext context = FacesContext.getCurrentInstance();
		UserDto userDto = userService.getUserByUsername(username);

		if (userDto != null && userDto.getStatus().equals(Status.PENDING.getValue())) {

			return "pages/" + Constant.REGISTERED_SUCCESS_PAGE + "?faces-redirect=true";

		} else if (userDto != null && password.equals(userDto.getPassword())) {

			return Constant.CAVEAT_EMPTOR_PAGE + "?faces-redirect=true";

		} else {

			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, Constant.LOGIN_ERROR, Constant.INVALID_CREDENTIALS));

			return Constant.HOME_PAGE + "?faces-redirect=true";

		}
	}

	public void validateForm() {

		if (!username.isEmpty())
			isLoginEnabled = true;

	}

}
