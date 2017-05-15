package services.items.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Items;
import model.ItemDto;
import repository.items.IItemsRepository;
import services.items.IItemsService;
import services.mapper.DtoEntityMapper;

@Stateless
public class ItemsImpl implements IItemsService {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@EJB
	IItemsRepository iItemRepository;

	public List<ItemDto> getItemsByUserId(Long id) {

		ArrayList<Items> items = (ArrayList<Items>) iItemRepository.findItemByUserId(entityManager, id);

		ArrayList<ItemDto> itemsDto = new ArrayList<>();

		for (Items item : items) {

			itemsDto.add(DtoEntityMapper.createItemDto(item.getId(), item.getName(), item.getDescription(), item.getImagePath(),
					item.getInitialPrice(), item.getOpeningDate(), item.getExpiringDate(), item.getBestBidValue(),
					item.getStatus(), DtoEntityMapper.createCategoryDto(item.getCategory().getId(), item.getCategory().getName(),
							item.getCategory().getDescription(), item.getCategory().getParentId()),  DtoEntityMapper.createUserDto(item.getUser())));

		}

		return (List<ItemDto>) itemsDto;
	}
	
	public void addNewItem(ItemDto item){
		
		Items newItem = DtoEntityMapper.createItemEntity(item);
		
		if(newItem != null){
			
			entityManager.merge(newItem);
			
		}
		
	}

}
