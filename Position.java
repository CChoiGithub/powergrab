package uk.ac.ed.inf.powergrab;
import java.lang.Math;

public class Position {   
	
	
	public double latitude;
	public double longitude;
	
	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//getters
	public double getLatitude(Position position) {
		return this.latitude;
	}
	
	public double getLongitude(Position position) {
		return this.longitude;
	}
	
	public double getDegree(Direction direction) {
		double degree = 0;
		if (direction.direction == direction.N){ 
			degree = 0;}
		else if (direction.direction == direction.NNE) {
			degree = 22.5;
		}
		else if(direction.direction == direction.NE) {
			degree = 45;
		}
		else if(direction.direction == direction.ENE) {
			degree = 67.5;
		}
		else if (direction.direction == direction.E) {
			degree = 90;
		}
		else if (direction.direction == direction.ESE) {
			degree = 112.5;
		}
		else if (direction.direction == direction.SE) {
			degree = 135;
		}
		else if (direction.direction == direction.SSE) {
			degree = 157.5;
		}
		else if (direction.direction == direction.S) {
			degree = 180;
		}
		else if (direction.direction == direction.SSW) {
			degree = 202.5;
		}
		else if(direction.direction == direction.SW) {
			degree = 225;
		}
		else if (direction.direction == direction.WSW) {
			degree = 247.5;
		}
		else if (direction.direction == direction.W) {
			degree = 270;
		}
		else if (direction.direction == direction.WNW) {
			degree = 292.5;
		}
		else if (direction.direction == direction.NW) {
			degree = 315;
		}
		else if(direction.direction == direction.NNW) {
			degree = 337.5;
		}
		return degree;
	}
	public Position nextPosition(Direction direction) {
		this.latitude = latitude + 0.0003 * Math.cos(this.getDegree(direction));
		this.longitude = longitude + 0.0003 * Math.sin(this.getDegree(direction));
		return this;
	}
	
	public boolean inPlayArea() {
		return (-3.192473 < this.latitude && this.latitude < -3.184319 &&
		55.942617 < this.longitude && this.longitude < 55.942633);
		}
	
}
