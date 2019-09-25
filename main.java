package uk.ac.ed.inf.powergrab;

import static org.junit.Assert.assertTrue;

import javax.annotation.Generated;

public class main{
	
	public static void main(String args[]) {

		final Position p0 = new Position(55.944425, -3.188396);
		Position p1 = p0.nextPosition(Direction.SSE);
		System.out.println(p1.latitude);
		System.out.println(p1.longitude);
		
		Position p2 = p1.nextPosition(Direction.S);
		System.out.println(p2.latitude);
		System.out.println(p2.longitude);
		
		Position stop = new Position(55.94384783614024,-3.1882811949702905);


	}
}
