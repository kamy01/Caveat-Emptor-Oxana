package constants;

public enum EmailConstants {
	
	MAIL_REGISTRATION_SITE_LINK("http://localhost:8080/Caveat-Emptor-Web-0.0.1-SNAPSHOT/activate.xhtml"),
	MAIL_HELLO_MSG("HELLO"),
	MAIL_NEW_LINE("\n"),
	MAIL_SENDER("\n Best regards. \n \n The Caveat Emptor team."),
	MAIL_SUBJECT("We are glad you have registered to Caveat Emptor. \n \n Please, activate your account by clicking this link:");
	
	private String value;

	private EmailConstants(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
