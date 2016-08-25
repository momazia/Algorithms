package com.algorithms.main;

public class Alphabet {

	private char chr;
	private int score;
	private int counter;

	public Alphabet(char chr) {
		this.chr = chr;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public char getChr() {
		return chr;
	}

	public void setChr(char chr) {
		this.chr = chr;
	}

	public String toString() {
		return this.chr + ": Score (" + this.score + "), Counter (" + this.counter + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Alphabet))
			return false;
		if (obj == this)
			return true;

		Alphabet rhs = (Alphabet) obj;
		return rhs.getChr() == this.chr;
	}
}
