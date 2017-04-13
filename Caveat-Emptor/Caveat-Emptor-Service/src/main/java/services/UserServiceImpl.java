package services;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import persistence.UserDto;
import repository.model.UserDao;
import services.model.UserService;

@Stateless
@Remote(UserService.class)
public class UserServiceImpl implements UserService {

	@EJB
	UserDao userDao;

	public UserDto getUser(String username) {
		return userDao.findUserByUsername(username);
	}
}
