package com.baybridges.codeeval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			File file = new File(args[0]);
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String fileLine;
			List<Line> lines = new ArrayList<>();
			while ((fileLine = buffer.readLine()) != null) {
				// System.out.println(fileLine);
				fileLine = fileLine.trim();
				// Process line of input Here
				lines.add(convertToLine(fileLine));
			}
			List<Integer> results = processLines(lines);

			for (Integer lineName : results) {
				System.out.println(lineName);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Integer> processLines(List<Line> lines) {
		countNumOfLinesCrossed(lines);

		Collections.sort(lines, new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return -Integer.compare(o1.getNumOfLinesCrossed(), o2.getNumOfLinesCrossed());
			}
		});

		Iterator<Line> iterator = lines.iterator();
		while (iterator.hasNext()) {
			Line line = iterator.next();
			if (line.getNumOfLinesCrossed() > 0) {
				iterator.remove();
				countNumOfLinesCrossed(lines);
				iterator = lines.iterator();
			} else {
				break;
			}
		}
		List<Integer> results = new ArrayList<>();
		for (Line line : lines) {
			results.add(Integer.valueOf(line.getName()));
		}

		Collections.sort(results);

		return results;
	}

	private static void countNumOfLinesCrossed(List<Line> lines) {
		for (int i = 0; i < lines.size(); i++) {
			lines.get(i).setNumOfLinesCrossed(0);
			for (int j = 0; j < lines.size(); j++) {
				if (linesIntersect(lines.get(i), lines.get(j)) && i != j) {
					lines.get(i).setNumOfLinesCrossed(lines.get(i).getNumOfLinesCrossed() + 1);
				}
			}
		}
	}

	public static boolean linesIntersect(Line line1, Line line2) {
		// double x1 = line1.getPoints()[0].getX();
		// double x2 = line1.getPoints()[1].getX();
		// double y1 = line1.getPoints()[0].getY();
		// double y2 = line1.getPoints()[1].getY();
		//
		// double x3 = line2.getPoints()[0].getX();
		// double x4 = line2.getPoints()[1].getX();
		// double y3 = line2.getPoints()[0].getY();
		// double y4 = line2.getPoints()[1].getY();

		Point A = line1.getPoints()[0];
		Point B = line1.getPoints()[1];
		Point C = line2.getPoints()[0];
		Point D = line2.getPoints()[1];

		return (ccw(A, C, D) != ccw(B, C, D)) && (ccw(A, B, C) != ccw(A, B, D));
		//
		// double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 -
		// y3 * x4)) / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		// double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 -
		// y3 * x4)) / ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
		// return Math.min(x1, Math.min(x2, Math.min(x3, x4))) <= x && x <=
		// Math.max(x1, Math.max(x2, Math.max(x3, x4))) && Math.min(y1,
		// Math.min(y2, Math.min(y3, y4))) <= y
		// && y <= Math.max(y1, Math.max(y2, Math.max(y3, y4)));
	}

	private static boolean ccw(Point a, Point b, Point c) {
		BigDecimal cayDiff = c.getY().add(a.getY().negate());
		BigDecimal baxDiff = b.getX().add(a.getX().negate());
		BigDecimal bayDiff = b.getY().add(a.getY().negate());
		BigDecimal caxDiff = c.getX().add(a.getX().negate());
		BigDecimal leftSide = cayDiff.multiply(baxDiff);
		BigDecimal rightSide = bayDiff.multiply(caxDiff);
		return leftSide.compareTo(rightSide) > 0;
		// return (c.getY() - a.getY()) * (b.getX() - a.getX()) > (b.getY() -
		// a.getY()) * (c.getX() - a.getX());
	}

	public static Line convertToLine(String fileLine) {
		String name = fileLine.substring(0, fileLine.indexOf(":"));
		String firstPointStr = fileLine.substring(fileLine.indexOf("[") + 1);
		firstPointStr = firstPointStr.substring(0, firstPointStr.indexOf("]"));
		String secondPointStr = fileLine.substring(fileLine.indexOf("], [") + 4);
		secondPointStr = secondPointStr.substring(0, secondPointStr.indexOf("]"));
		Point firstPoint = createPoint(firstPointStr);
		Point secondPoint = createPoint(secondPointStr);
		Line line = new Main().new Line();
		line.setPoints(new Point[] { firstPoint, secondPoint });
		line.setName(name);
		return line;
	}

	public static Point createPoint(String firstPoint) {
		BigDecimal x = new BigDecimal(firstPoint.substring(0, firstPoint.indexOf(",")));
		BigDecimal y = new BigDecimal(firstPoint.substring(firstPoint.indexOf(",") + 1).trim());
		return new Main().new Point(x, y);
	}

	public class Line {

		private Point[] points;
		private String name;
		private int numOfLinesCrossed = 0;

		public Line() {
		}

		public Point[] getPoints() {
			return points;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setPoints(Point[] points) {
			this.points = points;
		}

		public int getNumOfLinesCrossed() {
			return numOfLinesCrossed;
		}

		public void setNumOfLinesCrossed(int numOfLinesCrossed) {
			this.numOfLinesCrossed = numOfLinesCrossed;
		}

	}

	public class Point {

		private BigDecimal x;
		private BigDecimal y;

		public Point(BigDecimal x, BigDecimal y) {
			this.x = x;
			this.y = y;
		}

		public BigDecimal getX() {
			return x;
		}

		public void setX(BigDecimal x) {
			this.x = x;
		}

		public BigDecimal getY() {
			return y;
		}

		public void setY(BigDecimal y) {
			this.y = y;
		}

	}
}
