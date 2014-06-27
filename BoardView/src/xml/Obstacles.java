package xml;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Point;

public class Obstacles {
	private String obstacleDistribution;
	private int minObstacleNumber;
	private int maxObstacleNumberx;
	private ArrayList<Point> obstaclePoints = new ArrayList<Point>();
	private String childrenDescription;
	private String type;

	public String getChildrenDescription() {
		return childrenDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String typ) {
		this.type = typ;
	}

	public String getObstacleDistribution() {
		return obstacleDistribution;
	}

	public void setObstacleDistribution(String figurVerteilung) {
		this.obstacleDistribution = figurVerteilung;
	}

	public int getMinObstacleNumber() {
		return minObstacleNumber;
	}

	public Obstacles(String type, String childrenDescription) {
		super();
		this.childrenDescription = childrenDescription;
		this.type = type;
	}

	public void setMinObstacleNumber(int figurMin) {
		this.minObstacleNumber = figurMin;
	}

	public int getMaxObstacleNumber() {
		return maxObstacleNumberx;
	}

	public void setMaxObstacleNumber(int figurMax) {
		this.maxObstacleNumberx = figurMax;
	}

	public ArrayList<Point> getFigurPoints() {
		return obstaclePoints;
	}

	public void addObstaclePoint(Point figurPoint) {
		this.obstaclePoints.add(figurPoint);
	}

}
