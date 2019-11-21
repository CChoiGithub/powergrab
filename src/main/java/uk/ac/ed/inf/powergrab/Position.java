package uk.ac.ed.inf.powergrab;

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
  
  //moves to next position according to the given direction
  public Position nextPosition(Direction direction) {
    //initialise current position
    Position currentPos = new Position(this.latitude, this.longitude);

    //values to be added to current latitude/longitude
    double updateLat = 0.0003 * Math.sin(Math.toRadians(direction.getDegree()));
    double updateLong = 0.0003 * Math.cos(Math.toRadians(direction.getDegree()));
    Position nextPos = new Position(currentPos.latitude + updateLat, currentPos.longitude + updateLong);

    return nextPos;

  }
  //checks if the drone is within the playarea
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