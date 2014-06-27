package figur;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import server.Point;

public class MyMouse_01 extends AbstractMouse{
	
	private final static Logger LOGGER = Logger.getLogger(MyMouse_01.class.getName()); 
	
	public MyMouse_01() {
		LOGGER.setLevel(Level.INFO); 
	}
	
	public Point defineStartPosition(int width, int height) {
		super.defineStartPosition(width, height);
		//TODO das Setzen der Startposition müßte vereinfacht werden
		point.setPositionX(0);
		point.setPositionY(0);
		
		LOGGER.log(new LogRecord(Level.INFO, "MyMouse_01: " + point));
		
		return point;
	}

	public String naechsterSchritt() {
//		if(grenze_rechts) {
//			return(stop);
//		}
//		else {
			return gehNachRechts;
//		}
	}
}
