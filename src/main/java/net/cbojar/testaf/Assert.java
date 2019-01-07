package net.cbojar.testaf;

public class Assert {
	public static <T> Assertion<T> that(final T actual) {
		return new Assertion<>(actual);
	}

	public static BooleanAssertion that(final boolean actual) {
		return new BooleanAssertion(actual);
	}

	public static IntAssertion that(final int actual) {
		return new IntAssertion(actual);
	}
}
