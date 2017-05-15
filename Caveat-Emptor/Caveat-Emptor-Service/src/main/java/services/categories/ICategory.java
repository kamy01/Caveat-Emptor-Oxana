package services.categories;

import java.util.List;

import javax.ejb.Remote;

import exception.CaveatEmptorException;
import model.CategoryDto;

@Remote
public interface ICategory {

	public List<CategoryDto> getAllCAtegories() throws CaveatEmptorException;
	public CategoryDto getLastAddedCategory() throws CaveatEmptorException;
	public void addNewCategory(CategoryDto categoryDto) throws CaveatEmptorException;
	public void removeCategory(CategoryDto categoryDto, List<CategoryDto> children) throws CaveatEmptorException;
	
}