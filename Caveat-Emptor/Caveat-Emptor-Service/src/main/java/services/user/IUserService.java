package services.user;

import javax.ejb.Remote;

import exception.CaveatEmptorException;
import model.UserDto;

@Remote
public interface IUserService {

	public UserDto getUserByUsername(String username) throws CaveatEmptorException;
	
	public UserDto getUserByEmail(String email) throws CaveatEmptorException;
	
}
