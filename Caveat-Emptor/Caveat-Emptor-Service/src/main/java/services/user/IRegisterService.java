package services.user;



import model.UserDto;
import javax.ejb.Remote;

import entities.Register;
import entities.Users;

@Remote
public interface IRegisterService {

	public boolean isUserRegistered(UserDto userDto, String key);
	
	public Register findUserByKey(String key);
	
	public void deleteConfirmedRegistration(Register register);
	
	public boolean isAccountActive(String key);
	
	public void updateUserStatus(Users user);
	
	public void deleteRowFromRegisterTable(Register register);
	
}
