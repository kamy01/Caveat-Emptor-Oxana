package constants;

public enum AccountConstants {

	ERROR("ERROR"),
	INVALID_CREDENTIALS("Invalid credentials!"),
	DUPLICATE_USERNAME("Username already exists!"),
	DUPLICATE_EMAIL("Email already exists!"),
	ALREADY_REGISTERED("Already registered"),
	REGISTER_NOT_SUCCIDED("Failed to register. Please try again!"),
	NO_SUCH_USER("No such user");
	
	private String value;
	
	private AccountConstants(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
