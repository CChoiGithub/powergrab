package uk.ac.ed.inf.powergrab;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.lang3.ArrayUtils;

public class MapInf {
	
	//public String mapString;
	public String mapString;
	public String mapSource;
	public MapInf(String mapString) throws IOException {

		this.mapString = mapString;
		
	}

	public String getMapsource() {
		return this.mapSource;
	}
	
	//returns FeatureCollection in accordance with the given URL
	public FeatureCollection getFc() throws IOException{
		
		
		URL mapUrl  = new URL(mapString);
		HttpURLConnection conn = (HttpURLConnection) mapUrl.openConnection();
		//http settings
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
//		conn.connect();
//		this.mapSource = conn.getInputStream().toString();

		//parse external data
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		this.mapSource = response.toString();

		FeatureCollection fc;
		fc = FeatureCollection.fromJson(this.mapSource);

		return fc;
					
	}
	
	
}
