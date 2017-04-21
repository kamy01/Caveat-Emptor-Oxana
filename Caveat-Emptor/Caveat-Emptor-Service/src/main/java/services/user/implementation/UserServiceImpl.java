package services.user.implementation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Users;
import model.UserDto;
import repository.user.IUserDao;
import services.common.Utils;
import services.user.IUserService;

@Stateless
public class UserServiceImpl implements IUserService {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager _entityManager;

	@EJB
	IUserDao iUserDao;

	public UserDto getUserByUsername(String username) {

		Utils util = new Utils();

		Users user = iUserDao.findUserByUsername(username, _entityManager);
		
		if(user != null )
			return util.createUserDto(user);
		
		return null;

	}

	public UserDto getUserByEmail(String email) {

		Utils util = new Utils();

		Users user = iUserDao.findUserByEmail(email, _entityManager);

		if(user != null )
			return util.createUserDto(user);
		
		return null;

	}

}
