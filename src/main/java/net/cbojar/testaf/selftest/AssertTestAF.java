package net.cbojar.testaf.selftest;

import static org.hamcrest.Matchers.*;

import net.cbojar.testaf.*;

public class AssertTestAF {
	@Test
	public void shouldAssertThatAStringisAString() {
		Assert.that("A").is("A");
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThatAStringIsBString() {
		Assert.that("A").is("B");
	}

	@Test
	public void shouldAssertThatAStringIsNotBString() {
		Assert.that("A").is(not("B"));
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThatAStringIsNotAString() {
		Assert.that("A").is(not("A"));
	}

	@Test
	public void shouldAssertTrueIsTrue() {
		Assert.that(true).isTrue();
	}

	@Test
	public void shouldAssertFalseIsFalse() {
		Assert.that(false).isFalse();
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThatTrueIsFalse() {
		Assert.that(true).isFalse();
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThatFalseIsTrue() {
		Assert.that(false).isTrue();
	}

	@Test
	public void shouldAssertThat1Is1() {
		Assert.that(1).is(1);
	}

	@Test
	public void shouldAssertThat1IsNot2() {
		Assert.that(1).is(not(2));
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThat1Is2() {
		Assert.that(1).is(2);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThat1IsNot1() {
		Assert.that(1).is(not(1));
	}

	@Test
	public void shouldAssertThat2IsGreaterThan1() {
		Assert.that(2).isGreaterThan(1);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThat1IsGreaterThan2() {
		Assert.that(1).isGreaterThan(2);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThat2IsGreaterThan2() {
		Assert.that(2).isGreaterThan(2);
	}

	@Test
	public void shouldAssertThat2IsGreaterThanOrEqualTo1() {
		Assert.that(2).isGreaterThanOrEqualTo(1);
	}

	@Test
	public void shouldAssertThat1IsGreaterThanOrEqualTo1() {
		Assert.that(1).isGreaterThanOrEqualTo(1);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThant1IsGreaterThanOrEqualTo2() {
		Assert.that(1).isGreaterThanOrEqualTo(2);
	}

	@Test
	public void shouldAssertThat1IsLessThan2() {
		Assert.that(1).isLessThan(2);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThat2IsLessThan1() {
		Assert.that(2).isLessThan(1);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThat2IsLessThan2() {
		Assert.that(2).isLessThan(2);
	}

	@Test
	public void shouldAssertThat1IsLessThanOrEqualTo2() {
		Assert.that(1).isLessThanOrEqualTo(2);
	}

	@Test
	public void shouldAssertThat1IsLessThanOrEqualTo1() {
		Assert.that(1).isLessThanOrEqualTo(1);
	}

	@Test(thrown=AssertionError.class)
	public void shouldNotAssertThant2IsLessThanOrEqualTo1() {
		Assert.that(2).isLessThanOrEqualTo(1);
	}
}
