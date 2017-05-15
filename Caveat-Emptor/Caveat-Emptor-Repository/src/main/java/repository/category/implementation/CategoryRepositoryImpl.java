package repository.category.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Category;
import repository.category.ICategoryRepository;

@Stateless
public class CategoryRepositoryImpl implements ICategoryRepository {
	
	private static final String FIND_ALL_CATEGORIES = Category.FIND_ALL_CATEGORIES;
	private static final String FIND_LAST_ADDED_CATEGORY = Category.FIND_LAST_ADDED_CATEGORY;

	@SuppressWarnings("unchecked")
	public List<Category> getAllCAtegories(EntityManager entityManager) {

		Query categoriesQuery = entityManager.createNamedQuery(FIND_ALL_CATEGORIES);

		return categoriesQuery.getResultList();

	}


	public Category getLastAddedCategory(EntityManager entityManager) {
		
		Query categoryQuery = entityManager.createNamedQuery(FIND_LAST_ADDED_CATEGORY);
		
		return (Category)categoryQuery.setMaxResults(1).getResultList().get(0);
		
	}

}
