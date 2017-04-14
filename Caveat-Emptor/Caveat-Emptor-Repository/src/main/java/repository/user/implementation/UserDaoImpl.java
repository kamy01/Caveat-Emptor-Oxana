package repository.user.implementation;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.User;
import model.UserDto;
import repository.user.UserDao;

@Stateless
@Remote(UserDao.class)
public class UserDaoImpl implements UserDao{

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	public UserDto findUserByEmail(String email) {
		
		Query userQuery = entityManager.createNamedQuery("User.findByEmail");
		
		userQuery.setParameter("email", email);
		
		User user = (User)userQuery.getSingleResult();
		
		return createUserDto(user);
		
	}

	public UserDto findUserByUsername(String username) {
		
		try{
			
			Query userQuery = entityManager.createNamedQuery("User.findByUsername");
			
			userQuery.setParameter("username", username);
			
			User user = (User)userQuery.getSingleResult();
			
			return createUserDto(user);
			
		}catch(NoResultException e){
			
			return null;
			
		}
		
	}

	private UserDto createUserDto(User user) {
		
		UserDto userDto = new UserDto();

		userDto.setUserId(user.getUserId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUserName(user.getUserName());
		userDto.setEmail(user.getEmail());
		userDto.setAdmin(user.isAdmin());
		userDto.setConfirmationDate(user.getConfirmationDate());
		userDto.setPassword(user.getPassword());
		
		return userDto;
		
	}
}
