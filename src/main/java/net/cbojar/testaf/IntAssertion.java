package net.cbojar.testaf;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public class IntAssertion {
	private final int actual;

	IntAssertion(final int actual) {
		this.actual = actual;
	}

	public void is(final int expected) {
		is(expected, String.format("Expected %s but was %s", expected, actual));
	}

	public void is(final Integer expected) {
		if (expected == null) {
			throw new NullPointerException("Expected boolean cannot be null");
		}

		is(expected.intValue());
	}

	public void is(final Integer expected, final String messageOnFail) {
		if (expected == null) {
			throw new NullPointerException("Expected int cannot be null");
		}

		is(expected.intValue(), messageOnFail);
	}

	public void is(final int expected, final String messageOnFail) {
		if (actual != expected) {
			throw new AssertionError(messageOnFail);
		}
	}

	public void is(final Matcher<? super Integer> matcher) {
		MatcherAssert.assertThat(actual, matcher);
	}

	public void is(final Matcher<? super Integer> matcher, final String messageOnFail) {
		MatcherAssert.assertThat(messageOnFail, actual, matcher);
	}

	public void isGreaterThan(final int expected) {
		isGreaterThan(expected, String.format("Expected %s to be greater than %s", actual, expected));
	}

	public void isGreaterThan(final int expected, final String messageOnFail) {
		if (actual <= expected) {
			throw new AssertionError(messageOnFail);
		}
	}

	public void isGreaterThanOrEqualTo(final int expected) {
		isGreaterThanOrEqualTo(expected, String.format("Expected %s to be greater than or equal to %s", actual, expected));
	}

	public void isGreaterThanOrEqualTo(final int expected, final String messageOnFail) {
		if (actual < expected) {
			throw new AssertionError(messageOnFail);
		}
	}

	public void isLessThan(final int expected) {
		isLessThan(expected, String.format("Expected %s to be less than %s", actual, expected));
	}

	public void isLessThan(final int expected, final String messageOnFail) {
		if (actual >= expected) {
			throw new AssertionError(messageOnFail);
		}
	}

	public void isLessThanOrEqualTo(final int expected) {
		isLessThanOrEqualTo(expected, String.format("Expected %s to be less than or equal to %s", actual, expected));
	}

	public void isLessThanOrEqualTo(final int expected, final String messageOnFail) {
		if (actual > expected) {
			throw new AssertionError(messageOnFail);
		}
	}
}
