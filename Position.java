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

	//gets degree(double) of direction
	public double getDegree(Direction direction) {
		double degree = 0;
		double rad = Math.PI;
		
		if (direction == null) { direction = Direction.N; }
		else if (direction == Direction.N){ 
			degree = 2 * Math.toDegrees(rad); return degree;}
		else if (direction == Direction.NNE) {
			degree = 2 * Math.toDegrees(rad) + 22.5;
			return degree;
		}
		else if(direction == Direction.NE) {
			degree = 2 * Math.toDegrees(rad) + 45;
			return degree;
		}
		else if(direction == Direction.ENE) {
			degree = 2 * Math.toDegrees(rad) + 67.5;
			return degree;
		}
		else if (direction == Direction.E) {
			degree = 2 * Math.toDegrees(rad) + 90;
			return degree;
		}
		else if (direction == Direction.ESE) {
			degree = 2 * Math.toDegrees(rad) + 112.5;
			return degree;
		}
		else if (direction == Direction.SE) {
			degree = 2 * Math.toDegrees(rad) + 135;
			return degree;
		}
		else if (direction == Direction.SSE) {
			degree = 2 * Math.toDegrees(rad) + 157.5;
			return degree;
		}
		else if (direction == Direction.S) {
			degree = 2 * Math.toDegrees(rad) + 180;
			return degree;
		}
		else if (direction == Direction.SSW) {
			degree = 2 * Math.toDegrees(rad) + 202.5;
			return degree;
		}
		else if(direction == Direction.SW) {
			degree = 2 * Math.toDegrees(rad) + 225;
			return degree;
		}
		else if (direction == Direction.WSW) {
			degree = 2 * Math.toDegrees(rad) + 247.5;
			return degree;
		}
		else if (direction == Direction.W) {
			degree = 2 * Math.toDegrees(rad) + 270;
			return degree;
		}
		else if (direction == Direction.WNW) {
			degree = 2 * Math.toDegrees(rad) + 292.5;
			return degree;
		}
		else if (direction == Direction.NW) {
			degree = 2 * Math.toDegrees(rad) + 315;
			return degree;
		}
		else if(direction == Direction.NNW) {
			degree = 2 * Math.toDegrees(rad) + 337.5;
			return degree;
		}return degree;
		
	}
	
	public Position nextPosition(Direction direction) {
		
		Position nextPos = new Position(this.latitude + 0.0003 * Math.cos(getDegree(direction)),
										this.longitude + 0.0003 * Math.sin(getDegree(direction)));
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
