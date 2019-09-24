package uk.ac.ed.inf.powergrab;

public class Direction {
	
	public Direction degree;
	
	Direction(Direction direction){
		this.degree = direction;
	}
	
	//initialise directions
	public final static double N = 0;
	N = (Direction)0;
	public final static double NNE = 22.5; 
	public final static double NE = 45; 
	public final static double ENE = 67.5; 
	public final static double E = 90; 
	public final static double ESE = 112.5; 
	public final static double SE = 135;
	public final static double SSE = 157.5; 
	public final static double S = 180; 
	public final static double SSW = 202.5; 
	public final static double SW = 225; 
	public final static double WSW = 247.5;
	public final static double W = 270;
	public final static double WNW = 292.5;
	public final static double NW = 315;
	public final static double NNW = 337.5;
	
}
