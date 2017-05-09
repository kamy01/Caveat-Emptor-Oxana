package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;

import FacesMessages.MyFacesMessage;
import common.ItemPurpose;
import constants.Constant;
import model.CategoryDto;
import model.ItemDto;
import services.items.IItemsService;

@ManagedBean(name = "itemsView")
@ViewScoped
public class Items implements Serializable {

	private static final long serialVersionUID = 8967168714511818014L;

	@EJB
	IItemsService iItemService;

	@ManagedProperty("#{userLogin}")
	private UserLogin userLogin;

	@ManagedProperty("#{treeBasicView}")
	private Tree tree;

	private List<ItemDto> items;
	private String itemPurpose;
	private Map<String, String> dropDownItems;
	private CategoryDto category;
	private ItemDto itemDto;
	private Date itemEndDate;
	private Date itemStartDate;

	@PostConstruct
	public void init() {

		if (userLogin.getUsername() == null) {

			MyFacesMessage.redirectToPage("../" + Constant.HOME_PAGE + "?faces-redirect=true");

		}

		initializeDropDown();

		onDropDownChange();

		itemDto = new ItemDto();

		itemEndDate = new Date();

		itemStartDate = new Date();

	}

	public Date getItemEndDate() {
		return itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		this.itemEndDate = itemEndDate;
	}

	public Date getItemStartDate() {
		return itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		this.itemStartDate = itemStartDate;
	}

	public ItemDto getItemDto() {
		return itemDto;
	}

	public void setItemDto(ItemDto itemDto) {
		this.itemDto = itemDto;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public Map<String, String> getDropDownItems() {
		return dropDownItems;
	}

	public void setDropDownItems(Map<String, String> dropDownItems) {
		this.dropDownItems = dropDownItems;
	}

	public String getItemPurpose() {
		return itemPurpose;
	}

	public void setItemPurpose(String itemPurpose) {
		this.itemPurpose = itemPurpose;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

	public void initializeDropDown() {

		List<String> enumValues = Stream.of(ItemPurpose.values()).map(ItemPurpose::name).collect(Collectors.toList());

		dropDownItems = new HashMap<String, String>();

		itemPurpose = enumValues.get(0);

		for (String value : enumValues) {

			dropDownItems.put(value, value);

		}

	}

	public void onDropDownChange() {

		if (itemPurpose.toLowerCase().equals(ItemPurpose.SELL.getValue())) {

			items = iItemService.getItemsByUserId(userLogin.getUser().getId());

		} else if (itemPurpose.equals(ItemPurpose.BUY.getValue())) {

			// TODO

		}

	}

	public void openDialogWindow() {

		RequestContext.getCurrentInstance().execute("PF('newItem-dialog').show()");

	}
	
	public void closeDialogWindow() {
		
		RequestContext.getCurrentInstance().execute("PF('newItem-dialog').hide()");
		
	}

	public void createNewItem() {

		System.out.println(itemStartDate + " " + itemEndDate + " " + itemDto);

		itemDto.setCategory((CategoryDto) tree.getSelectedNode().getData());
		
		itemDto.setUser(userLogin.getUser());

		iItemService.addNewItem(itemDto);
		
		items.add(itemDto);	
		
		closeDialogWindow();
		//TODO verify date to set the status.....add path to default image

	}

	public String addCategoryToItem(FlowEvent event) {

		category = (CategoryDto) tree.getSelectedNode().getData();

		return category != null ? event.getNewStep() : event.getOldStep();

	}

	public void onStartDateSelect() {

		itemDto.setOpeningDate(new Timestamp(itemStartDate.getTime()));

	}

	public void onEndDateSelect() {

		itemDto.setExpiringDate(new Timestamp(itemEndDate.getTime()));

	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			
			iItemService.addNewItem(items.get(event.getRowIndex()));
			
		}
		
		
		//TODO edit item and call method from service
	}

}
