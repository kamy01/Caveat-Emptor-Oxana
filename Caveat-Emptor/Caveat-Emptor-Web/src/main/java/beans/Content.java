package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

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
import java.util.List;

@ManagedBean(name = "content")
@ViewScoped
public class Content implements Serializable {

	private static final long serialVersionUID = 4227840304921271358L;

	@ManagedProperty("#{treeBasicView}")
	Tree tree;

	@EJB
	ICategory iCategory;
	
	private String pageUrl;

	private MenuModel model;
	private DefaultMenuItem item;
	private String name;
	private String description;
	private String searchValue;
	private List<CategoryDto> breadcrumb;
	private List<CategoryDto> categorySearchResult;
	private List<TreeNode> children;

	@PostConstruct
	public void init() {

		initializeBreadcrumb();
		pageUrl = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		
		System.out.println(pageUrl);

	}

	public List<CategoryDto> getCategorySearchResult() {
		return categorySearchResult;
	}

	public void setCategorySearchResult(List<CategoryDto> categorySearchResult) {
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

	private void initializeBreadcrumb() {

		model = new DefaultMenuModel();
		item = new DefaultMenuItem("External");
		item.setUrl("http://localhost:8080/Caveat-Emptor-Web-0.0.1-SNAPSHOT"+ getViewUrl());
		item.setIcon("ui-icon-home");
		model.addElement(item);

	}
	
	private String getViewUrl(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId();
	}

	public void addBreadcrumbMenuItem(MenuModel model) {

		if (isSelectedNode(tree)) {

			breadcrumb = new ArrayList<>();

			getNodeParents(tree.getSelectedNode());

			Collections.reverse(breadcrumb);

			for (CategoryDto parent : breadcrumb) {

				addElementToBreadcrumb(parent);

			}

			addElementToBreadcrumb((CategoryDto)tree.getSelectedNode().getData());
		}

	}

	private void addElementToBreadcrumb(CategoryDto category) {

		item = new DefaultMenuItem(category.getName());
		item.setUrl("#");
		item.setId(category.getId().toString());
		model.addElement(item);

	}

	private void getNodeParents(TreeNode selectedNode) {

		CategoryDto selectedNodeData = (CategoryDto) selectedNode.getData();

		if (selectedNodeData.getParentId() != null) {

			breadcrumb.add((CategoryDto) selectedNode.getParent().getData());
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

				List<CategoryDto> children = getTreeChildren(tree.getSelectedNode());

				iCategory.removeCategory(parentNode, children);

				tree.initializeTree();

				resetContentFields();

			} else {

				MyFacesMessage.addMessage(FacesMessage.SEVERITY_WARN, Constant.CATEGORY_WARN,
						Constant.DELETE_NO_CATEGORY);

			}

		} catch (AccountException e) {

			System.out.println("persistence error");

		}
	}

	private List<CategoryDto> getTreeChildren(TreeNode selectedNode) {

		ArrayList<CategoryDto> children = new ArrayList<>();

		for (TreeNode child : selectedNode.getChildren()) {

			children.add((CategoryDto) child.getData());

		}

		return (List<CategoryDto>)children;
	}

	private boolean isFilledNewCotegory() {

		return (!name.isEmpty() && !description.isEmpty()) ? true : false;
	}

	private boolean isSelectedNode(Tree tree) {

		return tree.getSelectedNode() != null ? true : false;

	}

	public void onNodeSelect(NodeSelectEvent event) {
		
		initializeBreadcrumb();

		initializeContextFields(event.getTreeNode());
		
	}

	private void initializeContextFields(TreeNode node) {

		this.name = ((CategoryDto) node.getData()).getName();

		this.description = ((CategoryDto) node.getData()).getDescription();

		addBreadcrumbMenuItem(model);

	}

	public void resetContentFields() {

		this.name = null;
		this.description = null;

		initializeBreadcrumb();
	}

	public void searchForCategory(AjaxBehaviorEvent e) {

		if (searchValue != null && !searchValue.isEmpty()) {

			categorySearchResult = new ArrayList<>();

			ArrayList<CategoryDto> categories = (ArrayList<CategoryDto>)tree.getCategories();

			for (CategoryDto category : categories) {

				if (category.getName().toLowerCase().indexOf((searchValue.toLowerCase())) != -1) {

					categorySearchResult.add(category);

				}

			}

			searchValue = null;

		}

	}

	public void findTreeCategory(CategoryDto category) {

		findNodeToSelect(tree.getRoot(), category.getId());

	}

	private void findNodeToSelect(TreeNode parent, Long idToSelect) {

		children = parent.getChildren() != null ? (ArrayList<TreeNode>) parent.getChildren() : null;

		if (children != null) {

			for (TreeNode child : children) {

				if (((CategoryDto) child.getData()).getId() == idToSelect) {

					setSelectedNode(child);
					expandParent(child);
					resetContentFields();
					initializeContextFields(child);

					break;

				} else {

					findNodeToSelect(child, idToSelect);

				}

			}

		}

	}

	private void setSelectedNode(TreeNode node) {

		if(tree.getSelectedNode() != null){
			
			tree.getSelectedNode().setSelected(false);
			
		}
		
		node.setSelected(true);
		tree.setSelectedNode(node);

	}

	private void expandParent(TreeNode child) {
		if (child.getParent() != null) {
			child.getParent().setExpanded(true);
			expandParent(child.getParent());
		}
	}
	
	public void navigateInBreadcrumb(){
		System.out.println("in method");
	}

}