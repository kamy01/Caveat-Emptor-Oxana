package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import FacesMessages.MyFacesMessage;
import constants.Constant;
import exception.AccountException;
import model.UserDto;
import repository.user.AccountStatus;
import services.email.SendEmailService;
import services.user.IRegisterService;
import services.user.IUserService;

@ManagedBean(name = "userRegister")
@ViewScoped
public class UserRegister {

	private static final long serialVersionUID = 5443351151396868724L;

	private boolean isRegisterEnabled;

	@EJB
	IRegisterService iRegisterService;

	@EJB
	IUserService iUserService;
	UserDto user;

	@PostConstruct
	public void init() {

		user = new UserDto();
		isRegisterEnabled = false;

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isRegisterEnabled() {
		return isRegisterEnabled;
	}

	public void setRegisterEnabled(boolean isRegisterEnabled) {
		this.isRegisterEnabled = isRegisterEnabled;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public void checkExistingUsername() {

		try {

			if (iUserService.getUserByUsername(user.getUserName()) != null) {

				MyFacesMessage.addMessage(FacesMessage.SEVERITY_WARN, Constant.REGISTER_ERROR,
						Constant.USERNAME_EXISTS);
				
				setRegisterEnabled(false);

			}

		} catch (AccountException e) {

			setRegisterEnabled(true);

		}

	}

	public void checkExistingEmail() {
		try {

			if (iUserService.getUserByEmail(user.getEmail()) != null) {

				MyFacesMessage.addMessage(FacesMessage.SEVERITY_WARN, Constant.REGISTER_ERROR, Constant.EMAIL_EXISTS);
				
				setRegisterEnabled(false);

			}
			
		} catch (AccountException e) {

			setRegisterEnabled(true);

		}

	}

	public String register(){

		try {

			if (isRegisterEnabled) {

				user.setAdmin(false);
				user.setStatus(AccountStatus.PENDING.getValue());

				String key = SendEmailService.sendEmail(user);

				iRegisterService.registerNewUser(user, key);

				return Constant.REGISTERED_SUCCESS_PAGE + "?faces-redirect=true";

			}

			MyFacesMessage.addExternalMessage(FacesMessage.SEVERITY_WARN, Constant.REGISTER_ERROR, Constant.ALREADY_REGISTERED);
			
			return Constant.REGISER_PAGE + "?faces-redirect=true";

		} catch (AccountException e) {

			return Constant.REGISER_PAGE + "?faces-redirect=true";

		}

	}

}
