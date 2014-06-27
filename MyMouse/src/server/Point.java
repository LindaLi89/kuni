package server;

public class Point {
	private int positionX;
	private int positionY;
	
	
	
	public Point() {
		super();
	}
	public Point(int positionX, int positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	@Override
	public String toString() {
		return "Point [positionX=" + positionX + ", positionY=" + positionY
				+ "]";
	}
	
	
}
