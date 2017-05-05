package services.categories;

import java.util.List;

import javax.ejb.Remote;

import exception.AccountException;
import model.CategoryDto;

@Remote
public interface ICategory {

	public List<CategoryDto> getAllCAtegories();
	public void addNewCategory(CategoryDto categoryDto) throws AccountException;
	public void removeCategory(CategoryDto categoryDto, List<CategoryDto> children) throws AccountException;
	
}