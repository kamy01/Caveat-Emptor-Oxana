package services.categories.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Category;
import exception.CaveatEmptorException;
import model.CategoryDto;
import repository.category.ICategoryRepository;
import services.categories.ICategory;
import services.mapper.DtoEntityMapper;

@Stateless
public class CategoryImpl implements ICategory {

	@PersistenceContext(unitName = "persistanceUnit")
	private EntityManager entityManager;

	@EJB
	ICategoryRepository iCategory;

	public List<CategoryDto> getAllCAtegories() throws CaveatEmptorException{

		ArrayList<Category> categories = (ArrayList<Category>)iCategory.getAllCAtegories(entityManager);

		ArrayList<CategoryDto> categoriesDto = new ArrayList<>();

		for (Category category : categories) {

			categoriesDto.add(DtoEntityMapper.createCategoryDto(category.getId(), category.getName(), category.getDescription(),
					category.getParentId()));

		}

		return (List<CategoryDto>)categoriesDto;
	}

	public void addNewCategory(CategoryDto categoryDto) throws CaveatEmptorException {

		Category category = DtoEntityMapper.createCategoryEntity(categoryDto);

		entityManager.persist(category);

	}

	public void removeCategory(CategoryDto parent, List<CategoryDto> children) throws CaveatEmptorException {

		List<Category> categories = changeCategoryDtoToCategory(children);

		updateParentIdForChildren(categories, parent.getParentId());

		removeCategory(DtoEntityMapper.createCategoryEntity(parent));

	}

	public List<Category> changeCategoryDtoToCategory(List<CategoryDto> categoriesDto) {

		ArrayList<Category> categories = new ArrayList<>();

		for (CategoryDto categoryDto : categoriesDto) {

			categories.add(DtoEntityMapper.createCategoryEntity(categoryDto));

		}

		return (List<Category>)categories;
	}

	public void removeCategory(Category category) {

		entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));

	}

	public void updateCategoryParent(Category category) {

		entityManager.merge(category);

	}

	private void updateParentIdForChildren(List<Category> children, Long grandpaId) {

		for (Category child : children) {

			child.setParentId(grandpaId);

			updateCategoryParent(child);

		}

	}

	public CategoryDto getLastAddedCategory() throws CaveatEmptorException {
		
		Category category = iCategory.getLastAddedCategory(entityManager);

		 return DtoEntityMapper.createCategoryDto(category.getId(), category.getName(), category.getDescription(),
					category.getParentId());
		
	}

}