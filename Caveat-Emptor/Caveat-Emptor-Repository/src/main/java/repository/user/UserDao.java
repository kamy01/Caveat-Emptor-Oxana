package repository.user;

import model.UserDto;

public interface UserDao {

	public UserDto findUserByEmail(String email);
	
	public UserDto findUserByUsername(String username);
	
}
