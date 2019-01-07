package net.cbojar.testaf;

public class TestAFException extends RuntimeException {
	public TestAFException() {
		super();
	}

	public TestAFException(final String message) {
		super(message);
	}

	public TestAFException(final Throwable cause) {
		super(cause);
	}

	public TestAFException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
