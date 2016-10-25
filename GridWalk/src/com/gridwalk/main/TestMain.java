package com.gridwalk.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMain {

	@Test
	public void test_sumDigits() {
		assertEquals(1, Main.sumDigits(100));
		assertEquals(0, Main.sumDigits(0));
		assertEquals(14, Main.sumDigits(1535));
		assertEquals(1, Main.sumDigits(-100));
		assertEquals(14, Main.sumDigits(-1535));
	}

}
