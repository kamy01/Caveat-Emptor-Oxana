package constants;

public enum ItemConstants {

	DIALOG_WINDOW_NEW_ITEM("newItem-dialog"),
	DIALOG_WINDOW_EDIT_ITEM("editItem-dialog"),
	DATE_ERROR("Invalid Date"),
	DATE_INVALID_RANGE("Invalid date range selection");
	
	
	private String value;
	
	private ItemConstants(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
}
