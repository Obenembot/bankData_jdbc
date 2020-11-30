package wrongPin;

public class WrongPinException  extends Exception {

	private static final String MESSAGE = "WRONG PASSWORD OR USERNAME";

	public WrongPinException() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}

	public WrongPinException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
