package repository.category;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Category;

@Remote
public interface ICategoryRepository {

	public List<Category> getAllCAtegories(EntityManager entityManager);
	
}
