package repository.user;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Register;
import entities.Users;
import exception.AccountException;

@Remote
public interface IUserRepository {

	public Users findUserByEmail(String email, EntityManager entityManager) throws AccountException;
	
	public Users findUserByUsername(String username, EntityManager entityManager) throws AccountException;
	
	public Register findUserRegisterByUserId(Long id, EntityManager entityManager) throws AccountException;
	
	public Register findUserByKeyValue(String key, EntityManager entityManager) throws AccountException;
	
}
