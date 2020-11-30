package invalidAccoutNumber;

public class InvalidAccountNumber  extends Exception {

	private static  final String MESSAGE = "INVALID ACCOUNT NUMBER";

	public InvalidAccountNumber() {
		super(MESSAGE);
		// TODO Auto-generated constructor stub
	}

	public InvalidAccountNumber(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
