package repository.bidding.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Items;
import repository.bidding.IBidding;

@Stateless
public class BiddingImpl implements IBidding{

	public static final String FIND_ITEMS_BY_CATEGORY_IDS = Items.FIND_ITEMS_BY_CATEGORY_IDS;
			
	@SuppressWarnings("unchecked")
	public List<Items> findItemsByCategoryIds(EntityManager entityManager, List<Long> ids) {
		
		Query categoriesQuery = entityManager.createNamedQuery(FIND_ITEMS_BY_CATEGORY_IDS);
		
		categoriesQuery.setParameter("ids", ids);
		
		return categoriesQuery.getResultList();
		
	}

}
