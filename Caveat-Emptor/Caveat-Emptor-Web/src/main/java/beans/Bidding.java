package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import model.CategoryDto;
import model.ItemDto;
import services.bidding.IBiddingService;

@ManagedBean(name = "biddingView")
@ViewScoped
public class Bidding implements Serializable {

	private static final long serialVersionUID = 8444352393327844036L;

	@ManagedProperty("#{treeBasicView}")
	Tree tree;
	
	@EJB
	IBiddingService iBidding;
	
	private List<ItemDto> items;
	private boolean hideDataTable;
	private CategoryDto descriptionCategory;
	private ItemDto itemDetails;
	
	@PostConstruct
	public void init() {
		
		descriptionCategory = new CategoryDto();
		
	}

	public ItemDto getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(ItemDto itemDetails) {
		this.itemDetails = itemDetails;
	}

	public CategoryDto getDescriptionCategory() {
		return descriptionCategory;
	}

	public void setDescriptionCategory(CategoryDto descriptionCategory) {
		this.descriptionCategory = descriptionCategory;
	}

	public boolean getHideDataTable() {
		return hideDataTable;
	}

	public void setHideDataTable(boolean hideDataTable) {
		this.hideDataTable = hideDataTable;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		
		List<Long> ids = new ArrayList<>();
		
		hideDataTable = true;
		
		descriptionCategory = (CategoryDto) event.getTreeNode().getData();
		
		ids.add(descriptionCategory.getId());
		
		getIdsListForSelectedTree(event.getTreeNode(), ids);
		
		items = new ArrayList<>();
		
		items = iBidding.findItemsByCategoryIds(ids);
		
	}
	
	public void getIdsListForSelectedTree(TreeNode root, List<Long>ids){
		
		List<TreeNode> children = root.getChildren();
		
		for(TreeNode child: children){
			
			ids.add(((CategoryDto)child.getData()).getId());
			
			getIdsListForSelectedTree(child, ids);
			
		}
		
		
	}
	
	public void openItemDetailsWindow(ItemDto item){
		
		RequestContext.getCurrentInstance().execute("PF('dlg1').show()");
		
		setItemDetails(item);
		
	}

}
