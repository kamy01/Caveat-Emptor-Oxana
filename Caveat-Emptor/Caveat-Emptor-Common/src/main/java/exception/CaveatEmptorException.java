package exception;

public class CaveatEmptorException extends Exception {

	private static final long serialVersionUID = 8694645760779381677L;

	private String message;

	public CaveatEmptorException() {
		super();
	}

	public CaveatEmptorException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
