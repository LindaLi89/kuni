package figur;

public class MyMouse_Strategy_05 extends AbstractMouse {

	public String naechsterSchritt() {
		if(ziel_erreicht) return stop;
		
		if(pilz_links)	return gehNachRechts;
		if(baum_oben && baum_rechts) return gehNachUnten;
		if(baum_oben && baum_links) return gehNachUnten;
		if(baum_unten && baum_rechts) return gehNachLinks;
		if(baum_unten && baum_links) return gehNachRechts;
		return wiederhole;
	}	
}
