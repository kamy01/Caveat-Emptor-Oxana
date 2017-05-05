package repository.items.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Items;
import repository.items.IItemsRepository;

@Stateless
public class ItemsRepository implements IItemsRepository{

	@SuppressWarnings("unchecked")
	public List<Items> finAllItems(EntityManager entityManager) {

		Query itemsQuery = entityManager.createNamedQuery(Items.FIND_ALL_ITEMS);

		return itemsQuery.getResultList();
		
	}

}
