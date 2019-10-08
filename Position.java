package uk.ac.ed.inf.powergrab;
import java.lang.Math;

public class Position {   
	
	
	public double latitude;
	public double longitude;
	double degree = 0;
	
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

	//gets degree(in double) of direction
	public double getDegree(Direction direction) {
		
		//set East as 0 and north as 90 (in degrees)
		if (direction == Direction.E) {
			degree = 0;
		}
		else if (direction == Direction.ENE) {
			degree = 22.5;
		}
		else if (direction == Direction.NE) {
			degree = 45;
		}
		else if (direction == Direction.NNE) {
			degree = 67.5;
		}
		else if (direction == Direction.N) {
			degree = 90;
		}
		else if (direction == Direction.NNW) {
			degree = 112.5;
		}
		else if (direction == Direction.NW) {
			degree = 135;
		}
		else if (direction == Direction.WNW) {
			degree = 157.5;
		}
		else if (direction == Direction.W) {
			degree = 180;
		}
		else if (direction == Direction.WSW) {
			degree = 202.5;
		}
		else if(direction == Direction.SW) {
			degree = 225;
		}
		else if (direction == Direction.SSW) {
			degree = 247.5;
		}
		else if (direction == Direction.S) {
			degree = 270;
		}
		else if (direction == Direction.SSE) {
			degree = 292.5;
		}
		else if (direction == Direction.SE) {
			degree = 315;
		}
		else if(direction == Direction.ESE) {
			degree = 337.5;
		}
		return degree;
		
	}

	public Position nextPosition(Direction direction) {
		//initialise current position
		Position currentPos = new Position(this.latitude, this.longitude);
		
		//values to be added to current latitude/longitude
		double updateLat = 0.0003 * Math.sin(Math.toRadians(getDegree(direction)));
		double updateLong = 0.0003 * Math.cos(Math.toRadians(getDegree(direction)));
		Position nextPos = new Position(currentPos.latitude + updateLat, currentPos.longitude + updateLong);
		
		return nextPos;
		
	}
		
	public boolean inPlayArea() {
		return inLatitude() && inLongitude(); 
	}
	public boolean inLatitude() {
		return 55.942617 < this.latitude && this.latitude < 55.946233;
	}
	public boolean inLongitude() {
		return -3.192473 < this.longitude && this.longitude < -3.184319;
	}
}
