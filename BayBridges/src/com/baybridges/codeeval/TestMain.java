package com.baybridges.codeeval;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.baybridges.codeeval.Main.Line;
import com.baybridges.codeeval.Main.Point;

public class TestMain {

	private static final double DELTA = 1e-15;

	@Test
	public void test_convertToLine() {
		Line line = Main.convertToLine("1: ([37.788353, -122.387695], [37.829853, -122.294312])");
		assertEquals(new BigDecimal("37.788353"), line.getPoints()[0].getX());
		assertEquals("1", line.getName());
	}

	@Test
	public void test_createPoint() {
		Point point = Main.createPoint("37.788353, -122.387695");

		assertEquals(new BigDecimal("37.788353"), point.getX());
		assertEquals(new BigDecimal("-122.387695"), point.getY());
	}

	@Test
	public void test_processLines() {
		List<Line> lines = new ArrayList<>();
		lines.add(Main.convertToLine("1: ([37.788353, -122.387695], [37.829853, -122.294312])"));
		lines.add(Main.convertToLine("2: ([37.429615, -122.087631], [37.487391, -122.018967])"));
		lines.add(Main.convertToLine("3: ([37.474858, -122.131577], [37.529332, -122.056046])"));
		lines.add(Main.convertToLine("4: ([37.532599,-122.218094], [37.615863,-122.097244])"));
		lines.add(Main.convertToLine("5: ([37.516262,-122.198181], [37.653383,-122.151489])"));
		lines.add(Main.convertToLine("6: ([37.504824,-122.181702], [37.633266,-122.121964])"));
		List<Integer> results = Main.processLines(lines);
		for (Integer lineName : results) {
			System.out.println(lineName);
		}
	}

	@Test
	public void test_RealInput() {
		List<Line> lines = new ArrayList<>();
		try {
			List<String> readAllLines = Files.readAllLines(Paths.get("../BayBridges/io/sample3.txt"));
			for (String line : readAllLines) {
				lines.add(Main.convertToLine(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Integer> results = Main.processLines(lines);
		for (Integer lineName : results) {
			System.out.println(lineName);
		}
	}
}
