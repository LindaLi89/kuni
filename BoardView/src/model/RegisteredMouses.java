package model;

import java.util.ArrayList;

public class RegisteredMouses {
	private static RegisteredMouses instance = null;
	
	public ArrayList<Mouse> mice = new ArrayList<Mouse>();
	
	public void replaceMouse(Mouse m) {
		mice = new ArrayList<Mouse>();
		mice.add(m);
	}
	
	public void addMouse(Mouse m){
		if(mice.size() == 0){
			mice = new ArrayList<Mouse>();
			mice.add(m);
		}
		else{
			mice.add(m);
		}
	}
	
	public Mouse getFirstMouse() {
		if(mice.size() == 0) {
			return null;
		}
		return mice.iterator().next();
	}
	
	public Mouse getMouse(int number){
		return mice.get(number);
	}
	
	public static RegisteredMouses getInstance() {
        if (instance == null) {
            instance = new RegisteredMouses();
        }
        return instance;
    }
}
