package uk.ac.ed.inf.powergrab;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drone {

    public Position position;
    public double power;
    public double coin;
    public String type;
    public int moveNum;
    public int seed;
    public FeatureCollection map;
    Position centre = new Position(55.944425, -3.188396);

    public final Direction[] dir = {Direction.E, Direction.ENE, Direction.NE, Direction.NNE,
            Direction.N, Direction.NNW, Direction.NW, Direction.WNW,
            Direction.W, Direction.WSW, Direction.SW, Direction.SSW,
            Direction.S, Direction.SSE, Direction.SE, Direction.ESE};

    public ArrayList<Position> path = new ArrayList<>();
    Random rnd = new Random(this.seed);
    //constructors
    public Drone(FeatureCollection map, Position position, int seed, String type) {
        this.map = map;
        this.type = type;
        this.power = 250;
        this.coin = 0;
        this.moveNum = 0;
        this.position = position;
    }
    //setters
    public void setType(String type) {
        this.type = type;
    }
    public void setPower(double power){
        this.power = power;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public void setMap(FeatureCollection map){
        this.map = map;
    }
    public void setCoin(double coin){
        this.coin = coin;
    }
    public void setMoveNum(int moveNum){
        this.moveNum = moveNum;
    }
    public void setSeed(int seed){
        this.seed = seed;
    }

    //getters
    public double getPower() {
        return this.power;
    }
    public double getCoin() {
        return this.coin;
    }
    public int getMoveNum() {
        return this.moveNum;
    }
    public Position getPosition() {
        return this.position;
    }

    public FeatureCollection getMap() {
        return map;
    }
    public int getSeed() {
        return this.seed;
    }

    public void createPath(ArrayList<Position> path){
        String coordinates =  "";
        for (int i = 0; i < path.size(); i++) {
            if(i < path.size()-1){
                coordinates += Position.posToStringReverse(path.get(i))+",";
            }if(i == path.size()-1){
                coordinates += Position.posToStringReverse(path.get(i));
            }
        }

        String flightPath =
                "{ \"type\": \"Feature\", \"properties\": { }, " +
                        "\"geometry\":{\"type\": \"LineString\"," +
                        "			\"coordinates\":["+coordinates+"]" +
                        "}" + "}";



        System.out.println(flightPath);
    }

}
