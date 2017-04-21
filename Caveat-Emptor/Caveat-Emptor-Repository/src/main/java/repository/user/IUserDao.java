package repository.user;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Register;
import entities.Users;

@Remote
public interface IUserDao {

	public Users findUserByEmail(String email, EntityManager entityManager);
	
	public Users findUserByUsername(String username, EntityManager entityManager);
	
	public Register findUserRegisterByUserId(Long id, EntityManager entityManager);
	
	public Register findUserByKeyValue(String key, EntityManager entityManager);
	
}
