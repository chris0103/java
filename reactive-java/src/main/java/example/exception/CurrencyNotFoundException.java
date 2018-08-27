package example.exception;

public class CurrencyNotFoundException extends Exception {

	public CurrencyNotFoundException() {
		
		super("Currency not supported");
	}
}
