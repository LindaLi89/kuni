package model;

import util.Constants;

public class Mushroom extends AbstractObject {
	protected static int points = -10;

	public static int getPoints() {
		return points;
	}

	public static void setPoints(int points) {
		Mushroom.points = points;
	}
	
	public String getName(){
		return Constants.Objects.mushroom;
	}
	
	public String getImg(){
		return "..\\..\\..\\icons\\pilz01.png";
	}
}
