package repository.category;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entities.Category;

@Remote
public interface ICategoryRepository {

	public ArrayList<Category> getAllCAtegories(EntityManager entityManager);
	
}
