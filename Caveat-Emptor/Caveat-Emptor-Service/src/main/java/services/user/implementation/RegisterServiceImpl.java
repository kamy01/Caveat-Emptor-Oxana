package services.user.implementation;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Register;
import entities.Users;
import exception.CaveatEmptorException;
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
	
	public static final long HOUR = 3600*1000;

	public void registerNewUser(UserDto userDto, String key) throws CaveatEmptorException {

		Users user = populateUserObject(userDto);
		Register register = new Register();

		register.setConfirmationDate(getCurrentDatePlusOneDay());
		register.setKey(key);
		register.setUser(user);

		try {
			
			entityManager.persist(register);
			
		} catch (PersistenceException e) {
			
			throw new CaveatEmptorException();
			
		}

	}

	public Register findUserByKey(String key) throws CaveatEmptorException {

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

	public void activateAccount(String key) throws CaveatEmptorException {

		Register register = findUserByKey(key);

		if (register != null) {

			register.getUser().setStatus(AccountStatus.ACTIVE.getValue());

			updateUserStatus(register.getUser());

			deleteConfirmedRegistration(register);
		}
	}

	private Timestamp getCurrentDatePlusOneDay() {

		return new Timestamp(System.currentTimeMillis() + 24 * HOUR);

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
