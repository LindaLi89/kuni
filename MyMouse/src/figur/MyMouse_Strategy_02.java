package figur;

public class MyMouse_Strategy_02 extends AbstractMouse {

	public String naechsterSchritt() {
		if(grenze_unten) return stop;
		if(blatt_unten) return gehNachUnten;
		return gehNachRechts;
	}	
}
