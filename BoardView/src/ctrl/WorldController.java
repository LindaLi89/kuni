package ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.AbstractObject;
import model.Leaf;
import model.Mouse;
import model.Mushroom;
import model.RegisteredMouses;
import model.Tree;
import model.World;
import model.exceptions.EatableObjectOnPlaceException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import spielbrettview.customviews.WorldView;

import util.Constants;


public class WorldController {

	private World world;

	public World getWorld() {
		return world;
	}
	
	public Point getGoal() {
		return world.getGoal();
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getWorldWidth() {
		return world.getWidth();
	}
	
	public int getWorldHeight() {
		return world.getHeight();
	}

	private WorldView canvas = null;

	public WorldView getCanvas() {
		return canvas;
	}

	private boolean recButton = false;
	
	public WorldController(World world, WorldView canvas) {
		this.world = world;
		if (canvas != null) {
			this.canvas = canvas;
			int[] spaces = world.calculateGrid();
			canvas.setSpaces(spaces[0], spaces[1]);
		}

		updateObstacles();

	}

	public void performActionOnKeyPress(KeyEvent e) {
		if (canvas != null) {
			// bewegen

			if (e.keyCode == SWT.ARROW_UP)
				moveUp();
			else if (e.keyCode == SWT.ARROW_DOWN)
				moveDown();
			else if (e.keyCode == SWT.ARROW_LEFT)
				moveLeft();
			else if (e.keyCode == SWT.ARROW_RIGHT)
				moveRight();

			canvas.redraw();

		}
	}

	public void updateObstacles() {
		List<List<Point>> obstacleList = new ArrayList<List<Point>>();
		
		if (world.isRandomMushrooms() && Math.random() < Constants.Random.limit) {
			randomItem();
		}
		obstacleList.add(world.getMushroomPoints());
		obstacleList.add(world.getLeafPoints());
		obstacleList.add(world.getTreePoints());
		if (canvas != null) {
			updateObstaclesInternal(obstacleList);
		}
	}

	private void updateObstaclesInternal(List<List<Point>> obstacleList) {
		canvas.setObjects(obstacleList);
	}

	
	public void updateMovableObjects() {
		if (canvas != null) {
			updateMovableObjectsInternal();
		}
	}

	private void updateMovableObjectsInternal() {
		if (world.isRandomMushrooms() && Math.random() < Constants.Random.limit) {
			randomItem();
			updateObstacles();
		}
		canvas.setMoveableObjects(mouseListWithDirectionAsNumber());
	}
	
	public void addObstacle(Point p, AbstractObject obstacle) {
		world.getObstacles().put(p, obstacle);
	}

	private Map<Point, Image> mouseListWithDirectionAsNumber() {
		Map<Point, Image> miceList = new HashMap<Point, Image>();
		Iterator<Point> iter = Controller.getMouseList().getMousePoints()
				.iterator();
		while (iter.hasNext()) {
			Point p = iter.next();
			
			Mouse m = Controller.getMouseList().getMouseAtPoint(p.x, p.y);
			Image img = getCanvas().generateImageForWorld(m.getImg());
			m.setImage(img);
			miceList.put(p,img);
		}
		return miceList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#removeLeaf(int, int)
	 */
	
	public void removeLeaf(int x, int y) {
		world.removeObstacle(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#obstacleOnPlace(int, int)
	 */
	
	public AbstractObject obstacleOnPlace(int x, int y) {
		return (world.getObjectAtPoint(x, y));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#treeOnPlace(int, int)
	 */
	
	public Tree treeOnPlace(int x, int y) {
		AbstractObject object = world.getObjectAtPoint(x, y);
		if (object != null && (object instanceof Tree)) {
			return (Tree) object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#leafOnPlace(int, int)
	 */
	
	public Leaf leafOnPlace(int x, int y) {
		AbstractObject object = world.getObjectAtPoint(x, y);
		if ((object != null) && (object instanceof Leaf)) {
			return (Leaf) object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#mushroomOnPlace(int, int)
	 */
	
	public Mushroom mushroomOnPlace(int x, int y) {
		AbstractObject object = world.getObjectAtPoint(x, y);
		if ((object != null) && (object instanceof Mushroom)) {
			return (Mushroom) object;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#mouseOnPlace(int, int)
	 */
	
	public Mouse mouseOnPlace(int x, int y) {
		return Controller.getMouseList().getMouseAtPoint(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#pointOnBoard(int, int)
	 */
	
	public boolean pointOnBoard(int x, int y) {
		return (world.getNorthBorder() <= y && world.getSouthBorder() >= y
				&& world.getWestBorder() <= x && world.getEastBorder() >= x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#isNorthBorder(int)
	 */
	
	public boolean isNorthBorder(int i) {
		return i == world.getNorthBorder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#isSouthBorder(int)
	 */
	
	public boolean isSouthBorder(int i) {
		return i == world.getSouthBorder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#isWestBorder(int)
	 */
	
	public boolean isWestBorder(int i) {
		return i == world.getWestBorder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#isEastBorder(int)
	 */
	
	public boolean isEastBorder(int i) {
		return i == world.getEastBorder();
	}

	public boolean isBorder(int i, int j) {
		return (isNorthBorder(j) || isSouthBorder(j) || isEastBorder(i) || isWestBorder(i));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#goForward()
	 */
	
	public void moveUp() {
		Point p = Controller.getMouseList().getFirstKey();
		Mouse mouse = Controller.getMouseList().getMice().get(p);
		// TODO bessere Lösung als Exception? Evtl. Exception bei "move" und
		// true bei essen?
		try {
			if (mouse.moveUp()) {
				updateWorldAndMouse(p, mouse);
			}
		} catch (EatableObjectOnPlaceException e) {
			updateWorldAndMouseWithPoints(p, mouse, e.getObstacle());
		}
		if (recButton){
			Controller.getMouseCtrl().getRecord().recordAction(Constants.Arrows.moveUp);
		}		
	}

	public void updateWorldAndMouse(Point previous, Mouse mouse) {
		if(previous != null)
			Controller.getMouseList().getMice().remove(previous);
		Controller
				.getMouseList()
				.getMice()
				.put(new Point(mouse.getPositionX(), mouse.getPositionY()),
						mouse);
		
		//TODO ist dieser Aufruf notwendig? Die HIndernisse werden hier nicht neu gezeichnet ...
		world.removeObstacle(mouse.getPositionX(), mouse.getPositionY());
		updateMovableObjects();
	}

	public void updateWorldAndMouseWithPoints(Point previousp, Mouse mouse,
			AbstractObject object) {
		if(previousp != null)
			Controller.getMouseList().getMice().remove(previousp);
		Controller
				.getMouseList()
				.getMice()
				.put(new Point(mouse.getPositionX(), mouse.getPositionY()),
						mouse);
		world.removeObstacle(mouse.getPositionX(), mouse.getPositionY());
		updateMovableObjects();
		updateObstacles();
		if (object instanceof Leaf) {
			if (Controller.isTournamentModus()) {
				if (mouse.getId() == 1) {
					Controller.getScoreCtrl().setScore(Leaf.getPoints());
				} else {
					Controller.getScoreCtrl().setScoreMouse2(Leaf.getPoints());
				}
			} else {
				Controller.getScoreCtrl().setScore(Leaf.getPoints());
			}
		} else {
			if (object instanceof Mushroom) {
				if (Controller.isTournamentModus()) {
					if (mouse.getId() == 1) {
						Controller.getScoreCtrl().setScore(Mushroom.getPoints());
					} else {
						Controller.getScoreCtrl().setScoreMouse2(Mushroom.getPoints());
					}
				} else {
					Controller.getScoreCtrl().setScore(Mushroom.getPoints());
				}
			}
		}
	}
	
	
	public void okButtonPressed(String cond, String then) {
		Controller.getMouseCtrl().getRecord().recordRule(cond, then);
	}

	/*
	 * (non-Javadoc)
	 * Rectangle
	 * @see ctrl.IController#goBackward()
	 */
	
	public void moveDown() {
		Point p = Controller.getMouseList().getFirstKey();
		Mouse mouse = Controller.getMouseList().getMice().get(p);
		// TODO bessere Lösung als Exception? Evtl. Exception bei "move" und
		// true bei essen?
		try {
			if (mouse.moveDown()) {
				updateWorldAndMouse(p, mouse);
			}
		} catch (EatableObjectOnPlaceException e) {
			updateWorldAndMouseWithPoints(p, mouse, e.getObstacle());
		}
		if (recButton){
			Controller.getMouseCtrl().getRecord().recordAction(Constants.Arrows.moveDown);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#moveLeft()
	 */
	
	public void moveLeft() {
		Point p = Controller.getMouseList().getFirstKey();
		Mouse mouse = Controller.getMouseList().getMice().get(p);
		// TODO bessere Lösung als Exception? Evtl. Exception bei "move" und
		// true bei essen?
		try {
			if (mouse.moveLeft()) {
				updateWorldAndMouse(p, mouse);
			}
		} catch (EatableObjectOnPlaceException e) {
			updateWorldAndMouseWithPoints(p, mouse, e.getObstacle());
		}
		if (recButton){
			Controller.getMouseCtrl().getRecord().recordAction(Constants.Arrows.moveLeft);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#moveRight()
	 */
	
	public void moveRight() {
		Point p = Controller.getMouseList().getFirstKey();
		Mouse mouse = Controller.getMouseList().getMice().get(p);
		// TODO bessere Lösung als Exception? Evtl. Exception bei "move" und
		// true bei essen?
		try {
			if (mouse.moveRight()) {
				updateWorldAndMouse(p, mouse);
			}
		} catch (EatableObjectOnPlaceException e) {
			updateWorldAndMouseWithPoints(p, mouse, e.getObstacle());
		}
		if (recButton){
			Controller.getMouseCtrl().getRecord().recordAction(Constants.Arrows.moveRight);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#updateView(boolean, org.eclipse.swt.graphics.Point,
	 * org.eclipse.swt.graphics.Point)
	 */
	
	public void updateView(AbstractObject changedObstacles, Point previous,
			Point current) {
		if (changedObstacles != null) {
			updateObstacles(current);
		}
		updateMovableObjects();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#updateObstacles(org.eclipse.swt.graphics.Point)
	 */
	
	public void updateObstacles(Point current) {
		world.removeObstacle(current.x, current.y);
		updateObstacles();
	}

	public AbstractObject getObjectAt(int x, int y) {
		return world.getObjectAtPoint(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#updateCanvas()
	 */
	
	public void updateCanvas() {
		canvas.redraw();
		Controller.getScoreCtrl().updateScoreView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ctrl.IController#startAutomaticGame()
	 */
	
	public void startAutomaticGame() {
		if (Controller.isTournamentModus()) {
			Mouse mouse1 = Controller.getMouseList().getFirstMouse();
			Mouse mouse2 = Controller.getMouseList().getSecondMouse();
			mouse1.startGame();
			mouse2.startGame();
		} else {
			Point p = Controller.getMouseList().getFirstKey();
			Mouse mouse = Controller.getMouseList().getMice().get(p);
			mouse.startGame();
		}
	}
	
	public void startOneStep(){
		if (Controller.isTournamentModus()) {
			Mouse mouse1 = Controller.getMouseList().getFirstMouse();
			Mouse mouse2 = Controller.getMouseList().getSecondMouse();
			mouse1.doOneStep();
			mouse2.doOneStep();
		} else {
			Point p = Controller.getMouseList().getFirstKey();
			Mouse mouse = Controller.getMouseList().getMice().get(p);
			mouse.doOneStep();
		}
	}

	public void stopAutomaticGame(){
		if(Controller.isTournamentModus()){
			Mouse mouse1 = Controller.getMouseList().getFirstMouse();
			Mouse mouse2 = Controller.getMouseList().getSecondMouse();
			mouse1.setActive(false);
			mouse2.setActive(false);
		}else{
			Mouse mouse1 = Controller.getMouseList().getFirstMouse();
			mouse1.setActive(false);
		}
	}
	
	public void setMouseAtStartPosition() {
		Mouse mouse = RegisteredMouses.getInstance().getFirstMouse();
		mouse.defineStartPosition();
	}

	public boolean isRecButton() {
		return recButton;
	}

	public void setRecButton(boolean recButton) {
		this.recButton = recButton;
	}
	
	
	public void randomItem(){
		int height = getWorldHeight();		
		int width = getWorldWidth();		
		int randomX = (int)(Math.random() * width);
		int randomY = (int) (Math.random()* height);
		
		Point x = new Point(randomX, randomY);
		addObstacle(x, new Mushroom());
	}
	
	public void resetWorld() {
		canvas.reset();
	}
	
	public void setCurrentWorldName(String name) {
		Controller.getCurrentView().setCurrentWorldName(name);
	}
	
	public Point getMouseStartPosition() {
		return world.getMouseStartPoint();
	}
	
}
