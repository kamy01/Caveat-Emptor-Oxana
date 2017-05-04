package services.common;



import entities.Category;
import entities.Users;
import model.CategoryDto;
import model.UserDto;
import repository.user.AccountStatus;

public class Utils {
	
	public static UserDto createUserDto(Users user) {

		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUserName(user.getUserName());
		userDto.setEmail(user.getEmail());
		userDto.setAdmin(user.isAdmin());
		userDto.setPassword(user.getPassword());
		userDto.setStatus(user.getStatus());
		
		return userDto;

	}
	
	public static UserDto createUserDto(String firstName, String lastName, String userName, String email, String password){
		
		UserDto userDto = new UserDto();
		
		userDto.setAdmin(false);
		userDto.setEmail(email);
		userDto.setFirstName(firstName);
		userDto.setLastName(lastName);
		userDto.setUserName(userName);
		userDto.setPassword(password);
		userDto.setStatus(AccountStatus.PENDING.getValue());
		
		return userDto;
	}
	
	public static CategoryDto createCategoryDto(Long id, String name, String description, Long parentId){
		
		CategoryDto categoryDto = new CategoryDto();
		
		if(id != null){
			
			categoryDto.setId(id);
			
		}
		
		categoryDto.setName(name);
		categoryDto.setDescription(description);
		categoryDto.setParentId(parentId);
		
		return categoryDto;
		
	}
	
	public static Category createCategoryEntity(CategoryDto categoryDto){
		
		Category category = new Category();
		
		if(categoryDto.getId() != null){
			
			category.setId(categoryDto.getId());
			
		}
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setParentId(categoryDto.getParentId());
		
		return category;
	}
}
