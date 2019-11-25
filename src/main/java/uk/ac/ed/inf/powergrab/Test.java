package uk.ac.ed.inf.powergrab;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Test {

	public static final String[] startInfos = new String[]{
			"01 01 2019 55.944425 -3.188396 6789 stateless",
			"15 09 2019 55.944427 -3.188394 6789 stateless",
			"03 03 2019 55.944421 -3.188395 6789 stateless",
			"23 01 2019 55.9444 -3.1870 6789 stateless",
			"28 02 2019 55.944221 -3.189364 6789 stateless",
			"11 10 2019 55.944425 -3.188396 6789 stateless",
			"11 10 2019 55.944425 -3.188396 5678 stateful",
			"12 08 2019 55.944425 -3.188396 5678 stateful",
			"21 06 2019 55.944425 -3.188396 5678 stateful"


	};
	public static void main(String[] args) throws IOException {

		/*
		String map11 = "http://homepages.inf.ed.ac.uk/stg/powergrab/2019/01/01/powergrabmap.geojson";
		MapInf m11 = new MapInf(map11);
		m11.getCoinsNPower();
//		//m11.getCoords();
//		System.out.println(m11.getMapsource());
*/
		//test
		//takeInput();
		playDrone(startInfos[8].split(" "));
	}

	public static void takeInput() throws IOException {

		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Enter dd mm yyyy, starting point, seed and drone type: ");

		String[] inputString = myObj.nextLine().split(" ");

		playDrone(inputString);

	}

	public static void playDrone(String[] inputString) throws IOException{
		ParseInput pi = new ParseInput(inputString);
		String mapUrl= String.format("http://homepages.inf.ed.ac.uk/stg/powergrab/%s/%s/%s/powergrabmap.geojson", pi.yyyy, pi.mm, pi.dd);

		if(pi.getType().equals("stateless")){
			//play stateless drone
			MapInf map = new MapInf(mapUrl);
			StatelessDrone sld = new StatelessDrone(map.getFc(), pi.p, pi.seed, pi.type);
			sld.bestMove(map);
		}else if(pi.getType().equals("stateful")){
			MapInf map = new MapInf(mapUrl);
			StatefulDrone sfd = new StatefulDrone(map.getFc(), pi.p, pi.seed, pi.type);
			sfd.bestMove();
		}else {
			System.out.println("droneTypeInputError: Wrong drone type!");
		}

	}
}
