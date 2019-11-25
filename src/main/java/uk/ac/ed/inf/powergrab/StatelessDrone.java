package uk.ac.ed.inf.powergrab;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.geometry.Pos;
import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class StatelessDrone extends Drone {

	List<Feature> stations = this.getMap().features();

	public StatelessDrone(FeatureCollection map, Position position, int seed, String type) {
		super(map, position, seed, type);
		this.seed = seed;
	}

	public void bestMove(MapInf map) throws IOException {

		//create a local copy of the features as a list of features

		while (this.getPower() > 0 && this.getMoveNum() < 250) {

			int check = 0;
			//dir to arraylist of directions
			ArrayList<Direction> directions = new ArrayList<>();
			for (int i = 0; i < dir.length; i++) {
				directions.add(dir[i]);
			}
			while(true){
				if(getMoveNum() == 250){break;}
				ArrayList<Integer> positive = new ArrayList<>();
				//scan and find out the position within range of 0.0003(each step) + 0.00025(radius of charging stations)
				for (int i = 0; i < stations.size(); i++) {
					Feature feature = stations.get(i);
					Position position = Position.convertPointToPos(((Point) feature.geometry()));
					double d = Position.squareDistance(this.getPosition(), position);
					double coins = feature.getProperty("coins").getAsDouble();
					double power = feature.getProperty("power").getAsDouble();
					if (d < 0.0004 && coins > 0) {
						positive.add(i);
					}
				}
				path.add(this.getPosition());//add initial location to flightpath
				if (positive.size() > 0) { //if any positive chargers exist
					for (int i = 0; i < positive.size(); i++) {
						Position goodTarget = Position.convertPointToPos((Point) (stations.get(positive.get(rnd.nextInt(positive.size()))).geometry())); //get random positive charger
						Direction nextDir = this.getPosition().calcDirection(goodTarget); //gets the direction towards the station from drone's current position
						Position next = this.getPosition().nextPosition(nextDir);
						if (next.inPlayArea() && scanDanger(nextDir).equals("go")) {
							this.setPosition(next);
							this.setMoveNum(this.getMoveNum() + 1);
							this.setPower(this.getPower() - 1.25);
							path.add(this.getPosition());
							this.updateFeatures();
							System.out.println("Move count: " + this.getMoveNum());
							System.out.println("coin: " +this.getCoin());
							System.out.println("power: " + this.getPower());
							break;
						} else {
							randomMove(directions);
						}
					}
				}else{
					randomMove(directions);

				}
			}

			}

			if (this.getMoveNum() == 250) {
			createPath(path);
			}

		}

	public void randomMove(ArrayList<Direction> directions) {
		ArrayList<Direction> localDirections = new ArrayList<>();
		//copy direction to localDirections to editing purposes
		for (int i = 0; i < directions.size(); i++) {
			localDirections.add(directions.get(rnd.nextInt(directions.size())));
		}
		for (int i = 0; i < localDirections.size(); i++) {
			Position next = this.getPosition().nextPosition(localDirections.get(i)); //get random target position
			Direction toNext = this.getPosition().calcDirection(next);
			if (next.inPlayArea() && scanDanger(toNext).equals("go")) {
				this.setPosition(this.getPosition().nextPosition(toNext));
				this.setPower(this.getPower() - 1.25);
				path.add(this.getPosition());
				System.out.println("Move count: " + this.getMoveNum());
				System.out.println("coin: " +this.getCoin());
				System.out.println("power: " + this.getPower());
				this.updateFeatures();
				this.setMoveNum(this.getMoveNum() + 1);
				break;
			} else {
				localDirections.remove(toNext);
				if(localDirections.size() == 0){
					localDirections = directions;
				}
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
			if (distance < 0.0003 && feature.getProperty("coins").getAsDouble() < 0) {
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

