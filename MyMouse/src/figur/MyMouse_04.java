package figur;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import server.Point;

public class MyMouse_04 extends AbstractMouse{
	
	private final static Logger LOGGER = Logger.getLogger(MyMouse_04.class.getName()); 
	
	public MyMouse_04() {
		LOGGER.setLevel(Level.INFO); 
	}
	
	public Point defineStartPosition(int width, int height) {
		super.defineStartPosition(width, height);
		//TODO das Setzen der Startposition müßte vereinfacht werden
		point.setPositionX(0);
		point.setPositionY(0);
		
		LOGGER.log(new LogRecord(Level.INFO, "MyMouse_02: " + point));
		
		return point;
	}

	public String naechsterSchritt() {
		if(grenze_unten && grenze_rechts) {
			return stop;
		}
		if(baum_rechts) {
			return gehSenkrecht;
		}
		if(baum_links) {
			return gehSenkrecht;
		}
		if(pilz_rechts) {
			return gehSenkrecht;
		}
		if(pilz_links) {
			return gehSenkrecht;
		}
		if(grenze_rechts) {
			return gehZufaellig;
		}

		return gehWaagerecht;
	}
	
}
