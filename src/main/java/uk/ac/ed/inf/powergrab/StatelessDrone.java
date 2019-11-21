package uk.ac.ed.inf.powergrab;

import java.util.Random;

public class StatelessDrone {
	
	private Position position;
	private double power;
	private double coin;
	private Type type;
	private int moveNum;
	
	//constructor
	public StatelessDrone(Position position, int seed, Type type) {
		this.type = type;
		this.power = 250;
		this.coin = 0;
		this.moveNum = 0;
		java.util.Random rnd = new Random(seed);
		
		
	}
	
	//goes to next position according to direction
	public Position move(Position pos, Direction dir) {
		pos = pos.nextPosition(dir); //use Direction.nextposition
		this.power -= 1.25; //-1.25 units of power per move
		return pos;
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
	public Position scanLoc(MapInf map) {
		//MapInfo class에서 저장했던 coin과 power를 기반으로,
		//Direction class에 선언되어 있는 16방향에 있는 충전소들 중
		//가장 가까우면서도 power와 coin을 가장 많이 주는 충전소(bestPos)의 위치를 찾아서 리턴하는 함수를 구현하려고 합니다.
		Position bestPos = null;
		return bestPos;
	}
	
	

}
