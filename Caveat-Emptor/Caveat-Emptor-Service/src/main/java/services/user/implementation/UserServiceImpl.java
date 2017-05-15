package services.user.implementation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Users;
import exception.CaveatEmptorException;
import model.UserDto;
import repository.user.IUserRepository;
import services.mapper.DtoEntityMapper;
import services.user.IUserService;

@Stateless
public class UserServiceImpl implements IUserService {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@EJB
	IUserRepository iUserRepository;

	public UserDto getUserByUsername(String username) throws CaveatEmptorException {

			Users user = iUserRepository.findUserByUsername(username, entityManager);

			return DtoEntityMapper.createUserDto(user);

	}

	public UserDto getUserByEmail(String email) throws CaveatEmptorException{

			Users user = iUserRepository.findUserByEmail(email, entityManager);
			return DtoEntityMapper.createUserDto(user);

	}

}
