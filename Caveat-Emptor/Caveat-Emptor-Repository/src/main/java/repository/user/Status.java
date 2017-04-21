package repository.user;

public enum Status {
	ACTIVE("active"),
	INACTIVE("inactive"),
	PENDING("pending");
	
	private final String value;
	
	private Status(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
