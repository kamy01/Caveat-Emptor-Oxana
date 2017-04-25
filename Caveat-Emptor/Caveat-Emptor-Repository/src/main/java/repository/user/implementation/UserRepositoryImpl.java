package repository.user.implementation;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.Register;
import entities.Users;
import exception.AccountException;
import repository.user.IUserRepository;

@Stateless
public class UserRepositoryImpl implements IUserRepository {

	public Users findUserByEmail(String email, EntityManager entityManager) throws AccountException {

		try {

			Query userQuery = entityManager.createNamedQuery(Users.FIND_USER_BY_EMAIL);

			userQuery.setParameter("email", email);

			return (Users) userQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new AccountException();
		}

	}

	public Users findUserByUsername(String username, EntityManager entityManager) throws AccountException {

		try {

			Query userQuery = entityManager.createNamedQuery(Users.FIND_USER_BY_USERNAME);

			userQuery.setParameter("username", username);

			return (Users) userQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new AccountException();

		}

	}

	public Register findUserRegisterByUserId(Long id, EntityManager entityManager) throws AccountException {

		try {

			Query registerQuery = entityManager.createNamedQuery(Register.FIND_USER_REGISER_BY_USER_ID);

			registerQuery.setParameter("userId", id);

			return (Register) registerQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new AccountException();

		}

	}

	public Register findUserByKeyValue(String key, EntityManager entityManager) throws AccountException {

		try {

			Query registerQuery = entityManager.createNamedQuery(Register.FIND_USER_REGISER_BY_KEY_VALUE);

			registerQuery.setParameter("key", key);

			return (Register) registerQuery.getSingleResult();

		} catch (PersistenceException e) {

			throw new AccountException();

		}

	}

}
