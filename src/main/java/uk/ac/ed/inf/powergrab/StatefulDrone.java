package uk.ac.ed.inf.powergrab;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import javafx.geometry.Pos;

import java.lang.reflect.Array;
import java.util.*;

public class StatefulDrone extends Drone{

    List<Feature> stations = this.getMap().features();
    Direction[] rightDirections = {Direction.E, Direction.N, Direction.W, Direction.S};
    public StatefulDrone(FeatureCollection map, Position position, int seed, String type){
        super(map, position, seed, type);
    }

    public void bestMove() {
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        double score = 0;
        //score is calculated by (power + 3 * coins) / 10000 * distance
        Map<Double, Integer> indexByScore = new HashMap<>();
        ArrayList<Direction> directions = new ArrayList<>();
        for (int i = 0; i < dir.length; i++) {
            directions.add(dir[i]);
        }
        while (this.getMoveNum() < 250 && this.power > 0) {
            for (int i = 0; i < stations.size(); i++) {
                Feature feature = stations.get(i);
                Position pos = Position.convertPointToPos(((Point) feature.geometry()));
                double distance = Position.squareDistance(this.getPosition(), pos);
                score = (feature.getProperty("coins").getAsDouble() * 100 + feature.getProperty("power").getAsDouble() * 10) / ((distance+0.000000000000000000001) * 1000);
                indexByScore.put(score, i); //store scores by index of position
            }
            Map<Double, Integer> indexSortedByScore = new TreeMap<Double, Integer>(Collections.reverseOrder()); //has index of index of positions: the actual index can be retrieved by indexSortedByScore.get(key)
            indexSortedByScore.putAll(indexByScore);
            Double[] keyset = indexSortedByScore.keySet().toArray(new Double[indexSortedByScore.size()]);
            int[] indices = new int[indexSortedByScore.size()];
            for (int i = 0; i < indexSortedByScore.size(); i++) {
                if(keyset[i] > 0){
                    indices[i] = indexSortedByScore.get(keyset[i]);
                }else{indices[i] = indices[i-1];} // filling negative stations with the least positive one as they are not to be visited
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ranking stations by "score" and taking the indices is done
            path.add(this.getPosition());       //write initial position before moving
            for (int i = 0; i < indices.length; i++) {
                if(this.getMoveNum() == 250){break;}
                Position target = Position.convertPointToPos((Point)(stations.get(indices[i]).geometry()));
                Map<Double, Direction> dirOptions = new HashMap<>();
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                while(true) {
                    if (this.getMoveNum() == 250) { break;}
                    for (Direction direction : directions) {
                        if (this.getMoveNum() == 250) { break;}
                        Position next = this.getPosition().nextPosition(direction);
                        double distanceAfterMove = Position.squareDistance(next, target);
                        double distanceBeforeMove = Position.squareDistance(this.getPosition(), target);
                        if (distanceAfterMove < distanceBeforeMove && scanDanger(direction).equals("go")) {
                            dirOptions.put(distanceAfterMove, direction);
                        }
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////getting valid options Map<distance, direction> is done: now sort and get the best one.
                    Map<Double, Direction> dirOptionsSorted = new TreeMap<>(dirOptions);
                    double shortestKey = dirOptionsSorted.keySet().stream().findFirst().get();
                    Direction bestDirection = dirOptionsSorted.get(shortestKey);  /////////////////////////////////got the best direction! now move to that direction
                    this.setPosition(this.getPosition().nextPosition(bestDirection)); //move
                    path.add(getPosition()); //add to flightpath
                    this.setMoveNum(this.getMoveNum() + 1);
                    System.out.println("MoveNum: "+this.getMoveNum());
                    this.updateFeatures();
                    if(Position.squareDistance(this.getPosition(), target) < 0.00025){
                        //this.updateFeatures();
                        System.out.println("Coins: "+this.getCoin());
                        System.out.println("power: "+this.getPower());
                        break;
                    }
                }
            }
            if (this.getMoveNum() == 250) {
                createPath(path);
            }
        }
    }

    public void updateFeatures() {
        Map<Double, Integer> distIndex = new HashMap<>();
        for (int i = 0; i < stations.size(); i++) {
            Feature feature = stations.get(i);
            Position location = Position.convertPointToPos(((Point) feature.geometry()));
            double distance = Position.squareDistance(this.getPosition(), location);
            if (distance < 0.00025) {
                distIndex.put(distance, i);
            }
            //sort and get the nearest location
        }
        if (distIndex.size() > 0 && !(this.getCoin() < 0)) {
            Map<Double, Integer> distIndexSorted = new TreeMap<>(distIndex);
            double firstKey = distIndexSorted.keySet().stream().findFirst().get();
            int nearestIndex = distIndexSorted.get(firstKey);
            this.setCoin(this.getCoin() + stations.get(nearestIndex).getProperty("coins").getAsDouble());
            this.setPower(this.getPower() + stations.get(nearestIndex).getProperty("power").getAsDouble());
            if (stations.get(nearestIndex).getProperty("coins").getAsDouble() > 0 && stations.get(nearestIndex).getProperty("power").getAsDouble() > 0) {
                stations.get(nearestIndex).removeProperty("coins");
                stations.get(nearestIndex).addNumberProperty("coins", 0);
                stations.get(nearestIndex).removeProperty("power");
                stations.get(nearestIndex).addNumberProperty("power", 0);
            }
        }
    }
    public String scanDanger(Direction direction) {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            Feature feature = stations.get(i);
            Position location = Position.convertPointToPos(((Point) feature.geometry()));
            double distance = Position.squareDistance(this.getPosition().nextPosition(direction), location);
            if (distance < 0.00031 && feature.getProperty("coins").getAsDouble() < 0) {
                indices.add(i);
            }
        }
        if (indices.size() > 0) {
            return "avoid";
        } else {
            return "go";
        }
    }
}
