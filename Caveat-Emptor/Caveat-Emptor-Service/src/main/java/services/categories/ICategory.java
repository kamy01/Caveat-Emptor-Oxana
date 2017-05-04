package services.categories;

import java.util.ArrayList;

import javax.ejb.Remote;

import exception.AccountException;
import model.CategoryDto;

@Remote
public interface ICategory {

	public ArrayList<CategoryDto> getAllCAtegories();
	public void addNewCategory(CategoryDto categoryDto) throws AccountException;
	public void removeCategory(CategoryDto categoryDto, ArrayList<CategoryDto> children) throws AccountException;
	
}