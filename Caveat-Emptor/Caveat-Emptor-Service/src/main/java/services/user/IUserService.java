package services.user;

import javax.ejb.Remote;

import exception.AccountException;
import model.UserDto;

@Remote
public interface IUserService {

	public UserDto getUserByUsername(String username) throws AccountException;
	
	public UserDto getUserByEmail(String email) throws AccountException;
	
}
