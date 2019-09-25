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

	//gets degree(in double) of direction
	public double getDegree(Direction direction) {
		double degree = 0;
		
		double rad = Math.PI;
		//2 * Math.toDegrees(rad)
		
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
	
	public double getCosValue(Direction direction) {
		double cosValue; 
		double angle = getDegree(direction);
		
		if(angle == 90) {
			cosValue = 0;
			return cosValue;
		}else if (angle== 270) {
			cosValue = 0;
			return cosValue;
		}else if (angle== 180) {
			cosValue = -1;
			return cosValue;
		}else if (angle== 360) {
			cosValue = 1;
			return cosValue;
		}else if (angle== 0) {
			cosValue = 1;
			return cosValue;
		}else cosValue = Math.cos(Math.toRadians(getDegree(direction)));
			return cosValue;
	}
	
	public double getSinValue(Direction direction) {
		double sinValue;
		double angle = getDegree(direction);
		
		if (angle== 90) {
			sinValue = 1;
			return sinValue;
		}else if (angle== 180) {
			sinValue = 0;
			return sinValue;
		}else if (angle== 360) {
			sinValue = 0;
			return sinValue;
		}else if (angle== 0) {
			sinValue = 0;
			return sinValue;
		}else if (angle== 270) {
			sinValue = -1;
			return sinValue;
		}else sinValue = Math.sin(Math.toRadians(getDegree(direction)));
		
			return sinValue;
	}
	
	public Position nextPosition(Direction direction) {
		
		double updateLat = 0.0003 * getCosValue(direction);
		double updateLong = 0.0003 * getSinValue(direction);
		
		Position nextPos = new Position(this.latitude + updateLat, this.longitude + updateLong);
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
