package services.user.implementation;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import constants.Constant;
import entities.Register;
import entities.Users;
import model.UserDto;
import repository.user.IUserRepository;
import services.user.IRegisterService;
import repository.user.AccountStatus;

@Stateless
public class RegisterServiceImpl implements IRegisterService {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@EJB
	IUserRepository iUserRepository;

	public boolean isUserRegistered(UserDto userDto, String key) {

		Users user = populateUserObject(userDto);
		Register register = new Register();

		register.setConfirmationDate(getCurrentDatePlusOneDay());
		register.setKey(key);
		register.setUser(user);

		try{
			
			entityManager.persist(register);
			
			return true;
			
		} catch (PersistenceException e) {

			return false;

		}

	}

	public Register findUserByKey(String key) {

		return iUserRepository.findUserByKeyValue(key, entityManager);

	}

	public void deleteRowFromRegisterTable(Register register) {

		entityManager.remove(register);

	}

	public void updateUserStatus(Users user) {

		entityManager.merge(user);

	}

	public void deleteConfirmedRegistration(Register register) {

		Register registerToDelete = entityManager.getReference(Register.class, register.getId());

		if (registerToDelete != null) {

			deleteRowFromRegisterTable(registerToDelete);
		}

	}

	public boolean isAccountActive(String key) {

		try {

			Register register = findUserByKey(key);

			if (register != null) {

				register.getUser().setStatus(AccountStatus.ACTIVE.getValue());

				updateUserStatus(register.getUser());

				deleteConfirmedRegistration(register);

				return true;
			}

		} catch (PersistenceException e) {

			return false;

		}

		return false;

	}

	private Timestamp getCurrentDatePlusOneDay() {

		return new Timestamp(System.currentTimeMillis() + 24 * Constant.HOUR);

	}

	private Users populateUserObject(UserDto userDto) {

		Users user = new Users();

		user.setAdmin(userDto.isAdmin());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setStatus(userDto.getStatus());
		user.setUserName(userDto.getUserName());

		return user;
	}

}
