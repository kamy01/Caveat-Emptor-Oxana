package repository.user.implementation;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.Register;
import entities.Users;
import exception.CaveatEmptorException;
import repository.user.IUserRepository;

@Stateless
public class UserRepositoryImpl implements IUserRepository {
	
	private static final String FIND_USER_BY_EMAIL = Users.FIND_USER_BY_EMAIL;
	private static final String FIND_USER_BY_USERNAME = Users.FIND_USER_BY_USERNAME;
	private static final String FIND_USER_REGISER_BY_USER_ID = Register.FIND_USER_REGISER_BY_USER_ID;
	private static final String FIND_USER_REGISER_BY_KEY_VALUE = Register.FIND_USER_REGISER_BY_KEY_VALUE;

	public Users findUserByEmail(String email, EntityManager entityManager) throws CaveatEmptorException {

		try {

			Query userQuery = entityManager.createNamedQuery(FIND_USER_BY_EMAIL);

			userQuery.setParameter("email", email);

			return (Users) userQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new CaveatEmptorException();
		}

	}

	public Users findUserByUsername(String username, EntityManager entityManager) throws CaveatEmptorException {

		try {

			Query userQuery = entityManager.createNamedQuery(FIND_USER_BY_USERNAME);

			userQuery.setParameter("username", username);

			return (Users) userQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new CaveatEmptorException();

		}

	}

	public Register findUserRegisterByUserId(Long id, EntityManager entityManager) throws CaveatEmptorException {

		try {

			Query registerQuery = entityManager.createNamedQuery(FIND_USER_REGISER_BY_USER_ID);

			registerQuery.setParameter("userId", id);

			return (Register) registerQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new CaveatEmptorException();

		}

	}

	public Register findUserByKeyValue(String key, EntityManager entityManager) throws CaveatEmptorException {

		try {

			Query registerQuery = entityManager.createNamedQuery(FIND_USER_REGISER_BY_KEY_VALUE);

			registerQuery.setParameter("key", key);

			return (Register) registerQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new CaveatEmptorException();

		}

	}

}
