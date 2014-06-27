package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Point;

/**
 * List of all Mices currently being positioned in the world
 */
public class MouseList {
	
	private static MouseList instance = null;
	
	protected HashMap<Point, Mouse> mice = new HashMap<Point, Mouse>();
	

		public HashMap<Point, Mouse> getMice() {
			return mice;
		}
		
		public Mouse getMouseAtPoint(int x, int y) {
			Mouse result = null;
			boolean found = false;
			Iterator<Mouse> iter = mice.values().iterator();
			while(!found && iter.hasNext()) {
				Mouse c = iter.next();
				if (c.getPositionX() == x && c.getPositionY() == y) {
					result = c;
					found = true;
				}
			}
			return result;
		
		}
		

		public void addMouse(Mouse mouse) {
			mouse.initMouse(this);
		}	
		
		public List<Point> getMousePoints() {
			List<Point> result = new ArrayList<Point>();
			Iterator<Point> iter = mice.keySet().iterator();
			while(iter.hasNext()) {
				result.add(iter.next());
			}
			return result;
		}
		
		public Mouse getFirstMouse() {
			return mice.values().iterator().next();
		}
		
		public Mouse getSecondMouse(){
			Iterator<Point> iter = mice.keySet().iterator();
			iter.next();
			return mice.get(iter.next());
		}
		
		public Point getFirstKey() {
			return mice.keySet().iterator().next();
		}
		
		//Registrierungsmethode f�r die Benutzung eigener Mausimplementierungen in der aktuellen Welt
		public void registerMouse(Mouse m) {
			removeMouse(m);
			mice.put(new Point(m.getPositionX(), m.getPositionY()), m);
		}
		
		private void removeMouse(Mouse m) {
			Iterator<Point> iter = mice.keySet().iterator();
			while(iter.hasNext()) {
				Point p = iter.next();
				if(mice.get(p) == m) {
					mice.remove(p);
//					m.setDefaultPosition();
				}
			}
		}
		
		public void removeAll() {
			Iterator<Mouse> iter = mice.values().iterator();
			while (iter.hasNext()) {
				iter.next().setDefaultPosition();
			}
			mice = new HashMap<Point, Mouse>();
		}
		
		
		public static MouseList getInstance() {
	        if (instance == null) {
	            instance = new MouseList();
	        }
	        return instance;
	    }
}
