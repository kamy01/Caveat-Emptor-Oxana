package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;

import FacesMessages.MyFacesMessage;
import common.ItemPurpose;
import constants.ItemConstants;
import constants.RedirectPages;
import model.CategoryDto;
import model.ItemDto;
import repository.items.ItemStatus;
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
	private ItemDto itemDto;
	private Date itemEndDate;
	private Date itemStartDate;
	private String hideCellEdit;
	private boolean skipItemOpeningDate;
	private boolean skip;
	private boolean editTableRow;
	private Long currentRowIndex;

	@PostConstruct
	public void init() {

		if (userLogin.getUsername() == null) {

			MyFacesMessage.redirectToPage("../" + RedirectPages.LOGIN_PAGE.getValue() + "?faces-redirect=true");

		}

		initializeDropDown();

		onDropDownChange();

		itemDto = new ItemDto();

		itemEndDate = new Date();

		itemStartDate = new Date();

		hideCellEdit = ItemStatus.NOT_OPEN.getValue();

	}

	public Long getCurrentRowIndex() {
		return currentRowIndex;
	}

	public void setCurrentRowIndex(Long currentRowIndex) {
		this.currentRowIndex = currentRowIndex;
	}

	public boolean isEditTableRow() {
		return editTableRow;
	}

	public void setEditTableRow(boolean editTableRow) {
		this.editTableRow = editTableRow;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean getSkipItemOpeningDate() {
		return skipItemOpeningDate;
	}

	public void setSkipItemOpeningDate(boolean skipItemOpeningDate) {
		this.skipItemOpeningDate = skipItemOpeningDate;
	}

	public String getHideCellEdit() {
		return hideCellEdit;
	}

	public void setHideCellEdit(String hideCellEdit) {
		this.hideCellEdit = hideCellEdit;
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
	
	public String onFlowProcess(FlowEvent event){
		
		return null;
	}

	public void initializeDropDown() {

		List<String> enumValues = Stream.of(ItemPurpose.values()).map(ItemPurpose::name).collect(Collectors.toList());

		dropDownItems = new HashMap<String, String>();

		itemPurpose = enumValues.get(0);

		/*for (String value : enumValues) {

			dropDownItems.put(value, value);

		}*/
		
		dropDownItems.put(itemPurpose, itemPurpose);

	}

	public void onDropDownChange() {

		if (itemPurpose.toLowerCase().equals(ItemPurpose.SELL.getValue())) {

			items = iItemService.getItemsByUserId(userLogin.getUser().getId());

			for (ItemDto item : items) {

				item.setAvailableStatus(populateItemsSatusList(item));

			}

		} else if (itemPurpose.toLowerCase().equals(ItemPurpose.BUY.getValue())) {

			// TODO

		}

	}

	private List<String> populateItemsSatusList(ItemDto item) {

		List<String> statusList = new ArrayList<>();

		statusList.add(item.getStatus());
		statusList.add(ItemStatus.OPEN.getValue());
		
		if (item.getStatus().equals(ItemStatus.NOT_OPEN.getValue())) {

			statusList.add(ItemStatus.ABANDONED.getValue());

		}

		return statusList;

	}
	
	public void openWizard(){
		
		tree.setSelectedNode(null);
		
		openDialogWindow(ItemConstants.DIALOG_WINDOW_NEW_ITEM.getValue());
		
	}

	public void openDialogWindow(String id) {

		RequestContext.getCurrentInstance().execute("PF('" + id + "').show()");

	}

	public void closeDialogWindow(String id) {

		RequestContext.getCurrentInstance().execute("PF('" + id + "').hide()");

	}

	public void openTreeToEditItemCategory(ItemDto item) {

		itemDto = item;

		openDialogWindow(ItemConstants.DIALOG_WINDOW_EDIT_ITEM.getValue());

	}

	public void editItem() {

		if (tree.getSelectedNode() != null) {

			itemDto.setCategory((CategoryDto) tree.getSelectedNode().getData());

		}

		iItemService.addNewItem(itemDto);

		closeDialogWindow(ItemConstants.DIALOG_WINDOW_EDIT_ITEM.getValue());

	}

	public void createNewItem() {

		if(isValidTimePeriodForNewItem()) {

			completeEmptyFieldsForNewItem();

			iItemService.addNewItem(itemDto);

			items.add(itemDto);

			closeDialogWindow(ItemConstants.DIALOG_WINDOW_NEW_ITEM.getValue());

		}

	}
	
	private void completeEmptyFieldsForNewItem(){
		
		if (tree.getSelectedNode() != null) {

			itemDto.setCategory((CategoryDto) tree.getSelectedNode().getData());

		}

		itemDto.setUser(userLogin.getUser());

		itemDto.setStatus(setItemStatus());

		itemDto.setBestBidValue(new Long(800));
		
	}
	
	private boolean isValidTimePeriodForNewItem(){
		
		if(itemDto.getOpeningDate() == null) {
			setItemOpenDateAsCurrentTimestamp(); 
		}
		
		if(itemDto.getExpiringDate() == null) {
			setItemEndDateAsCurrentTimestamp();
		}
		
		if (itemDto.getOpeningDate().after(itemDto.getExpiringDate())) {

			MyFacesMessage.addMessage(FacesMessage.SEVERITY_ERROR, ItemConstants.DATE_ERROR.getValue() , ItemConstants.DATE_INVALID_RANGE.getValue());
			
			return false;
			
		} 
		
		return true;
		
	}

	private String setItemStatus() {

		if (itemDto.getOpeningDate().after(new Timestamp(System.currentTimeMillis()))) {

			return ItemStatus.OPEN.getValue();

		}

		return ItemStatus.NOT_OPEN.getValue();

	}

	public void disableItemDate() {

		if (skip) {

			skipItemOpeningDate = true;
			setItemOpenDateAsCurrentTimestamp();
			setItemEndDateAsCurrentTimestamp();

		} else {

			skipItemOpeningDate = false;

		}

	}

	public void setItemOpenDateAsCurrentTimestamp() {

		itemDto.setOpeningDate(new Timestamp(System.currentTimeMillis()));
	}

	public void setItemEndDateAsCurrentTimestamp() {

		itemDto.setExpiringDate(new Timestamp(System.currentTimeMillis()));
	}

	public void onStartDateSelect(ItemDto item) {

		item.setOpeningDate(new Timestamp(itemStartDate.getTime()));

	}

	public void onEndDateSelect(ItemDto item) {

		item.setExpiringDate(new Timestamp(itemEndDate.getTime()));

	}

	public void onCellEdit(CellEditEvent event) {

		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {

			items.get(event.getRowIndex()).setAvailableStatus(populateItemsSatusList(items.get(event.getRowIndex())));

			iItemService.addNewItem(items.get(event.getRowIndex()));

		}

	}

	public void abandonItem(ItemDto item) {

		editTableRow = false;

		item.setStatus(ItemStatus.ABANDONED.getValue());

		item.setAvailableStatus(populateItemsSatusList(item));

		iItemService.addNewItem(item);

	}

	public void editItemRow(ItemDto item) {

		editTableRow = true;

		setCurrentRowIndex(item.getId());

		item.setAvailableStatus(populateItemsSatusList(item));

		itemStartDate = new Date(item.getOpeningDate().getTime());
		itemEndDate = new Date(item.getExpiringDate().getTime());

	}

	public void saveEditedRow(ItemDto item) {

		editTableRow = false;
		iItemService.addNewItem(item);

	}

	public void onStatusChange(ItemDto item) {

		editTableRow = false;
		item.setAvailableStatus(populateItemsSatusList(item));
		iItemService.addNewItem(item);

	}

}
