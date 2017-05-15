package services.user;



import model.UserDto;
import javax.ejb.Remote;

import entities.Register;
import entities.Users;
import exception.CaveatEmptorException;

@Remote
public interface IRegisterService {

	public void registerNewUser(UserDto userDto, String key) throws CaveatEmptorException;
	
	public Register findUserByKey(String key) throws CaveatEmptorException;
	
	public void deleteConfirmedRegistration(Register register);
	
	public void activateAccount(String key) throws CaveatEmptorException;
	
	public void updateUserStatus(Users user);
	
	public void deleteRowFromRegisterTable(Register register);
	
}
