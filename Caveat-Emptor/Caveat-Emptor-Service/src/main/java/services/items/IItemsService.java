package services.items;

import java.util.List;

import javax.ejb.Remote;

import model.ItemDto;

@Remote
public interface IItemsService {

	public List<ItemDto> getItemsByUserId(Long id);
}
