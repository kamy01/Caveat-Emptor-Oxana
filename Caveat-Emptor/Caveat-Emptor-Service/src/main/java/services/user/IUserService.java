package services.user;

import javax.ejb.Remote;

import model.UserDto;

@Remote
public interface IUserService {

	public UserDto getUserByUsername(String username);
	
	public UserDto getUserByEmail(String email);
	
}
