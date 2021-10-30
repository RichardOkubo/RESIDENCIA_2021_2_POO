package org.serratec.residencia.poo.exception;

public class ValorNegativoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ValorNegativoException() {
		super();
	}
	
	public ValorNegativoException(String message) {
		super(message);
	}

	public ValorNegativoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValorNegativoException(Throwable cause) {
		super(cause);
	}

	protected ValorNegativoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
