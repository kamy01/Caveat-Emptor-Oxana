package services.user.implementation;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import model.UserDto;
import repository.user.UserDao;
import services.user.UserService;


@Stateless
@Remote(UserService.class)
public class UserServiceImpl implements UserService {

	@EJB
	UserDao userDao;

	public UserDto getUser(String username) {
		return userDao.findUserByUsername(username);
	}
}
