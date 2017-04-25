package constants;

public final class Constant {

	public static final String HELLO_MSG = "HELLO";
	
	public static final String REGISTER_ERROR = "Register Error";
	public static final String LOGIN_ERROR = "Login Error";
	public static final String USERNAME_EXISTS = "Username already exists!";
	public static final String EMAIL_EXISTS = "Email already exists!";
	public static final String INVALID_CREDENTIALS = "Invalid credentials";
	public static final String ALREADY_REGISTERED = "Already registerede";
	public static final String NO_SUCH_USER = "No such user";
	public static final String MAIL_SUBJECT = "We are glad you have registered to Caveat Emptor. \n\n Please, activate your account by clicking this link:";
	public static final String MAIL_SENDER = "Best regards. \n\n The Caveat Emptor team.";
	
	public static final String HOME_PAGE = "home";
	public static final String CAVEAT_EMPTOR_PAGE = "templates/caveatEmptor";
	public static final String REGISER_PAGE = "register";
	public static final String REGISTERED_SUCCESS_PAGE = "registeredSuccess";
	
	public static final String NEW_LINE = "\n";
	
	public static final String MAIL_REGISTRATION_SITE_LINK = "http://localhost:8080/Caveat-Emptor-Web-0.0.1-SNAPSHOT/activate.xhtml";
	
	public static final long HOUR = 3600 * 1000;

	private Constant() {
		throw new AssertionError();
	}

}
