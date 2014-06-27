package figur;

public class MyMouse_Strategy_14_alt extends AbstractMouse {

	public String naechsterSchritt() {
		if(grenze_unten && grenze_rechts) return stop;
		if(blatt_rechts)
			return gehNachRechts;
		if(blatt_unten)
			return gehNachUnten;
		if(blatt_links)
			return gehNachLinks;
		if(blatt_oben)
			return gehNachOben;
		
		if(baum_rechts) return gehSenkrecht;
		if(baum_unten) return gehWaagerecht;
		if(grenze_links) return gehNachRechts;
		if(grenze_oben || grenze_unten) return gehWaagerecht;
		if(pilz_rechts || pilz_links) return gehSenkrecht;
	
		
		return gehZufaellig;
	}	
}
