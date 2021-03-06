package services.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entities.Category;
import entities.Items;
import entities.Users;
import model.CategoryDto;
import model.ItemDto;
import model.UserDto;
import repository.user.AccountStatus;

public class DtoEntityMapper {

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

	public static UserDto createUserDto(String firstName, String lastName, String userName, String email,
			String password) {

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
	
	public static Users createUserEntity(UserDto user){
		
		Users userEntity = new Users();
		
		if(user.getId() != null){
			userEntity.setId(user.getId());
		}
		userEntity.setAdmin(false);
		userEntity.setEmail(user.getEmail());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setPassword(user.getPassword());
		userEntity.setStatus(user.getStatus());
		userEntity.setUserName(user.getUserName());
		
		return userEntity;
		
	}

	public static CategoryDto createCategoryDto(Long id, String name, String description, Long parentId) {

		CategoryDto categoryDto = new CategoryDto();

		if (id != null) {

			categoryDto.setId(id);

		}

		categoryDto.setName(name);
		categoryDto.setDescription(description);
		categoryDto.setParentId(parentId);

		return categoryDto;

	}

	public static Category createCategoryEntity(CategoryDto categoryDto) {

		Category category = new Category();

		if (categoryDto.getId() != null) {

			category.setId(categoryDto.getId());

		}
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setParentId(categoryDto.getParentId());

		return category;
	}

	public static ItemDto createItemDto(Long id, String name, String description, String imagePath, Double initialPrice,
			Timestamp openingDate, Timestamp expiringDate, Long bestBidValue, String status, CategoryDto category, UserDto user) {

		ItemDto itemDto = new ItemDto();
		
		itemDto.setBestBidValue(bestBidValue);
		itemDto.setCategory(category);
		itemDto.setDescription(description);
		itemDto.setExpiringDate(expiringDate);
		itemDto.setId(id);
		itemDto.setImagePath(imagePath);
		itemDto.setInitialPrice(initialPrice);
		itemDto.setName(name);
		itemDto.setOpeningDate(openingDate);
		itemDto.setStatus(status);
		itemDto.setUser(user);
		
		return itemDto;
	}
	
	public static Items createItemEntity(ItemDto item){
		
		Items itemEntity = new Items();
		
		if(item.getId() != null) {
			itemEntity.setId(item.getId());
		}
		itemEntity.setBestBidValue(item.getBestBidValue());
		itemEntity.setCategory(DtoEntityMapper.createCategoryEntity(item.getCategory()));
		itemEntity.setDescription(item.getDescription());
		itemEntity.setExpiringDate(item.getExpiringDate());
		itemEntity.setImagePath(null);
		itemEntity.setInitialPrice(item.getInitialPrice());
		itemEntity.setName(item.getName());
		itemEntity.setOpeningDate(item.getOpeningDate());
		itemEntity.setStatus(item.getStatus());
		itemEntity.setUser(DtoEntityMapper.createUserEntity(item.getUser()));
		
		return itemEntity;
		
	}
	
	public static List<ItemDto> convertItemEntityToDto(List<Items> itemEntityList){
		
		List<ItemDto> dtoList = new ArrayList<ItemDto>();
		
		for(Items item: itemEntityList){
			
			dtoList.add(createItemDto(item.getId(), item.getName(), item.getDescription(), item.getImagePath(),
					item.getInitialPrice(), item.getOpeningDate(), item.getExpiringDate(), item.getBestBidValue(),
					item.getStatus(), DtoEntityMapper.createCategoryDto(item.getCategory().getId(), item.getCategory().getName(),
							item.getCategory().getDescription(), item.getCategory().getParentId()),  DtoEntityMapper.createUserDto(item.getUser())));
			
		}
		
		return dtoList;
	}
}
