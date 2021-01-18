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
	public double getXStart() {
		return xStart;
	}
	public double getXEnd() {
		return xEnd;
	}
	public boolean check(Equation e) {
		boolean isCrossed = false;
		double x = (e.getB() - this.b ) / ( this.a - e.getA() );
		if((x > e.getXStart() && x < e.getXEnd()) ||( x > e.getXEnd() && x < e.getXStart() ) ) 
		{
			if((x > this.xStart && x < this.xEnd ||( x > this.xEnd && x < this.xStart )))
			{
				isCrossed = true;
			}	
		}
		return isCrossed;
	}
	public void setDomain(double xStart, double xEnd) {
		// TODO Auto-generated method stub
		this.xStart = xStart;
		this.xEnd = xEnd;
	}

}
