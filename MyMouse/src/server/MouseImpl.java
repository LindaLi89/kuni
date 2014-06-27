package server;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import figur.*;

/**
 * @author Dörsam
 *
 */

public class MouseImpl {
	
	static AbstractMouse mouse;
	private final static Logger LOGGER = Logger.getLogger(AbstractMouse.class.getName()); 

	
	/**
	 * registers the class of the mouse to be used
	 * must be called from the client before all other methds of this class
	 * @param className: Name of the class which implements the mouse's strategy, e.g., MyMouse_Strategy_01
	 * @return true
	 */
	public boolean setMouse(String className) {
		try {
			MouseImpl.mouse = generateObject(className);
			return true;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * registers the mouse calling the method setMouse(String)
	 * sets the mouse at a specific position, usually used to define the start position of the mouse
	 * @param className
	 * @param x
	 * @param y
	 * @return false
	 */
	public boolean setMouse(String className, int x, int y) {
		setMouse(className);
		Point p = new Point();
		p.setPositionX(x);
		p.setPositionY(y);
		MouseImpl.mouse.setPoint(p);
		return false;
	}
	
	public String doNextStep(int x, int y, boolean north, boolean east, boolean south, boolean west, 
			String top, String right, String bottom, String left, String objectName, boolean goalAchieved) {		
		if(MouseImpl.mouse != null) {
			
			MouseImpl.mouse.getPoint().setPositionX(x);
			MouseImpl.mouse.getPoint().setPositionY(y);
			
			MouseImpl.mouse.setOnNorthBorder(north);
			MouseImpl.mouse.setOnEastBorder(east);
			MouseImpl.mouse.setOnSouthBorder(south);
			MouseImpl.mouse.setOnWestBorder(west);
			
			
			MouseImpl.mouse.setLeafAbove(top.contains("Leaf"));
			MouseImpl.mouse.setLeafOnRight(right.contains("Leaf"));
			MouseImpl.mouse.setLeafBelow(bottom.contains("Leaf"));
			MouseImpl.mouse.setLeafOnLeft(left.contains("Leaf"));
			
			MouseImpl.mouse.setMushroomAbove(top.contains("Mushroom"));
			MouseImpl.mouse.setMushroomOnRight(right.contains("Mushroom"));
			MouseImpl.mouse.setMushroomBelow(bottom.contains("Mushroom"));
			MouseImpl.mouse.setMushroomOnLeft(left.contains("Mushroom"));
			
			MouseImpl.mouse.setTreeAbove(top.contains("Tree"));
			MouseImpl.mouse.setTreeOnRight(right.contains("Tree"));
			MouseImpl.mouse.setTreeBelow(bottom.contains("Tree"));
			MouseImpl.mouse.setTreeOnLeft(left.contains("Tree"));
			
			MouseImpl.mouse.setLeafEaten(objectName.contains("Leaf"));
			MouseImpl.mouse.setMushroomEaten(objectName.contains("Mushroom"));
			
			MouseImpl.mouse.setGoalAchieved(goalAchieved);
			
			String step = "";
			if(!MouseImpl.mouse.dontAsk) {
				step =  MouseImpl.mouse.naechsterSchritt();
				if(step.equalsIgnoreCase(MouseImpl.mouse.getWiederhole())) {
					step = repeatStep();
				}
				else {
					if(step.equalsIgnoreCase(MouseImpl.mouse.getGehUntenZurueck())) {
						step = downAndBackStep();			
					}
					else {
						if(step.equalsIgnoreCase(MouseImpl.mouse.getBiegeImUhrzeigerSinnAb())) {
							step = clockwiseStep();
						}
						else {
							if(step.equalsIgnoreCase(MouseImpl.mouse.getGehZurueck())) {
								step = backStep();
							}
							else {
								if(step.equalsIgnoreCase(MouseImpl.mouse.getGehWaagerecht())) {
									step = horizontalStep();
								}
								else {
									if(step.equalsIgnoreCase(MouseImpl.mouse.getGehSenkrecht())) {
										step = verticalStep();
									}
									else {
										if(step.equalsIgnoreCase(MouseImpl.mouse.getGehZufaellig())) {
											step = randomStep();
										}
									}
								}
							}
						}
					}

					
				}
			}
			else {
				MouseImpl.mouse.dontAsk = false;
				step = MouseImpl.mouse.nextStep;
			}
				
			LOGGER.log(new LogRecord(Level.INFO, mouse.getClass().getName() + ": " + step));
			MouseImpl.mouse.setLastStep(step);
			return step;
		} 
		return "";		
	}

	protected String backStep() {
		String step;
		String lastStep = MouseImpl.mouse.getLastStep();
		if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachRechts())) {			
			step = MouseImpl.mouse.getGehNachLinks();
		}
		else {
			if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachUnten())) {			
				step = MouseImpl.mouse.getGehNachOben();
			}
			else {
				if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachLinks())) {			
					step = MouseImpl.mouse.getGehNachRechts();
				}
				else {
					step = MouseImpl.mouse.getGehNachUnten();
				}
			}
		}
		return step;
	}
	
	protected String horizontalStep() {
		String step;
		double rnd = Math.random();
		if(rnd < 0.5)	
			step = MouseImpl.mouse.getGehNachLinks();
		else		
			step = MouseImpl.mouse.getGehNachRechts();
		return step;
	}
	
	protected String verticalStep() {
		String step;
		double rnd = Math.random();
		if(rnd < 0.5)	
			step = MouseImpl.mouse.getGehNachOben();
		else		
			step = MouseImpl.mouse.getGehNachUnten();
		return step;
	}
	
	protected String randomStep() {
		String step;
		double rnd = Math.random();
		if(rnd < 0.25)	
			step = MouseImpl.mouse.getGehNachOben();
		else	
			if(rnd < 0.5) {
				step = MouseImpl.mouse.getGehNachUnten();
			}
			else {
				if(rnd < 0.55) {
					step = MouseImpl.mouse.getGehNachLinks();
				}
				else {
					step = MouseImpl.mouse.getGehNachRechts();
				}
			}
		return step;
	}

	protected String clockwiseStep() {
		String step;
		if(!MouseImpl.mouse.getLastStep().equalsIgnoreCase("")) {
			String lastStep = MouseImpl.mouse.getLastStep();
			if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachRechts())) {			
				step = MouseImpl.mouse.getGehNachUnten();
			}
			else {
				if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachUnten())) {			
					step = MouseImpl.mouse.getGehNachLinks();
				}
				else {
					if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachLinks())) {			
						step = MouseImpl.mouse.getGehNachOben();
					}
					else {
						step = MouseImpl.mouse.getGehNachRechts();
					}
				}
			}
		}
		else {
			step = MouseImpl.mouse.getGehNachRechts();
		}
		return step;
	}

	protected String downAndBackStep() {
		String step;
		if(!MouseImpl.mouse.getLastStep().equalsIgnoreCase("")) {
			step = MouseImpl.mouse.getGehNachUnten();
			String lastStep = MouseImpl.mouse.getLastStep();
			if(lastStep.equalsIgnoreCase(MouseImpl.mouse.getGehNachRechts())) {			
				MouseImpl.mouse.dontAsk = true;
				MouseImpl.mouse.nextStep = MouseImpl.mouse.getGehNachLinks();
			}
			else {
				MouseImpl.mouse.dontAsk = true;
				MouseImpl.mouse.nextStep = MouseImpl.mouse.getGehNachRechts();
			}
		}
		else {
			step = MouseImpl.mouse.getGehNachRechts();
		}
		return step;
	}

	protected String repeatStep() {
		String step;
		if(!MouseImpl.mouse.getLastStep().equalsIgnoreCase(""))
			step = MouseImpl.mouse.getLastStep();
		else
			step = MouseImpl.mouse.getGehNachRechts();
		return step;
	}

	/**
	 * sets the x coordinate of the start position of the mouse 
	 * @param width: width of the current world
	 * @param height: height of the current world
	 * @return 0
	 */
	public Integer setStartPositionX(int width, int height) {
		if(MouseImpl.mouse != null) {
			if(!MouseImpl.mouse.isStarted()) {
				LOGGER.log(new LogRecord(Level.INFO, "MouseImpl 01: " + MouseImpl.mouse.getPoint()));
				return mouse.defineStartPosition(width, height).getPositionX();
			} 
			else {
				LOGGER.log(new LogRecord(Level.INFO, "MouseImpl 02: " + MouseImpl.mouse.getPoint()));
				return mouse.getPoint().getPositionX();
			}
		}
		LOGGER.log(new LogRecord(Level.INFO, "MouseImpl 03: " +MouseImpl. mouse.getPoint()));
		return 0;	
	}
	
	/**
	 * sets the y coordinate of the start position of the mouse 
	 * @param width: width of the current world
	 * @param height: height of the current world
	 * @return 0
	 */
	public Integer setStartPositionY(int width, int height) {
		if(MouseImpl.mouse != null) {
			if(!MouseImpl.mouse.isStarted()) {
				LOGGER.log(new LogRecord(Level.INFO, "MouseImpl 04: " + MouseImpl.mouse.getPoint()));
				return MouseImpl.mouse.defineStartPosition(width, height).getPositionY();
			} 
			else {
				LOGGER.log(new LogRecord(Level.INFO, "MouseImpl 05: " + MouseImpl.mouse.getPoint()));
				return MouseImpl.mouse.getPoint().getPositionY();
			}
		}
		LOGGER.log(new LogRecord(Level.INFO, "MouseImpl 0: " + MouseImpl.mouse.getPoint()));
		return 0;	
	}
	
	/**
	 * 
	 * @return the names of th eclasses implemented in package "figur" except the class "AbstractMouse"
	 */
	public String[] getAllStrategies(){
		Klassenname kn = new Klassenname();
		String[] klassen = kn.getKlassen();
		return klassen;
	
	}
	
	private AbstractMouse generateObject(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class cls = Class.forName( className);
		AbstractMouse obj = (AbstractMouse) cls.newInstance();
		return obj;
	}
}

