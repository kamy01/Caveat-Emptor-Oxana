package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import FacesMessages.MyFacesMessage;
import constants.AccountConstants;
import constants.RedirectPages;
import exception.CaveatEmptorException;
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

				MyFacesMessage.addMessage(FacesMessage.SEVERITY_WARN, AccountConstants.ERROR.getValue(),
						AccountConstants.DUPLICATE_USERNAME.getValue());
				
				setRegisterEnabled(false);

			}

		} catch (CaveatEmptorException e) {

			setRegisterEnabled(true);

		}

	}

	public void checkExistingEmail() {
		try {

			if (iUserService.getUserByEmail(user.getEmail()) != null) {

				MyFacesMessage.addMessage(FacesMessage.SEVERITY_WARN, AccountConstants.ERROR.getValue(), AccountConstants.DUPLICATE_EMAIL.getValue());
				
				setRegisterEnabled(false);

			}
			
		} catch (CaveatEmptorException e) {

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

				return RedirectPages.REGISTER_SUCCESS_PAGE.getValue() + "?faces-redirect=true";

			}

			MyFacesMessage.addExternalMessage(FacesMessage.SEVERITY_WARN, AccountConstants.ERROR.getValue(), AccountConstants.ALREADY_REGISTERED.getValue());
			
			return RedirectPages.REGISTER_PAGE.getValue() + "?faces-redirect=true";

		} catch (CaveatEmptorException e) {
			
			MyFacesMessage.addExternalMessage(FacesMessage.SEVERITY_WARN, AccountConstants.ERROR.getValue(), AccountConstants.REGISTER_NOT_SUCCIDED.getValue());

			return RedirectPages.REGISTER_PAGE.getValue() + "?faces-redirect=true";

		}

	}

}
