package net.cbojar.testaf;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public final class BooleanAssertion {
	private final boolean actual;

	BooleanAssertion(final boolean actual) {
		this.actual = actual;
	}

	public void is(final boolean expected) {
		is(expected, String.format("Expected %s but was %s", expected, actual));
	}

	public void is(final Boolean expected) {
		if (expected == null) {
			throw new NullPointerException("Expected boolean cannot be null");
		}

		is(expected.booleanValue());
	}

	public void is(final Boolean expected, final String messageOnFail) {
		if (expected == null) {
			throw new NullPointerException("Expected boolean cannot be null");
		}

		is(expected.booleanValue(), messageOnFail);
	}

	public void is(final boolean expected, final String messageOnFail) {
		if (actual != expected) {
			throw new AssertionError(messageOnFail);
		}
	}

	public void is(final Matcher<? super Boolean> matcher) {
		MatcherAssert.assertThat(actual, matcher);
	}

	public void is(final Matcher<? super Boolean> matcher, final String messageOnFail) {
		MatcherAssert.assertThat(messageOnFail, actual, matcher);
	}

	public void isTrue() {
		is(true);
	}

	public void isTrue(final String messageOnFail) {
		is(true, messageOnFail);
	}

	public void isFalse() {
		is(false);
	}

	public void isFalse(final String messageOnFail) {
		is(false, messageOnFail);
	}
}
