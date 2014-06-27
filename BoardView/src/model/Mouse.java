package model;

import model.exceptions.EatableObjectOnPlaceException;

import org.eclipse.swt.graphics.Point;

import ctrl.Controller;

/**
 * @author Dörsam
 *
 */
public abstract class Mouse extends AbstractObject {

	protected boolean active = false;
	protected boolean greedy = true;
	protected int direction = 1;
	protected Score score;
	protected int id;

	protected Mouse(String name, String image){
		this.name = name;
		this.img = image;
		score = new Score();
		score.mouse = this;
		setDefaultPosition();
	}

	public boolean setPosition(int x, int y) {
		if(isPositionAllowed(x,y)) {
			this.positionX = x;
			this.positionY = y;
			return true;
		}
		else
			return false;
	}
	
	public void setDefaultPosition() {
		this.positionX = -1;
		this.positionY = -1;
	}
	
	public void setActive(boolean active){
		this.active=active;
	}
	
	public Point getPosition() {
		return new Point(positionX, positionY);
	}
	
	private boolean isPositionAllowed(int x, int y) {
		return (onBoard(x, y) && !onTree(x, y) && !onMouse(x, y));
	}
	
	public boolean onTopBorder(int i) {
		return Controller.getWorldCtrl().isNorthBorder(i);
	}
	
	public boolean onBottomBorder(int i) {
		return Controller.getWorldCtrl().isSouthBorder(i);
	}
	
	public boolean onLeftBorder(int i) {
		return Controller.getWorldCtrl().isWestBorder(i);
	}
	
	public boolean onRightBorder(int i) {
		return Controller.getWorldCtrl().isEastBorder(i);
	}
	
	protected Mouse getMouse() {
		return this;
	}
	
	/**
	 * makes one move in the top direction, i.e., 
	 * decrements positionX by 1
	 * @return true if the move was possible, false otherwise
	 * @throws EatableObjectOnPlaceException, if after the move mouse is positioned on a leaf
	 */
	public boolean moveUp() throws EatableObjectOnPlaceException {
		if (!treeAbove() && !mouseAbove()) {
			if (!onTopBorder(positionY)) {
				positionY = positionY - 1;
				eatPerformingStep();
				return true;
			}
			else {
				return moveDown();	
			}
		}
		return false;
	}

	/**
	 * eat an obstacle (leaf or mushroom) being placed on current position;
	 * nothing to do if greedy ist set to false (the user is repsonsible himself for eating actions)
	 * @throws EatableObjectOnPlaceException, if the obstacle has been eaten
	 */
	private void eatPerformingStep() throws EatableObjectOnPlaceException {
		if(greedy) {
			AbstractObject object = eat();
			if (object != null) {
				throw new EatableObjectOnPlaceException(object);
			}
		}
	}
	
	/**
	 * makes one move in the bottom direction, i.e., 
	 * decrements positionX by 1
	 * @return true if the move was possible, false otherwise
	 * @throws EatableObjectOnPlaceException, if after the move mouse is positioned on a leaf
	 */
	public boolean moveDown() throws EatableObjectOnPlaceException {
		if (!treeInBelow() && !mouseBelow()) {
			if (!onBottomBorder(positionY)) {
				positionY = positionY + 1;
				eatPerformingStep();
				return true;
			}
			else {
				return moveUp();
			}
		}
		return false;
	}
	
	/**
	 * makes one move in the left direction, i.e., 
	 * decrements positionX by 1
	 * @return true if the move was possible, false otherwise
	 * @throws EatableObjectOnPlaceException, if after the move mouse is positioned on a leaf
	 */
	public boolean moveLeft() throws EatableObjectOnPlaceException {
		if (!treeOnLeft() && !mouseOnLeft()) {
			if (!onLeftBorder(positionX)) {
				positionX = positionX - 1;
				eatPerformingStep();
				return true;
			}
			else {
				return moveRight();
			}
		}
		return false;
	}
	
	/**
	 * makes one move in the right direction, i.e., 
	 * decrements positionX by 1
	 * @return true if the move was possible, false otherwise
	 * @throws EatableObjectOnPlaceException, if after the move mouse is positioned on a leaf
	 */
	public boolean moveRight() throws EatableObjectOnPlaceException {
		if (!treeOnRight() && !mouseOnRight()) {
			if (!onRightBorder(positionX)) {
				positionX = positionX + 1;
				eatPerformingStep();
				return true;
			}
			else {
				return moveLeft();
			}
		}
		return false;
	}
	
	protected Leaf eatLeaf() {
		//TODO vereinfachen
		Leaf leaf = onLeaf(positionX, positionY);
		
		if (leaf != null) {
			return leaf;
		}
		else {
			return null;
		}
	}
	
	protected AbstractObject eatMushroom() {
		Mushroom m = onMushroom(positionX, positionY);
		if (m != null) {
			return m;
		}
		else {
			return null;
		}
	}
	
	protected AbstractObject eat() {
		AbstractObject leaf = eatLeaf();
		if(eatLeaf() == null)
			return eatMushroom();
		return leaf;
	}

	protected boolean treeAbove() {
		return (Controller.getWorldCtrl().treeOnPlace(positionX, positionY-1) != null);
	}
	
	protected boolean treeInBelow() {
		return (Controller.getWorldCtrl().treeOnPlace(positionX, positionY+1) != null);
	}
	
	protected boolean treeOnLeft() {
		return (Controller.getWorldCtrl().treeOnPlace(positionX-1, positionY)!= null);
	}
	
	protected boolean treeOnRight() {
		return (Controller.getWorldCtrl().treeOnPlace(positionX+1, positionY) != null);
	}
	
