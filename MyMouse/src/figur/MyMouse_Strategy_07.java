package figur;

public class MyMouse_Strategy_07 extends AbstractMouse {

	public String naechsterSchritt() {
		if(ziel_erreicht) return stop;
		
		if(baum_links) return gehNachUnten;
		if(baum_oben && !baum_links)
				return gehNachLinks;
		if(baum_oben && baum_links)
				return gehNachUnten;
		if(baum_rechts) return gehNachOben;
		if(baum_unten && !baum_rechts)
				return gehNachRechts;
		if(baum_unten && baum_rechts)
				return gehNachOben;
		
		return biegeImUhrzeigerSinnAb;
	}	
}
