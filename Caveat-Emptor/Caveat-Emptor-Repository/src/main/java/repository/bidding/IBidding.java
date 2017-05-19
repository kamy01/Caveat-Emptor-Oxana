package repository.bidding;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Items;

@Remote
public interface IBidding {

	public List<Items> findItemsByCategoryIds(EntityManager entityManager, List<Long> ids);
	
}
