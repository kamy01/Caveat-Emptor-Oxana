package repository.user;

public enum AccountStatus {
	ACTIVE("active"),
	PENDING("pending");
	
	private final String value;
	
	private AccountStatus(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
