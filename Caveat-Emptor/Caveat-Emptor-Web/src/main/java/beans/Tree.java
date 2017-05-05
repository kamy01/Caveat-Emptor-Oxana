package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import model.CategoryDto;
import services.categories.ICategory;

@ManagedBean(name = "treeBasicView")
@ViewScoped
public class Tree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	ICategory iCategory;

	private TreeNode root;

	private TreeNode selectedNode;
	
	private String name;
	
	private String description;

	private List<CategoryDto> categories;

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
	

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void initializeTree() {

		root = new DefaultTreeNode(new CategoryDto(), null);
		
		categories = iCategory.getAllCAtegories();

		createRootNodes(root, null);

	}

	private void createRootNodes(TreeNode root, Long parentId) {

		ArrayList<CategoryDto> children = (ArrayList<CategoryDto>)getChildrenForCurrentParrent(parentId);

		for (CategoryDto child : children) {

			TreeNode thChild = new DefaultTreeNode(child, root);

			thChild.setParent(root);
			createRootNodes(thChild, child.getId());

		}

	}

	private List<CategoryDto> getChildrenForCurrentParrent(Long parentId) {

		ArrayList<CategoryDto> children = new ArrayList<>();

		for (CategoryDto category : categories) {

			if (category.getParentId() == parentId) {

				children.add(category);
			}

		}

		return (List<CategoryDto>)children;
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
