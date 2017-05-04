package services.categories.implementation;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Category;
import exception.AccountException;
import model.CategoryDto;
import repository.category.ICategoryRepository;
import services.categories.ICategory;
import services.common.Utils;

@Stateless
public class CategoryImpl implements ICategory {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@EJB
	ICategoryRepository iCategory;

	public ArrayList<CategoryDto> getAllCAtegories() {

		ArrayList<Category> categories = iCategory.getAllCAtegories(entityManager);

		ArrayList<CategoryDto> categoriesDto = new ArrayList<>(categories.size());

		for (Category category : categories) {

			categoriesDto.add(Utils.createCategoryDto(category.getId(), category.getName(), category.getDescription(),
					category.getParentId()));

		}

		return categoriesDto;
	}

	public void addNewCategory(CategoryDto categoryDto) throws AccountException {

		Category category = Utils.createCategoryEntity(categoryDto);

		entityManager.persist(category);

	}

	public void removeCategory(CategoryDto parent, ArrayList<CategoryDto> children) throws AccountException {

		ArrayList<Category> categories = changeCategoryDtoToCategory(children);

		updateParentIdForChildren(categories, parent.getParentId());

		removeCategory(Utils.createCategoryEntity(parent));

	}

	public ArrayList<Category> changeCategoryDtoToCategory(ArrayList<CategoryDto> categoriesDto) {

		ArrayList<Category> categories = new ArrayList<>();

		for (CategoryDto categoryDto : categoriesDto) {

			categories.add(Utils.createCategoryEntity(categoryDto));

		}

		return categories;
	}

	public void removeCategory(Category category) {

		entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));

	}

	public void updateCategoryParent(Category category) {

		entityManager.merge(category);

	}

	private void updateParentIdForChildren(ArrayList<Category> children, Long grandpaId) {

		for (Category child : children) {

			child.setParentId(grandpaId);

			updateCategoryParent(child);

		}

	}

}