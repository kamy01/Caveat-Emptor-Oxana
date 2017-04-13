package repository.model;

import persistence.UserDto;

public interface UserDao {

	public UserDto findUserByEmail(String email);
	
	public UserDto findUserByUsername(String username);
	
}
