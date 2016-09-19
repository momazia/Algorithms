package com.maxrangesum.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.maxrangesum.main.Main;
import com.maxrangesum.main.Main.Entry;

public class TestMain {

	@Test
	public void test_readFile() {
		String line = "5;7 -3 -10 4 2 8 -2 4 -5 -2";
		Entry entry = Main.readFile(line);
		assertEquals(5, entry.getNumOfDays());
		assertEquals(10, entry.getValues().length);
		assertEquals(7, entry.getValues()[0]);
		assertEquals(-2, entry.getValues()[9]);
	}

}
