package uk.ac.ed.inf.powergrab;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		
		String map11 = "http://homepages.inf.ed.ac.uk/stg/powergrab/2019/01/01/powergrabmap.geojson";
		MapInf m11 = new MapInf(map11);
		m11.getfc(map11);
		
		System.out.println(m11.getMapsource());
		//test
		
	
		
	}
}
