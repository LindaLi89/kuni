package figur;

public class MyMouse_Strategy_03 extends AbstractMouse {

	public String naechsterSchritt() {
		if(blatt_rechts) 
			return gehNachRechts;
		if(blatt_unten)
			return gehNachUnten;
		if(blatt_links)
			return gehNachLinks;
		if(blatt_oben)
			return gehNachOben;
		if(blatt_rechts)
			return gehNachRechts;
	
		return stop;
	}	
}
