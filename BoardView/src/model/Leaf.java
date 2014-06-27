package model;

public class Leaf extends AbstractObject {
	protected static int points = 10;

	public static int getPoints() {
		return points;
	}

	public static void setPoints(int points) {
		Leaf.points = points;
	}
	
	public String getName(){
		return "Leaf";
	}
	
	public String getImg(){
		return "..\\..\\..\\icons\\pilz02.png";
	}

}
