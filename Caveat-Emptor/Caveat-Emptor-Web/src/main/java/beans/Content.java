package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import FacesMessages.MyFacesMessage;
import constants.Constant;
import exception.AccountException;
import model.CategoryDto;
import services.categories.ICategory;
import services.common.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

@Named
@RequestScoped
public class Content implements Serializable {

	private static final long serialVersionUID = 4227840304921271358L;

	@Inject
	Tree tree;
	
	@EJB
	ICategory iCategory;

	private MenuModel model;
	private DefaultMenuItem item;
	private String name;
	private String description;
	private String searchValue;
	private ArrayList<CategoryDto> breadcrumb;
	private ArrayList<CategoryDto> categorySearchResult;

	@PostConstruct
	public void init() {
		
		initializeBreadcrumb();
		
	}

	public ArrayList<CategoryDto> getCategorySearchResult() {
		return categorySearchResult;
	}

	public void setCategorySearchResult(ArrayList<CategoryDto> categorySearchResult) {
		this.categorySearchResult = categorySearchResult;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public MenuModel getModel() {
		return model;
	}

	private void initializeBreadcrumb(){
		
		model = new DefaultMenuModel();
		item = new DefaultMenuItem("External");
		item.setUrl("http://www.primefaces.org");
		item.setIcon("ui-icon-home");
		model.addElement(item);
		
	}
	public void addBreadcrumbMenuItem(MenuModel model) {

		if(isSelectedNode(tree)){
			
			breadcrumb = new ArrayList<>();
			
			getNodeParents(tree.getSelectedNode());
			
			Collections.reverse(breadcrumb);
			
			for(CategoryDto parent: breadcrumb){
				
				item = new DefaultMenuItem(parent.getName());
				model.addElement(item);
				
			}
			
			item = new DefaultMenuItem(name);
			model.addElement(item);
		}
		
	}

	private void getNodeParents(TreeNode selectedNode){

		CategoryDto selectedNodeData = (CategoryDto)selectedNode.getData();
		
		if(selectedNodeData.getParentId() != null){
			
			breadcrumb.add((CategoryDto)selectedNode.getParent().getData());
			getNodeParents(selectedNode.getParent());
			
		}
		
	}
	
	public void addNewCategory() {

		if (isFilledNewCotegory()) {

			CategoryDto categoryDto = new CategoryDto();

			if (isSelectedNode(tree)) {

				categoryDto = (CategoryDto) tree.getSelectedNode().getData();

			}

			CategoryDto newCategory = Utils.createCategoryDto(null, name, description, categoryDto.getId());

			try {

				iCategory.addNewCategory(newCategory);

				tree.initializeTree();
				
				resetContentFields();

			} catch (AccountException e) {

				System.out.println("persistence error");

			}

		}

	}

	public void removeCategory() {

		try {

			if (isSelectedNode(tree)) {

				CategoryDto parentNode = (CategoryDto) tree.getSelectedNode().getData();

				ArrayList<CategoryDto> children = getTreeChildren(tree.getSelectedNode());

				iCategory.removeCategory(parentNode, children);

				tree.initializeTree();
				
				resetContentFields();

			} else {

				MyFacesMessage.addMessage(FacesMessage.SEVERITY_WARN, Constant.CATEGORY_WARN, Constant.DELETE_NO_CATEGORY);

			}

		} catch (AccountException e) {

			System.out.println("persistence error");

		}
	}

	private ArrayList<CategoryDto> getTreeChildren(TreeNode selectedNode) {

		ArrayList<CategoryDto> children = new ArrayList<>();

		for (TreeNode child : selectedNode.getChildren()) {

			children.add((CategoryDto) child.getData());

		}

		return children;
	}

	private boolean isFilledNewCotegory() {

		return (!name.isEmpty() && !description.isEmpty()) ? true : false;
	}

	private boolean isSelectedNode(Tree tree) {

		return tree.getSelectedNode() != null ? true : false;

	}
	
	public void onNodeSelect(NodeSelectEvent event) {

		this.name = ((CategoryDto)event.getTreeNode().getData()).getName();
		
		this.description = ((CategoryDto)event.getTreeNode().getData()).getDescription();
		
		addBreadcrumbMenuItem(model);
		
	}
	
	public void resetContentFields(){
		
		this.name = null;
		this.description = null;
		
		initializeBreadcrumb();
	}
	
	public void searchForCategory(AjaxBehaviorEvent e){
		
		if(!searchValue.isEmpty()){
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlg1').show();");
			
			categorySearchResult = new ArrayList<>();
			
			ArrayList<CategoryDto> categories = tree.getCategories();
			
			for(CategoryDto category: categories){
				
				if(category.getName().toLowerCase().equals(searchValue.toLowerCase())){
					
					categorySearchResult.add(category);
					
				}
				
			}
			
			searchValue = null;
			
		}
		
	}
}