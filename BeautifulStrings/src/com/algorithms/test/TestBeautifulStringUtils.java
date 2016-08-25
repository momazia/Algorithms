package com.algorithms.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

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

}
