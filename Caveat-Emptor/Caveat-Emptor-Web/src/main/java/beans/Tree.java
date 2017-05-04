package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import model.CategoryDto;
import services.categories.ICategory;

@Named(value = "treeBasicView")
@SessionScoped
public class Tree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	Content content;
	
	@EJB
	ICategory iCategory;

	private TreeNode root;

	private TreeNode selectedNode;
	
	private String name;
	
	private String description;

	private ArrayList<CategoryDto> categories = new ArrayList<>();

	@PostConstruct
	public void init() {

		initializeTree();

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

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public TreeNode getRoot() {
		return root;
	}
	

	public ArrayList<CategoryDto> getCategories() {
		return categories;
	}

	public void initializeTree() {

		root = new DefaultTreeNode(new CategoryDto(), null);

		categories = iCategory.getAllCAtegories();

		createRootNodes(root, null);

	}

	private void createRootNodes(TreeNode root, Long parentId) {

		ArrayList<CategoryDto> children = getChildrenForCurrentParrent(parentId);

		for (CategoryDto child : children) {

			TreeNode thChild = new DefaultTreeNode(child, root);

			thChild.setParent(root);
			createRootNodes(thChild, child.getId());

		}

	}

	private ArrayList<CategoryDto> getChildrenForCurrentParrent(Long parentId) {

		ArrayList<CategoryDto> children = new ArrayList<>();

		for (CategoryDto category : categories) {

			if (category.getParentId() == parentId) {

				children.add(category);
			}

		}

		return children;
	}

	public void onNodeExpand(NodeExpandEvent event) {
		event.getTreeNode().toString();
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
	}

	public void onNodeSelect(NodeSelectEvent event) {
		event.getTreeNode().getData();
		this.name = ((CategoryDto)event.getTreeNode().getData()).getName();
		this.description = ((CategoryDto)event.getTreeNode().getData()).getDescription();
	}

	public void onNodeUnselect(NodeUnselectEvent event) {
	}

}
