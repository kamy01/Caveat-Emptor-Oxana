package repository.category.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Category;
import repository.category.ICategoryRepository;

@Stateless
public class CategoryRepositoryImpl implements ICategoryRepository {

	@SuppressWarnings("unchecked")
	public List<Category> getAllCAtegories(EntityManager entityManager) {

		Query categoriesQuery = entityManager.createNamedQuery(Category.FIND_ALL_CATEGORIES);

		return categoriesQuery.getResultList();

	}

}
