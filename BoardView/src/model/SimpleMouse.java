package model;


import java.util.Iterator;

import model.exceptions.EatableObjectOnPlaceException;
import model.exceptions.NoRuleException;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import record.Rule;
import util.Constants;
import ctrl.Controller;

public class SimpleMouse extends Mouse {
	
	public SimpleMouse(String name, String image) {
		super(name, image);
	}
	
	/**
	 * starts the game thread, used for updating the GUI
	 */
	public void startGame() {
		GameThread gt = new GameThread();
		gt.go();
	}
	
	public void doOneStep() {
		GameThread gt = new GameThread();
		gt.doSteps();
	}
	
	
	/**
	 * @author Dörsam
	 * Thread for updating the GUI
	 */
	protected class GameThread implements Runnable {
		// The timer interval in milliseconds
		private static final int TIMER_INTERVAL = 100;
			
		protected void go() {
			Thread t = new Thread(this);
			active = true;
			t.start();
		}
			
		public void run() {
			
				while (active) {
						try {
							doSteps();
							Thread.sleep(TIMER_INTERVAL);
						} catch (Exception e) {
							Display.getDefault().syncExec(new Runnable() {
								public void run() {
									doSteps();
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						}
				}
		}

		private void doSteps() {

						Point previous = new Point(positionX,positionY);
					
						try {
							AbstractObject result = doNextStep();	

							if (result == null) {
								Controller.getWorldCtrl().updateWorldAndMouse(previous, getMouse());	
							}
							else {
								Controller.getWorldCtrl().updateWorldAndMouseWithPoints(previous, getMouse(), result);
							}
							Controller.getWorldCtrl().updateCanvas();

	            	    Thread.sleep(TIMER_INTERVAL);
					} 
					catch (Exception e) {
			            Display.getDefault().syncExec(new Runnable() {
			               public void run() {
			            	   Controller.getWorldCtrl().updateCanvas();
			            	    try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			               }
			            });
					}
		}	
	}
	
	@Override
	public AbstractObject doNextStep()  {		
		try {
			return performRule();
		} catch (NoRuleException e) {
			String step = Controller.getMouseCtrl().getNextStep();
			if(step != null) {
				return performStep(step);
			}
			else {
				Controller.getMouseCtrl().initIterator();
				active = false;
				return null;
			}
		}
	}

	private AbstractObject performStep(String step) {
		switch(step) {
		case Constants.Arrows.moveRight:
			try {
				if (moveRight()) {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				return e.getObstacle();
			}
			break;
		case Constants.Arrows.moveDown:
			try {
				if (moveDown()) {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				return e.getObstacle();
			}
			break;
		case Constants.Arrows.moveLeft:
			try {
				if (moveLeft()) {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				return e.getObstacle();
			}
			break;
		case Constants.Arrows.moveUp:
			try {
				if (moveUp()) {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				return e.getObstacle();
			}
			break;
		case Constants.Thens.stop:
			active = false;
			return null;
		}
		return null;
	}
	
	//TODO Dörsam vereinfachen mit performSteps
	private AbstractObject performStepWithoutRules(String step) {
		AbstractObject result = this;
		switch(step) {
		case Constants.Arrows.moveRight:
			try {
				if (moveRight()) {
					return result;
				}
				else {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				result = e.getObstacle();
				return result;
			}
		case Constants.Arrows.moveDown:
			try {
				if (moveDown()) {
					return result;
				}
				else {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				result = e.getObstacle();
				return result;
			}
		case Constants.Arrows.moveLeft:
			try {
				if (moveLeft()) {
					return result;
				}
				else {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				result = e.getObstacle();
				return result;
			}
		case Constants.Arrows.moveUp:
			try {
				if (moveUp()) {
					return result;
				}
				else {
					return null;
				}
			} catch (EatableObjectOnPlaceException e) {
				return result;
			}
		case Constants.Thens.stop:
			active = false;
			return null;
		}
		return null;
	}
	
	private AbstractObject performRule() throws NoRuleException {
		AbstractObject eatenObject = null;
		if(goalAchieved()) {
			String goalAction = Controller.getMouseCtrl().getGoalAction();
			if(goalAction != null) {
				eatenObject = performAction(goalAction);
			}
		}
		else {
			//1. Regeliterator holen
			Iterator<Rule> ruleConditions = Controller.getMouseCtrl().getRuleIter();
			boolean found = false;		
			while(!found && ruleConditions.hasNext()) {
				//bis regel ausgeführt:
				// 1. hole nächste REgel
				// 2. überprüfe Bedingung
				// 3. Bedingung erfüllt: führe Aktion aus
				// 4. Bedingung nicht erfüllt: nächste REgel
				//TODO geht es nicht performanter?
				Rule rule = ruleConditions.next();
				if (checkCondition(rule.getIfCond())) {
					String action = Controller.getMouseCtrl().getActionForCondition(rule.getIfCond());
					eatenObject = performAction(action);
					if(eatenObject != null)
						found = true;
				}
			}
			if (!found) {
				String defaultAction = Controller.getMouseCtrl().getDefaultAction();
				if(defaultAction != null) {
					eatenObject = performAction(defaultAction);
				}
				else {
					throw new NoRuleException();
				}
			}
		}
		if(eatenObject != this)
			return eatenObject;
		else
			return null;
	}
	
	private boolean checkCondition(String ifCond) {
		switch(ifCond) {
			case Constants.Ifs.topBorder:
				if(onTopBorder(positionY)) 
					return true;
				break;
			case Constants.Ifs.rightBorder: 
				if(onRightBorder(positionX))
					return true;
				break;
			case Constants.Ifs.downBorder: 
				if(onBottomBorder(positionY)) 
					return true;
				break;
			case Constants.Ifs.leftBorder:
				if(onLeftBorder(positionX))
					return true;
				break;
			case Constants.Ifs.treeAbove: 
				return treeAbove();
			case Constants.Ifs.treeRight: 
				return treeOnRight();
			case Constants.Ifs.treeBelow: 
				return treeInBelow();
			case Constants.Ifs.treeLeft: 
				return treeOnLeft();
			case Constants.Ifs.mushroomAbove: 
				return mushroomAbove();
			case Constants.Ifs.mushroomRight:
				return mushroomOnRight();
			case Constants.Ifs.mushroomBelow: 
				return mushroomBelow();
			case Constants.Ifs.mushroomLeft: 
				return mushroomOnLeft();
			case Constants.Ifs.leafAbove: 
				return leafAbove();
			case Constants.Ifs.leafRight:
				return leafOnRight();
			case Constants.Ifs.leafBelow: 
				return leafBelow();
			case Constants.Ifs.leafLeft: 
				return leafOnLeft();
		}
		return false;
	}
	
	private AbstractObject performAction(String action) {
		switch(action) {
			case Constants.Thens.up: 				
				return performStepWithoutRules(Constants.Arrows.moveUp);
			case Constants.Thens.right:
				return performStepWithoutRules(Constants.Arrows.moveRight);
			case Constants.Thens.down:
				return performStepWithoutRules(Constants.Arrows.moveDown);
			case Constants.Thens.left: 
				return performStepWithoutRules(Constants.Arrows.moveLeft);
			case Constants.Thens.horizontal:
				double rnd = Math.random();
				if(rnd < 0.5)
					return performStepWithoutRules(Constants.Arrows.moveLeft);
				else
					return performStepWithoutRules(Constants.Arrows.moveRight);
			case Constants.Thens.vertical: 
				rnd = Math.random();
				if(rnd < 0.5)
					return performStepWithoutRules(Constants.Arrows.moveUp);
				else
					return performStepWithoutRules(Constants.Arrows.moveDown);
			case Constants.Thens.random:
				rnd = Math.random();
				if(rnd < 0.25)
					return performStepWithoutRules(Constants.Arrows.moveLeft);
				else
					if(rnd < 0.5)
						return performStepWithoutRules(Constants.Arrows.moveRight);
					else 
						if(rnd < 0.75)
							return performStepWithoutRules(Constants.Arrows.moveUp);
						else
							return performStepWithoutRules(Constants.Arrows.moveDown);
			case Constants.Thens.stop:
				return performStepWithoutRules(Constants.Thens.stop);
		}	
		return null;
	}
}
