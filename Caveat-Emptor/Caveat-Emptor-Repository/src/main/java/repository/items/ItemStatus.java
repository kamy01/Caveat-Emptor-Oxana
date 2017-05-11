package repository.items;

public enum ItemStatus {
	OPEN("open"),
	ABANDONED("abandoned"),
	CLOSED("closed"),
	NOT_OPEN("not yet open");
	
	private String value;
	
	private ItemStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
