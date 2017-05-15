package repository.user;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Register;
import entities.Users;
import exception.CaveatEmptorException;

@Remote
public interface IUserRepository {

	public Users findUserByEmail(String email, EntityManager entityManager) throws CaveatEmptorException;
	
	public Users findUserByUsername(String username, EntityManager entityManager) throws CaveatEmptorException;
	
	public Register findUserRegisterByUserId(Long id, EntityManager entityManager) throws CaveatEmptorException;
	
	public Register findUserByKeyValue(String key, EntityManager entityManager) throws CaveatEmptorException;
	
}
