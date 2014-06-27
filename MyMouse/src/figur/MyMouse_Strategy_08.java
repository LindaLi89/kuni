package figur;

public class MyMouse_Strategy_08 extends AbstractMouse {

	public String naechsterSchritt() {
		if(ziel_erreicht) return stop;
		if(baum_rechts) return gehSenkrecht;
		return gehNachRechts;
	}	
}
