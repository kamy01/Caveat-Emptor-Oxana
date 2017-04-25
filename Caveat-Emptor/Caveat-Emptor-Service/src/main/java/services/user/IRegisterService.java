package services.user;



import model.UserDto;
import javax.ejb.Remote;

import entities.Register;
import entities.Users;
import exception.AccountException;

@Remote
public interface IRegisterService {

	public void registerNewUser(UserDto userDto, String key) throws AccountException;
	
	public Register findUserByKey(String key) throws AccountException;
	
	public void deleteConfirmedRegistration(Register register);
	
	public void activateAccount(String key) throws AccountException;
	
	public void updateUserStatus(Users user);
	
	public void deleteRowFromRegisterTable(Register register);
	
}
