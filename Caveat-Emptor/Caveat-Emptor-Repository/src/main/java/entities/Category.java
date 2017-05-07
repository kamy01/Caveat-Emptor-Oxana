package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "Category.findAllCategories", query = "SELECT categories from Category categories"),
	@NamedQuery(name = "Category.findCategoriesByParentId", query = "SELECT categories from Category categories WHERE categories.parentId = :parentId"),})
@Table(name = "categories")
public class Category implements Serializable{

	private static final long serialVersionUID = -5172413896612841556L;

	public static final String FIND_ALL_CATEGORIES = "Category.findAllCategories";
	public static final String FIND_CATEGORIES_BY_PARENT_ID = "Category.findCategoriesByParentId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PARENT_ID")
	private Long parentId;
	
	@OneToMany(mappedBy="category", fetch = FetchType.LAZY)
	private List<Items> items;
	
	

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
