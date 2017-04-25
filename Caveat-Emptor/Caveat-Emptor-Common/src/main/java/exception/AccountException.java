package exception;

public class AccountException extends Exception {

	private static final long serialVersionUID = 8694645760779381677L;

	public AccountException() {
		super();
	}
	
	public AccountException(String message){
		super(message);
	}

}
