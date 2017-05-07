package repository.items;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Items;

@Remote
public interface IItemsRepository {

	public List<Items> findItemByUserId(EntityManager entityManager, Long id);
}
