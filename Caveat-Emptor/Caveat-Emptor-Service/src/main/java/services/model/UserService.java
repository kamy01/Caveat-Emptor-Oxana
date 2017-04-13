package services.model;

import persistence.UserDto;

public interface UserService {

	public UserDto getUser(String username);
}
