package constants;

public enum CategoryConstants {

	WARNING("WARNING"),
	ERROR("ERROR"),
	ERROR_UPDATE_TREE("An error ocured when trying to update the tree"),
	ERROR_ADD_ITEM("An error ocured when trying to add category. Please try again."),
	ERROR_REMOVE_ITEM("An error ocured when trying to remove item!"),
	ERROR_RETRIEVING_DATA("An error ocured when trying to retrieve data"),
	ERROR_NO_NODE_SELECTED_TO_DELETE("Select the category you want to delete!"),
	VALIDATION_EMPTY_FIELDS("Please complete all fields!");
	
	private String value;
	
	private CategoryConstants(String value){
		
		this.value = value;
		
	}
	
	public String getValue(){
		return value;
	}
}
