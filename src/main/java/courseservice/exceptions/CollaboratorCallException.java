package courseservice.exceptions;

public class CollaboratorCallException extends RuntimeException {

	private static final long serialVersionUID = 6645225581506689359L;

	public CollaboratorCallException() {
		super();
	}

	public CollaboratorCallException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public CollaboratorCallException(String message) {
		super(message);
	}

	public CollaboratorCallException(Throwable cause) {
		super(cause);
	}

	
}
