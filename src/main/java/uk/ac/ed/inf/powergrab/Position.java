package uk.ac.ed.inf.powergrab;

import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Point;

import java.util.HashMap;
import java.util.Map;

public class Position {

  public double latitude;
  public double longitude;

  public Position(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  //getters
  public Position getPosition(){
    return new Position(this.latitude, this.longitude);
  }

  //setters
  public void setPosition(Position position){
    this.latitude = position.latitude;
    this.longitude = position.longitude;
  }
  public double getLongitude(Position position) {
    return this.longitude;
  }

  public static final String[] dirString = {"E", "ENE", "NE", "NNE",
                                            "N", "NNW", "NW", "WNW",
                                            "W", "WSW", "SW", "SSW",
                                            "S", "SSE", "SE", "ESE"};

  public static final Direction[] dir = {Direction.E, Direction.ENE, Direction.NE, Direction.NNE,
                                         Direction.N, Direction.NNW, Direction.NW, Direction.W,
                                         Direction.W, Direction.WSW, Direction.SW, Direction.SSW,
                                         Direction.S, Direction.SSE, Direction.SE, Direction.ESE};

   //create a map to get a corresponding string value from a direction value
  public static Map<Direction, String> dtsMap(){
    Map<Direction, String> dirToString = new HashMap<>();
    for (int i = 0; i < 16; i++) {
      dirToString.put(dir[i], dirString[i]);
    }
    return dirToString;
  }
  //moves to next position according to the given direction
  public Position nextPosition(Direction direction) {
    //values to be added to current latitude/longitude
    double updateLat = 0.0003 * Math.sin(Math.toRadians(direction.getDegree()));
    double updateLong = 0.0003 * Math.cos(Math.toRadians(direction.getDegree()));
    Position nextPos = new Position(this.getPosition().latitude + updateLat, this.getPosition().longitude + updateLong);

    return nextPos;
  }

  public static Position convertPointToPos(Point pt){
    Position pos = new Position(pt.coordinates().get(1), pt.coordinates().get(0));
    return pos;
  }

  //calculates the angle between two points (from current to target, E = 0)
  public Direction calcDirection(Position targetPoint){
    double theta = Math.atan2(targetPoint.longitude - this.longitude, targetPoint.latitude - this.latitude);
    double angle = Math.toDegrees(theta);
    if(angle < 0){
      angle += 360;
    }
    return directionByAngle(angle);
  }

  public static Direction directionByAngle(double angle){

    if (appxEqual(angle, 0d)) {return Direction.E;}
    if (appxEqual(angle, 22.5)) {return Direction.ENE;}
    if (appxEqual(angle, 45d)) {return Direction.NE;}
    if (appxEqual(angle, 67.5)) {return Direction.NNE;}

    if (appxEqual(angle, 90)) {return Direction.N;}
    if (appxEqual(angle, 112.5)) {return Direction.NNW;}
    if (appxEqual(angle, 135d)) {return Direction.NW;}
    if (appxEqual(angle, 157.5)) {return Direction.WNW;}

    if (appxEqual(angle, 180d)) {return Direction.W;}
    if (appxEqual(angle, 202.5)) {return Direction.WSW;}
    if (appxEqual(angle, 225d)) {return Direction.SW;}
    if (appxEqual(angle, 257.5)) {return Direction.SSW;}

    if (appxEqual(angle, 270d)) {return Direction.S;}
    if (appxEqual(angle, 292.5)) {return Direction.SSE;}
    if (appxEqual(angle, 315d)) {return Direction.SE;}
    if (appxEqual(angle, 337.5)) {return Direction.ESE;}
    else return Direction.E;
  }

  public static String dirToStr(Direction d){

    return dtsMap().get(d);

  }
  public static double squareDistance(Position p1, Position p2) {
    double distance = Math.sqrt(Math.pow((p1.longitude - p2.longitude), 2) +
                               (Math.pow((p1.latitude - p2.latitude), 2)));
    return distance;
  }
  public static boolean appxEqual(double d1, double d2){
    return Math.abs(d1 - d2) < 11.25;
  }

  public static String posToString(Position pos){
    String postring = "["+Double.toString(pos.latitude) +","+ Double.toString(pos.longitude)+"]";
    return postring;
  }
  public static String posToStringReverse(Position pos){
    String postring = "["+Double.toString(pos.longitude) +","+ Double.toString(pos.latitude)+"]";
    return postring;
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