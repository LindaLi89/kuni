package figur;

public class MyMouse_Strategy_06 extends AbstractMouse {

	public String naechsterSchritt() {
		if(ziel_erreicht) return stop;
		if(baum_oben && baum_unten && baum_rechts) return gehZurueck;
		if(baum_oben && baum_unten && baum_links) return gehZurueck;
		if(baum_oben && baum_unten) 
				return wiederhole;
		if(baum_oben) return gehWaagerecht;
		if(!baum_oben)
			return gehNachOben;
		return gehNachRechts;
	}	
}
