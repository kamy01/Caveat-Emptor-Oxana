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
	public List<Items> findItemByUserId(EntityManager entityManager, Long id) {

		Query itemsQuery = entityManager.createNamedQuery(Items.FIND_ITEMS_BY_USER_ID);

		itemsQuery.setParameter("userId", id);
		
		return itemsQuery.getResultList();
		
	}

}
