package pharmapartners.crypto.currency;

public class CurrencyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyNotFoundException(String exception) {
		super(exception);
	}

}