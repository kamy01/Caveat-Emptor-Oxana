package services.user.implementation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Users;
import exception.AccountException;
import model.UserDto;
import repository.user.IUserRepository;
import services.common.Utils;
import services.user.IUserService;

@Stateless
public class UserServiceImpl implements IUserService {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@EJB
	IUserRepository iUserRepository;

	public UserDto getUserByUsername(String username) throws AccountException {

			Users user = iUserRepository.findUserByUsername(username, entityManager);

			return Utils.createUserDto(user);

	}

	public UserDto getUserByEmail(String email) throws AccountException{

			Users user = iUserRepository.findUserByEmail(email, entityManager);
			return Utils.createUserDto(user);

	}

}
