package com.baybridges.codeeval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Max
 *
 */
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			File file = new File(args[0]);
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String fileLine;
			List<Line> lines = new ArrayList<>();
			while ((fileLine = buffer.readLine()) != null) {
				fileLine = fileLine.trim();
				// Process line of input Here
				lines.add(convertToLine(fileLine));
			}
			// Processing the lines
			List<Integer> results = processLines(lines);
			// Printing the result
			for (Integer lineName : results) {
				System.out.println(lineName);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * It counts the number of lines crossed and try to remove the ones with
	 * highest number and recalculate until there is no two line segmets are
	 * crossing.
	 * 
	 * @param lines
	 * @return
	 */
	public static List<Integer> processLines(List<Line> lines) {
		// Counting the number of crossed lines for each of the lines.
		countNumOfLinesCrossed(lines);

		// Sorting the list of lines in Descending order based on the number of
		// lines crossed.
		Collections.sort(lines, new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return -Integer.compare(o1.getNumOfLinesCrossed(), o2.getNumOfLinesCrossed());
			}
		});
		// Going through the lines and removing the ones which highest number of
		// lines crossed until lines are crossing each other.
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
		List<Integer> results = getLineNames(lines);
		return results;
	}

	/**
	 * Sorts the final result based on the name of the lines (numbers) in
	 * ascending order.
	 * 
	 * @param lines
	 * @return
	 */
	private static List<Integer> getLineNames(List<Line> lines) {
		List<Integer> results = new ArrayList<>();
		for (Line line : lines) {
			results.add(Integer.valueOf(line.getName()));
		}
		Collections.sort(results);
		return results;
	}

	/**
	 * Calculates the number of lines crossing each other.
	 * 
	 * @param lines
	 */
	private static void countNumOfLinesCrossed(List<Line> lines) {
		for (int i = 0; i < lines.size(); i++) {
			// Reseting the counter
			lines.get(i).setNumOfLinesCrossed(0);
			for (int j = 0; j < lines.size(); j++) {
				// Compare the lines points with each other (avoid comparing the
				// same line with itself)
				if (i != j && segmentsIntersect(lines.get(i).getPoints()[0], lines.get(i).getPoints()[1], lines.get(j).getPoints()[0], lines.get(j).getPoints()[1])) {
					lines.get(i).setNumOfLinesCrossed(lines.get(i).getNumOfLinesCrossed() + 1);
				}
			}
		}
	}

	/**
	 * Calculates to see if the points given are on the segment
	 * 
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	private static boolean onSegment(Point p, Point q, Point r) {
		if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
			return true;
		}
		return false;
	}

	/**
	 * Calculates the direction of p and q, in relation with r.
	 * 
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	private static double direction(Point p, Point q, Point r) {
		return (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
	}

	/**
	 * If line p1 p2 intersects with line p3 p4.
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @return
	 */
	private static boolean segmentsIntersect(Point p1, Point p2, Point p3, Point p4) {
		double d1 = direction(p3, p4, p1);
		double d2 = direction(p3, p4, p2);
		double d3 = direction(p1, p2, p3);
		double d4 = direction(p1, p2, p4);

		if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
			return true;
		}

		if (d1 == 0 && onSegment(p3, p4, p1)) {
			return true;
		}
		if (d2 == 0 && onSegment(p3, p4, p2)) {
			return true;
		}
		if (d3 == 0 && onSegment(p1, p2, p3)) {
			return true;
		}
		if (d4 == 0 && onSegment(p1, p2, p4)) {
			return true;
		}
		return false;
	}

	/**
	 * Converts file line passed into a line
	 * 
	 * @param fileLine
	 * @return
	 */
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

	/**
	 * Creates points out of the point string given.
	 * 
	 * @param pointStr
	 * @return
	 */
	public static Point createPoint(String pointStr) {
		double x = Double.valueOf(pointStr.substring(0, pointStr.indexOf(",")));
		double y = Double.valueOf(pointStr.substring(pointStr.indexOf(",") + 1));
		return new Main().new Point(x, y);
	}

	/**
	 * This class holds the data about the lines
	 * 
	 * @author Max
	 *
	 */
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

	/**
	 * Point class holds X and Y coordinates.
	 * 
	 * @author Max
	 *
	 */
	public class Point {

		private double x;
		private double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

	}
}
