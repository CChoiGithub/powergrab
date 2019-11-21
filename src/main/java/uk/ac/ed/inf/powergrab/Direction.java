package uk.ac.ed.inf.powergrab;

public class Direction {

  //declaring direction constants
  public final static Direction N = new Direction(0d);
  public final static Direction NNE = new Direction(22.5);
  public final static Direction NE = new Direction(45d);
  public final static Direction ENE = new Direction(67.5);

  public final static Direction E = new Direction(90d);
  public final static Direction ESE = new Direction(112.5);
  public final static Direction SE = new Direction(135d);
  public final static Direction SSE = new Direction(157.5d);

  public final static Direction S = new Direction(180d);
  public final static Direction SSW = new Direction(202.5);
  public final static Direction SW = new Direction(225d);
  public final static Direction WSW = new Direction(247.5);

  public final static Direction W = new Direction(270d);
  public final static Direction WNW = new Direction(292.5);
  public final static Direction NW = new Direction(315d);
  public final static Direction NNW = new Direction(337.5);

  public double degree;

  public Direction(double degree) {
    this.degree = degree;
  }

  public double getDegree() {
    return degree;
  }
}
