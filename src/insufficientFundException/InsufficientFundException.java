package insufficientFundException;

public class InsufficientFundException  extends Exception{
	
	private static final String MESSAGE = "INSUFFICIENT FUND try a lesser amount";

	public InsufficientFundException() {
		super( MESSAGE);
		// TODO Auto-generated constructor stub
	}

	public InsufficientFundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
