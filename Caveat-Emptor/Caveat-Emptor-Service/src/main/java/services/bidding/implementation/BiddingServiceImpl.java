package services.bidding.implementation;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Items;
import model.ItemDto;
import repository.bidding.IBidding;
import services.bidding.IBiddingService;
import services.mapper.DtoEntityMapper;

@Stateless
public class BiddingServiceImpl implements IBiddingService{
	
	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;
	
	@EJB
	IBidding iBidding;

	public List<ItemDto> findItemsByCategoryIds(List<Long> ids) {
		
		List<Items> items = iBidding.findItemsByCategoryIds(entityManager, ids);
		
		ArrayList<ItemDto> itemsDto = new ArrayList<>();

		for (Items item : items) {

			itemsDto.add(DtoEntityMapper.createItemDto(item.getId(), item.getName(), item.getDescription(), item.getImagePath(),
					item.getInitialPrice(), item.getOpeningDate(), item.getExpiringDate(), item.getBestBidValue(),
					item.getStatus(), DtoEntityMapper.createCategoryDto(item.getCategory().getId(), item.getCategory().getName(),
							item.getCategory().getDescription(), item.getCategory().getParentId()),  DtoEntityMapper.createUserDto(item.getUser())));

		}

		return itemsDto;
		
	}

}
