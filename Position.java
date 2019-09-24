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
	
	//convert direction to degree
	public double dirToDeg(Direction direction) {
		if(direction.degree == Direction.N) { return 0;}
	}
	
	public Position nextPosition(Direction direction) {
		this.latitude = latitude + 0.0003 * Math.cos(double(direction.degree));
		this.longitude = longitude + 0.0003 * Math.sin(direction.degree);
		return this;
	}
	
	public boolean inPlayArea() {
		return (-3.192473 < this.latitude && this.latitude < -3.184319 &&
		55.942617 < this.longitude && this.longitude < 55.942633);
		}
	
}
