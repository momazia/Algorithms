package com.typeahead.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.typeahead.main.Main;
import com.typeahead.main.Main.Input;

public class TestMain {

	@Test
	public void test_split() {
		String[] split = Main.split(Main.INPUT_TEXT);
		assertEquals(89, split.length);
	}

	@Test
	public void test_removeSpecialCharacters3() {
		String[] array = Main.split("snow; And ");
		assertEquals(2, array.length);
		assertEquals("snow", array[0]);
		assertEquals("And", array[1]);
	}

	@Test
	public void test_removeSpecialCharacters1() {
		String[] array = Main.split("t rv;sb m.");
		assertEquals(4, array.length);
		assertEquals("t", array[0]);
		assertEquals("m", array[3]);
	}

	@Test
	public void test_removeSpecialCharacters2() {
		String[] array = Main.split("It a py, \"to a sl.");
		assertEquals(6, array.length);
		assertEquals("It", array[0]);
		assertEquals("sl", array[5]);
	}

	@Test
	public void test_readInput1() {
		Input input = Main.readInput("2,the");
		assertEquals(2, input.getNgram());
		assertEquals("the", input.getText());
	}

	@Test
	public void test_readInput2() {
		Input input = Main.readInput("3,lingered near");
		assertEquals(3, input.getNgram());
		assertEquals("lingered near", input.getText());
	}

	@Test
	public void test_isSame() {
		assertTrue(Main.isSame(new String[] { "mahdi", "zia" }, 0, new String[] { "mahdi", "zia", "something", "here" }));
	}

	@Test
	public void test_process1() {
		Main.process(new Main().new Input("the", 2));
	}

	@Test
	public void test_process2() {
		Main.process(new Main().new Input("lingered near", 3));
	}

	@Test
	public void test_process3() {
		Main.process(new Main().new Input(" cry; \"Why, Marry", 3));
	}
	
	@Test
	public void test_process4() {
		Main.process(new Main().new Input("Mary had", 3));
	}

}
