package common;

public enum ItemPurpose {

	SELL("sell"),
	BUY("buy");
	
	private String value;
	
	private ItemPurpose(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
}
