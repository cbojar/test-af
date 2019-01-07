package net.cbojar.testaf;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public final class Assertion<T> {
	private final T actual;

	Assertion(final T actual) {
		this.actual = actual;
	}

	public void is(final T expected) {
		is(expected, String.format("Expected %s but was %s", expected, actual));
	}

	public void is(final T expected, final String messageOnFail) {
		if (actual == null && expected == null) {
			return;
		}

		if (actual == null || expected == null) {
			throw new AssertionError(messageOnFail);
		}

		if (!actual.equals(expected)) {
			throw new AssertionError(messageOnFail);
		}
	}

	public void is(final Matcher<? super T> matcher) {
		MatcherAssert.assertThat(actual, matcher);
	}

	public void is(final Matcher<? super T> matcher, final String messageOnFail) {
		MatcherAssert.assertThat(messageOnFail, actual, matcher);
	}
}
