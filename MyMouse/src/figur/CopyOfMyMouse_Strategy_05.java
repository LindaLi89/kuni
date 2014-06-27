package figur;

public class CopyOfMyMouse_Strategy_05 extends AbstractMouse {

	public String naechsterSchritt() {
		if(grenze_unten && grenze_rechts) return stop;
		if(grenze_unten && grenze_links) return stop;
		if(grenze_links && grenze_oben) {
			return gehNachRechts;
		}
		
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
		

		
		if(baum_rechts || baum_links || pilz_links || pilz_rechts || grenze_links || grenze_rechts) {
			return gehUntenZurueck;
		}
		
		return wiederhole;
	}	
}
