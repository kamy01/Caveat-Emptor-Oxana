package constants;

public enum RedirectPages {
	
	LOGIN_PAGE("home.xhtml"),
	CONTENT_PAGE("templates/caveatEmptor.xhtml"),
	REGISTER_PAGE("register.xhtml"),
	REGISTER_SUCCESS_PAGE("registeredSuccess.xhtml");
	
	private String value;
	
	private RedirectPages(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

}
