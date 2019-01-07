package net.cbojar.testaf;

import org.junit.Test;

public class AssertTest {
	@Test
	public void shouldAssertTrueIsTrue() {
		Assert.that(true).isTrue();
	}

	@Test
	public void shouldAssertFalseIsFalse() {
		Assert.that(false).isFalse();
	}

	@Test(expected=AssertionError.class)
	public void shouldNotAssertThatTrueIsFalse() {
		Assert.that(true).isFalse();
	}

	@Test(expected=AssertionError.class)
	public void shouldNotAssertThatFalseIsTrue() {
		Assert.that(false).isTrue();
	}
}
