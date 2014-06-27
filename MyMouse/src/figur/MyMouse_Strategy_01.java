package figur;

public class MyMouse_Strategy_01 extends AbstractMouse {

	public String naechsterSchritt() {
		if(grenze_rechts) return stop;
		return gehNachRechts;
	}	
}
