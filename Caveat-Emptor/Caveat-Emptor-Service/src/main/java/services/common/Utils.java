package services.common;



import entities.Users;
import model.UserDto;
import repository.user.AccountStatus;

public class Utils {
	
	public static UserDto createUserDto(Users user) {

		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUserName(user.getUserName());
		userDto.setEmail(user.getEmail());
		userDto.setAdmin(user.isAdmin());
		userDto.setPassword(user.getPassword());
		userDto.setStatus(user.getStatus());
		
		return userDto;

	}
	
	public static UserDto createUserDto(String firstName, String lastName, String userName, String email, String password){
		
		UserDto userDto = new UserDto();
		
		userDto.setAdmin(false);
		userDto.setEmail(email);
		userDto.setFirstName(firstName);
		userDto.setLastName(lastName);
		userDto.setUserName(userName);
		userDto.setPassword(password);
		userDto.setStatus(AccountStatus.PENDING.getValue());
		
		return userDto;
	}
}
