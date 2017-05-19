package services.bidding;


import java.util.List;

import javax.ejb.Remote;

import model.ItemDto;

@Remote
public interface IBiddingService {

	public List<ItemDto> findItemsByCategoryIds(List<Long> ids);
}
