package figur;

public class MyMouse_Strategy_16 extends AbstractMouse {

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
		
		if(grenze_links && grenze_oben) {
			return gehNachRechts;
		}
		
		if(grenze_links && grenze_unten) {
			return gehNachRechts;
		}
		
		if(baum_rechts || baum_links || pilz_links || pilz_rechts || grenze_links || grenze_rechts) {
			return gehUntenZurueck;
		}
		

		return wiederhole;
	}	
}
