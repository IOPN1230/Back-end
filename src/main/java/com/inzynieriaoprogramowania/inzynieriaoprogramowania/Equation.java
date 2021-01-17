package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.Place;

public class Equation {
	private double a, b;
	private double xStart, xEnd;
	public Equation(double a, double b, double xStart, double xEnd) {
		this.a = a;
		this.b = b;
		this.xStart = xStart;
		this.xEnd = xEnd;
	}
	public void setA(double a) {
		this.a = a;
	}
	public void setB(double b) {
		this.b = b;
	}
	public void setB(Place p) {
		this.b = p.getY() - this.a * p.getX();
	}
	public double getA() {
		return a;
	}
	public double getB() {
		return b;
	}
	public boolean check(Equation ce) {
		boolean isCrossed = false;
		double x = (ce.getB() - this.b ) / ( this.a - ce.getA() );
		if((x > xStart && x < xEnd) ||( x > xEnd && x < xStart) ) isCrossed = true;
		return isCrossed;
	}

}
