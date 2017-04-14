package services.user;

import persistence.UserDto;

public interface UserService {

	public UserDto getUser(String username);
}
