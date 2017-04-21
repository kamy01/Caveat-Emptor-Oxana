package repository.user.implementation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Register;
import entities.Users;
import repository.user.IUserDao;

@Stateless
public class UserDaoImpl implements IUserDao {

	public Users findUserByEmail(String email, EntityManager entityManager) {

		try {

			Query userQuery = entityManager.createNamedQuery(Users.FIND_USER_BY_EMAIL);

			userQuery.setParameter("email", email);

			return (Users) userQuery.getSingleResult();

		} catch (NoResultException e) {

			return null;

		}

	}

	public Users findUserByUsername(String username, EntityManager entityManager) {

		try {

			Query userQuery = entityManager.createNamedQuery(Users.FIND_USER_BY_USERNAME);

			userQuery.setParameter("username", username);

			return (Users) userQuery.getSingleResult();

		} catch (NoResultException e) {

			return null;

		}

	}

	public Register findUserRegisterByUserId(Long id, EntityManager entityManager) {

		try {

			Query registerQuery = entityManager.createNamedQuery(Register.FIND_USER_REGISER_BY_USER_ID);

			registerQuery.setParameter("userId", id);

			return (Register) registerQuery.getSingleResult();

		} catch (NoResultException e) {

			return null;

		}

	}

	public Register findUserByKeyValue(String key, EntityManager entityManager) {

		try {

			Query registerQuery = entityManager.createNamedQuery(Register.FIND_USER_REGISER_BY_KEY_VALUE);
			
			registerQuery.setParameter("key", key);

			return (Register) registerQuery.getSingleResult();

		} catch (NoResultException e) {

			return null;

		}

	}

}
