package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import FacesMessages.MyFacesMessage;
import constants.Constant;
import exception.AccountException;
import model.UserDto;
import repository.user.AccountStatus;
import services.user.IUserService;

@ManagedBean(name = "userLogin")
@ApplicationScoped
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 5443351151396868724L;

	private String username;
	private String password;
	private Long id;
	private boolean isLoginEnabled;

	@EJB
	IUserService userService;

	@PostConstruct
	public void init() {
		isLoginEnabled = false;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

		try {

			UserDto userDto = userService.getUserByUsername(username);

			if (userDto != null && !isAccountConfirmed(userDto)) {

				return "pages/" + Constant.REGISTERED_SUCCESS_PAGE + "?faces-redirect=true";

			} else if (userDto != null && password.equals(userDto.getPassword())) {

				setId(userDto.getId());
				
				return Constant.CAVEAT_EMPTOR_PAGE + "?faces-redirect=true";

			} else {
				
				MyFacesMessage.addExternalMessage(FacesMessage.SEVERITY_WARN, Constant.LOGIN_ERROR, Constant.INVALID_CREDENTIALS);

				return Constant.HOME_PAGE + "?faces-redirect=true";
				
			}

		} catch (AccountException e) {

			MyFacesMessage.addExternalMessage(FacesMessage.SEVERITY_WARN, Constant.LOGIN_ERROR, Constant.NO_SUCH_USER);

			 return Constant.HOME_PAGE + "?faces-redirect=true";
		}

	} 

	private boolean isAccountConfirmed(UserDto userDto) {

		return userDto.getStatus().equals(AccountStatus.ACTIVE.getValue());
	}

	public void validateForm() {

		if (!username.isEmpty()) {

			isLoginEnabled = true;

		}

	}

}
