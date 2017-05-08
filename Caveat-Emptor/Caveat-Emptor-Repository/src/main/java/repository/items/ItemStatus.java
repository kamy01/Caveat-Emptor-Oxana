package repository.items;

public enum ItemStatus {
	OPEN("open"),
	ABANDONED("abandoned"),
	CLOSED("closed");
	
	private String value;
	
	private ItemStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
