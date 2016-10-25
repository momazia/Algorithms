package com.gridwalk.main;

import java.util.ArrayList;
import java.util.List;

/**
 * The main method to solve Grid walk.
 * 
 * @author Max
 *
 */
public class Main {

	private static List<Point> visitedPoints = new ArrayList<>(); // Keeps track of the visited nodes.
	private static int maxX = 0; // The most far positive value of x coordinate.

	/**
	 * Main method to be executed.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Starting with point 0,0
		explore(new Main().new Point(0, 0));
		// By finding 1/4 of the points, we can find the total number of points using:
		// total points = visitedPoints * 4 - redundant points + the initial point (1)
		System.out.println((visitedPoints.size() * 4 - (maxX + 1) * 4 + 1));
	}

	/**
	 * Explores the given point
	 * 
	 * @param point
	 */
	public static void explore(Point point) {
		if (nodeVisited(point)) {
			// Point has been visited before, so skipping.
			return;
		}
		if (isAccessible(point)) {
			// Adding the visited point into the set
			visitedPoints.add(point);
			// Exploring the next points to right and top.
			explore(new Main().new Point(point.getX() + 1, point.getY()));
			explore(new Main().new Point(point.getX(), point.getY() + 1));
		}

	}

	/**
	 * Checks to see if the point passed was visited before by comparing x and y coordinates.
	 * 
	 * @param point
	 * @return
	 */
	private static boolean nodeVisited(Point point) {
		for (Point visitedPoint : visitedPoints) {
			if (visitedPoint.getX() == point.getX() && visitedPoint.getY() == point.getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the point given is accessible. Meaning the sum of the x and y digits is less than 19. It also finds the maximum_x number.
	 * 
	 * @param point
	 * @return
	 */
	private static boolean isAccessible(Point point) {
		int digitsSum = sumDigits(point.getX()) + sumDigits(point.getY());
		if (point.getY() == 0 && digitsSum == 19) {
			maxX = point.getX();
		}
		return digitsSum <= 19;
	}

	/**
	 * Sums the digits in the number given after changing it to a positive number.
	 * 
	 * @param num
	 * @return
	 */
	public static int sumDigits(int num) {
		int temp = Math.abs(num);
		int result = 0;
		while (temp > 0) {
			result += temp % 10;
			temp /= 10;
		}
		return result;
	}

	/**
	 * Point object holds x and y coordinate of a point.
	 * 
	 * @author Max
	 *
	 */
	public class Point {

		private int x;
		private int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	}
}
