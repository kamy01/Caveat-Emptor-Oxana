package constants;

public enum TreeConstants {

	ERROR("ERROR");
	
	private String value; 
	
	private TreeConstants(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
