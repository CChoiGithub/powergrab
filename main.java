package uk.ac.ed.inf.powergrab;

import javax.annotation.Generated;
//for testing purposes

public class main{
	
	public static void main(String args[]) {
		Position p = new Position(55.944425, -3.188396);
		System.out.println(p.latitude);
		System.out.println(p.longitude);
		System.out.println(p.getDegree(Direction.S));

	}
}
