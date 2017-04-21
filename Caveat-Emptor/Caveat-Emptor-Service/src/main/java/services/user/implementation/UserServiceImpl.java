package services.user.implementation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Users;
import model.UserDto;
import repository.user.IUserRepository;
import services.common.Utils;
import services.user.IUserService;

@Stateless
public class UserServiceImpl implements IUserService {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager _entityManager;

	@EJB
	IUserRepository iUserRepository;

	public UserDto getUserByUsername(String username) {

		Utils util = new Utils();

		Users user = iUserRepository.findUserByUsername(username, _entityManager);
		
		if(user != null )
			return util.createUserDto(user);
		
		return null;

	}

	public UserDto getUserByEmail(String email) {

		Utils util = new Utils();

		Users user = iUserRepository.findUserByEmail(email, _entityManager);

		if(user != null )
			return util.createUserDto(user);
		
		return null;

	}

}
