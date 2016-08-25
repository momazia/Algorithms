package com.algorithms.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.algorithms.main.Alphabet;
import com.algorithms.main.BeautifulStringUtils;

public class TestBeautifulStringUtils {

	@Test
	public void testReadFile() {
		try {
			List<String> lines = BeautifulStringUtils.getInstance().readFile("input.txt");
			assertEquals(5, lines.size());
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			fail("An exception happened");
		}
	}

	@Test
	public void testAll() {
		List<Integer> result = new ArrayList<>();
		try {
			List<String> lines = BeautifulStringUtils.getInstance().readFile("input.txt");
			for (String line : lines) {
				result.add(BeautifulStringUtils.getInstance().calculateScore(line));
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			fail("An exception happened");
		}
		assertEquals(Integer.valueOf(152), result.get(0));
		assertEquals(Integer.valueOf(754), result.get(1));
		assertEquals(Integer.valueOf(491), result.get(2));
		assertEquals(Integer.valueOf(729), result.get(3));
		assertEquals(Integer.valueOf(646), result.get(4));
	}

	@Test
	public void testCalculateScore1() {
		assertEquals(152, BeautifulStringUtils.getInstance().calculateScore("ABbCcc"));
	}

	@Test
	public void testCalculateScore2() {
		assertEquals(754, BeautifulStringUtils.getInstance().calculateScore("Good luck in the Facebook Hacker Cup this year!"));
	}

	@Test
	public void testCountCharacters() {
		Collection<Alphabet> countCharacters = BeautifulStringUtils.getInstance().countCharacters("Hacker Cup this year!");
		assertEquals(12, countCharacters.size());
	}

}
