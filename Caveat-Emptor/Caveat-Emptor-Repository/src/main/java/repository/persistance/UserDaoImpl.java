package repository.persistance;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import entities.User;
import persistence.UserDto;
import repository.model.UserDao;

@Stateless
@Remote(UserDao.class)
public class UserDaoImpl implements UserDao {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager em;

	public UserDto findUserByEmail(String email) {

		User user = (User) em.createNamedQuery("User.findByEmail").setParameter("email", email).getSingleResult();

		UserDto userDto = createUserDto(user);
		return userDto;
	}

	public UserDto findUserByUsername(String username) {
		
		try{
			
			return createUserDto( (User) em.createNamedQuery("User.findByUsername").setParameter("username", username).getSingleResult());
			
		}catch(NoResultException e){
			return null;
		}
		
	}

	private UserDto createUserDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setUser_id(user.getUser_id());
		userDto.setFirstname(user.getFirstname());
		userDto.setLasname(user.getLastname());
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		userDto.setAdmin(user.isAdmin());
		userDto.setConfirmationDate(user.getConfirmationDate());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
