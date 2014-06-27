package xml;

import org.eclipse.swt.graphics.Point;

public class WorldData {
	private int width;
	private int height;
	private String name;
	private boolean randomMushrooms;
	private Point goal;

	public Point getGoal() {
		return goal;
	}

	public void setGoal(Point goal) {
		this.goal = goal;
	}

	public int getWidth() {
		return width;
	}

	public boolean isRandomMushrooms() {
		return randomMushrooms;
	}

	public void setRandomMushrooms(boolean randomMushrooms) {
		this.randomMushrooms = randomMushrooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
