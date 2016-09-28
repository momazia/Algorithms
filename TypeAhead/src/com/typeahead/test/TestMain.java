package com.typeahead.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.typeahead.main.Main;
import com.typeahead.main.Main.Input;

public class TestMain {

	@Test
	public void test_removeSpecialCharacters() {
		String[] array = Main.removeSpecialCharacters("It and play, to a school.");
		assertEquals(6, array.length);
		assertEquals("It", array[0]);
		assertEquals("school", array[5]);
	}

	@Test
	public void test_readInput() {
		Input input = Main.readInput("2,the");
		assertEquals(2, input.getNgram());
		assertEquals("the", input.getText());
	}

	@Test
	public void test_process1() {
		Main.process(new Main().new Input("the", 2));
	}
	
	@Test
	public void test_process2() {
		Main.process(new Main().new Input("lingered near", 3));
	}

}
