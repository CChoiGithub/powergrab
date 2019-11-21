package uk.ac.ed.inf.powergrab;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;

public class MapInf {
	
	//public String mapString;
	public String mapString;
	public String mapSource;
	//여기 feature에 access하여 각feature 안의 id별로 location의 coin과 power를 저장
	//public double coin = features.getProperty("coins").getAsFloat();

	//constructor
	public MapInf(String mapString) throws IOException {
		
		//mapString으로 넘어온 URL주소를 읽어서 JSon 파일을 저장하는 함수입니다.
		//this.mapString = mapString;
		this.mapString = mapString;
		
		/*
		try (PrintWriter out = new PrintWriter("/home/chanwoo/Documents/ILP/19-01-01.txt")) {
		    out.println(mapSource);
		}
		*/
		//collects FeatureCollection data from mapsource
		
	}
	
	public String getMapsource() {
		return this.mapSource;
	}
	
	//returns FeatureCollection in accordance with the given URL
	public FeatureCollection getfc(String mapString) throws IOException{
		
		
		URL mapUrl  = new URL(mapString);
		HttpURLConnection conn = (HttpURLConnection) mapUrl.openConnection();
		//http settings
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		this.mapSource = conn.getInputStream().toString();
		FeatureCollection fc;
		fc = FeatureCollection.fromJson(this.mapSource);
		return fc;
					
	}
	//returns the values of coins and power of each and every location as an array
	public double[][] getCoinsNPower() throws IOException{
		double[][] cnp = new double[50][2];
		
		List<Feature> features = getfc(mapString).features();
		
		for(int i = 0; i < 50; i++) {
			double coins0 = features.get(i).getProperty("coins").getAsDouble();
			double power0 = features.get(i).getProperty("power").getAsDouble();
				cnp[i][0] = coins0;
				cnp[i][1] = power0;
		}
		return cnp;
		
	}
	//returns only coordinates of each location as a LineString
	public LineString getCoords() throws IOException{
		List<Feature> features = getfc(mapString).features();
		List<Point> coordsList = null;
		for(int i = 0; i < 50; i++) {
			String geo = features.get(i).geometry().toJson();
			Point coo = Point.fromJson(geo);
			coordsList.add(i, coo);
		}
		LineString coords = LineString.fromLngLats(coordsList);
		return coords;
	}
	
	
}