	public boolean goalAchieved() {
		return (Controller.getWorldCtrl().getGoal().equals(this.getPosition()));
	}
	
	protected boolean mouseAbove() {
		return (Controller.getWorldCtrl().mouseOnPlace(positionX, positionY-1) != null);
	}
	
	protected boolean mouseBelow() {
		return (Controller.getWorldCtrl().mouseOnPlace(positionX, positionY+1) != null);
	}
	
	protected boolean mouseOnLeft() {
		return (Controller.getWorldCtrl().mouseOnPlace(positionX-1, positionY) != null);
	}
	
	protected boolean mouseOnRight() {
		return (Controller.getWorldCtrl().mouseOnPlace(positionX+1, positionY) != null);
	}


	protected boolean leafAbove() {
		return (Controller.getWorldCtrl().leafOnPlace(positionX, positionY-1) != null);
	}
	
	protected boolean leafBelow() {
		return (Controller.getWorldCtrl().leafOnPlace(positionX, positionY+1) != null);
	}
	
	protected boolean leafOnLeft() {
		return (Controller.getWorldCtrl().leafOnPlace(positionX-1, positionY) != null);
	}
	
	protected boolean leafOnRight() {
		return (Controller.getWorldCtrl().leafOnPlace(positionX+1, positionY) != null);
	}
	
	protected boolean mushroomAbove() {
		return (Controller.getWorldCtrl().mushroomOnPlace(positionX, positionY-1) != null);
	}
	
	protected boolean mushroomBelow() {
		return (Controller.getWorldCtrl().mushroomOnPlace(positionX, positionY+1) != null);
	}
	
	protected boolean mushroomOnLeft() {
		return (Controller.getWorldCtrl().mushroomOnPlace(positionX-1, positionY) != null);
	}
	
	protected boolean mushroomOnRight() {
		return (Controller.getWorldCtrl().mushroomOnPlace(positionX+1, positionY) != null);
	}


	protected Mushroom onMushroom(int x, int y) {
		return Controller.getWorldCtrl().mushroomOnPlace(x, y);
	}

	protected Leaf onLeaf(int x, int y) {
		return Controller.getWorldCtrl().leafOnPlace(x, y);
	}
	
	protected boolean onTree(int x, int y) {
		return (Controller.getWorldCtrl().treeOnPlace(x, y) != null);
	}
	
	protected boolean onMouse(int x, int y) {
		return (Controller.getWorldCtrl().mouseOnPlace(x, y) != null);
	}
	
	protected boolean onBoard(int x, int y) {
		return Controller.getWorldCtrl().pointOnBoard(x, y);
	}
	
	public AbstractObject doNextStep()  {
		if(!setPosition(getPositionX() + 1*direction, getPositionY())) {
			if(!setPosition(getPositionX(), getPositionY()+1)) {
				//Spiel beenden
				active = false;
			}
			else {
				direction = -1*direction;
			}
		}
		AbstractObject obj = onLeaf(getPositionX(), getPositionY());
		if(greedy && obj != null ) {
			return obj;
		}
		obj = onMushroom(getPositionX(), getPositionY());
		if(greedy) {
			return obj;
		}
		else {
			return null;
		}
	}


	public void initMouse(MouseList ml) {

		if(greedy && eat() != null) {
			Controller.getWorldCtrl().updateObstacles(new Point(positionX, positionY));
		}
		ml.registerMouse(this);
	}
	
	public boolean defineStartPosition() {
		int i = 0;
		int j = 0;
		
		Point p = Controller.getMouseCtrl().getMouseStartPosition();
		if(p != null) {
			i = p.x;
			j = p.y;
		}
		
		//TODO: eine bessere Vorgehensweise finden, hier Gefahr einer Endlosschleife
		while(!setPosition(i, j)) {
			if((i%2) == 0) {
				i = i + 1;
			}
			else {
				j = j + 1;
			}
		}
		if(onLeaf(getPositionX(), getPositionY()) != null || onMushroom(getPositionX(), getPositionY()) != null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public AbstractObject defineStartPosition(int i, int j) {
		Point p = Controller.getMouseCtrl().getMouseStartPosition();
		if(p != null) {
			i = p.x;
			j = p.y;
		}
		if(setPosition(i, j)) {
			AbstractObject object = eat();
			if(object != null) {
				return object;
			}
			else {
				return this;
			}
		}
		else {
			return null;
		}
	}
	
	public int getScorePoints() {
		return score.getScore();
	}
	
	public void resetScorePoints(){
		this.score.resetScore();
	}

	public void addScorePoints(int score) {
		this.score.addScore(score);
	}
	
	public int getId() {
		return id;
	}
	
	public abstract void startGame(); 
	public abstract void doOneStep();
	
	public AbstractObject getObjectAbove() {
		if(onBoard(positionX, positionY-1)) {
			return Controller.getWorldCtrl().getObjectAt(positionX, positionY-1);
		}
		return null;
	}
	
	public AbstractObject getObjectBelow() {
		if(onBoard(positionX, positionY+1)) {
			return Controller.getWorldCtrl().getObjectAt(positionX, positionY+1);
		}
		return null;
	}
	
	public AbstractObject getObjectOnRight() {
		if(onBoard(positionX+1, positionY)) {
			return Controller.getWorldCtrl().getObjectAt(positionX+1, positionY);
		}
		return null;
	}
	
	public AbstractObject getObjectOnLeft() {
		if(onBoard(positionX-1, positionY)) {
			return Controller.getWorldCtrl().getObjectAt(positionX-1, positionY);
		}
		return null;
	}

	public Score getScore() {
		return score;
	}
	
}
