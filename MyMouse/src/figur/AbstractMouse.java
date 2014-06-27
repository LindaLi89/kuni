package figur;

import server.Point;

public abstract class AbstractMouse {
	
	protected Point point;
	protected boolean started = false;
	
	protected boolean grenze_oben = false;
	protected boolean grenze_links = false;
	protected boolean grenze_unten = false;
	protected boolean grenze_rechts = false;
	
	protected boolean baum_oben = false;
	protected boolean baum_rechts = false;
	protected boolean baum_unten = false;
	protected boolean baum_links = false;
	
	protected boolean blatt_oben = false;
	protected boolean blatt_rechts = false;
	protected boolean blatt_unten = false;
	protected boolean blatt_links = false;
	
	protected boolean pilz_oben = false;
	protected boolean pilz_rechts = false;
	protected boolean pilz_unten = false;
	protected boolean pilz_links = false;
	
	private boolean mushroomEaten = false;
	private boolean leafEaten = false;
	
	protected boolean ziel_erreicht = false;
	
	
	
	protected final String gehNachRechts = "rechts"; 
	protected final String gehNachOben = "oben"; 
	protected final String gehNachLinks = "links"; 
	protected final String gehNachUnten = "unten"; 
	protected final String stop = "stop"; 
	protected final String gehWaagerecht = "waagerecht"; 
	protected final String gehSenkrecht = "senkrecht"; 
	protected final String gehZufaellig = "zufall"; 
	protected final String machNichts = "nichts"; 
	protected final String wiederhole = "wiederhole"; 
	protected final String gehUntenZurueck = "gehUntenZurueck"; 
	protected final String biegeImUhrzeigerSinnAb = "biegeImUhrzeigerSinnAb"; 
	protected final String gehZurueck = "gehZurueck"; 
	
	public boolean dontAsk = false;
	public String nextStep = "";


	public String getGehNachUnten() {
		return gehNachUnten;
	}

	public String getGehNachLinks() {
		return gehNachLinks;
	}

	public String getGehNachOben() {
		return gehNachOben;
	}

	public String getGehUntenZurueck() {
		return gehUntenZurueck;
	}

	public String getGehSenkrecht() {
		return gehSenkrecht;
	}

	public String getGehZufaellig() {
		return gehZufaellig;
	}

	public String getGehZurueck() {
		return gehZurueck;
	}

	protected String lastStep = "";
	
	
	public String getLastStep() {
		return lastStep;
	}

	public void setLastStep(String lastStep) {
		this.lastStep = lastStep;
	}

	public Point defineStartPosition(int width, int height){
		if(point == null) {
			point = new Point();
		}
		point.setPositionX(0);
		point.setPositionY(0);
		started = true;
		return point;
	}

	public String naechsterSchritt() {
		started = false;
		return "";
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isOnNorthBorder() {
		return grenze_oben;
	}

	public void setOnNorthBorder(boolean onNorthBorder) {
		this.grenze_oben = onNorthBorder;
	}

	public boolean isOnEastBorder() {
		return grenze_rechts;
	}

	public void setOnEastBorder(boolean onEastBorder) {
		this.grenze_rechts = onEastBorder;
	}

	public boolean isOnSouthBorder() {
		return grenze_unten;
	}

	public void setOnSouthBorder(boolean onSouthBorder) {
		this.grenze_unten = onSouthBorder;
	}

	public boolean isOnWestBorder() {
		return grenze_links;
	}

	public void setOnWestBorder(boolean onWestBorder) {
		this.grenze_links = onWestBorder;
	}

	public boolean isTreeAbove() {
		return baum_oben;
	}

	public void setTreeAbove(boolean treeAbove) {
		this.baum_oben = treeAbove;
	}

	public boolean isTreeOnRight() {
		return baum_rechts;
	}

	public void setTreeOnRight(boolean treeOnRight) {
		this.baum_rechts = treeOnRight;
	}
	
	public boolean isGoalAchieved() {
		return ziel_erreicht;
	}

	public void setGoalAchieved(boolean g) {
		this.ziel_erreicht = g;
	}

	public boolean isTreeBelow() {
		return baum_unten;
	}

	public void setTreeBelow(boolean treeBelow) {
		this.baum_unten = treeBelow;
	}

	public boolean isTreeOnLeft() {
		return baum_links;
	}

	public void setTreeOnLeft(boolean treeOnLeft) {
		this.baum_links = treeOnLeft;
	}

	public boolean isLeafAbove() {
		return blatt_oben;
	}

	public void setLeafAbove(boolean leafAbove) {
		this.blatt_oben = leafAbove;
	}

	public boolean isLeafOnRight() {
		return blatt_rechts;
	}

	public void setLeafOnRight(boolean leafOnRight) {
		this.blatt_rechts = leafOnRight;
	}

	public boolean isLeafBelow() {
		return blatt_unten;
	}

	public void setLeafBelow(boolean leafBelow) {
		this.blatt_unten = leafBelow;
	}

	public boolean isLeafOnLeft() {
		return blatt_links;
	}

	public void setLeafOnLeft(boolean leafOnLeft) {
		this.blatt_links = leafOnLeft;
	}

	public boolean isMushroomAbove() {
		return pilz_oben;
	}

	public void setMushroomAbove(boolean mushroomAbove) {
		this.pilz_oben = mushroomAbove;
	}

	public boolean isMushroomOnRight() {
		return pilz_rechts;
	}

	public void setMushroomOnRight(boolean mushroomOnRight) {
		this.pilz_rechts = mushroomOnRight;
	}

	public boolean isMushroomBelow() {
		return pilz_unten;
	}

	public void setMushroomBelow(boolean mushroomBelow) {
		this.pilz_unten = mushroomBelow;
	}

	public boolean isMushroomOnLeft() {
		return pilz_links;
	}

	public void setMushroomOnLeft(boolean mushroomOnLeft) {
		this.pilz_links = mushroomOnLeft;
	}

	public boolean isMushroomEaten() {
		return mushroomEaten;
	}

	public void setMushroomEaten(boolean mushroomEaten) {
		this.mushroomEaten = mushroomEaten;
	}

	public boolean isLeafEaten() {
		return leafEaten;
	}

	public void setLeafEaten(boolean leafEaten) {
		this.leafEaten = leafEaten;
	}

	@Override
	public String toString() {
		return "AbstractMouse [point=" + point + ", started=" + started
				+ ", onNorthBorder=" + grenze_oben + ", onEastBorder="
				+ grenze_links + ", onSouthBorder=" + grenze_unten
				+ ", onWestBorder=" + grenze_rechts + ", treeAbove=" + baum_oben
				+ ", treeOnRight=" + baum_rechts + ", treeBelow=" + baum_unten
				+ ", treeOnLeft=" + baum_links + ", leafAbove=" + blatt_oben
				+ ", leafOnRight=" + blatt_rechts + ", leafBelow=" + blatt_unten
				+ ", leafOnLeft=" + blatt_links + ", mushroomAbove="
				+ pilz_oben + ", mushroomOnRight=" + pilz_rechts
				+ ", mushroomBelow=" + pilz_unten + ", mushroomOnLeft="
				+ pilz_links + ", mushroomEaten=" + mushroomEaten
				+ ", leafEaten=" + leafEaten + "]";
	}
	

	public String getBiegeImUhrzeigerSinnAb() {
		return biegeImUhrzeigerSinnAb;
	}

	public String getWiederhole() {
		return wiederhole;
	}

	public String getGehNachRechts() {
		return gehNachRechts;
	}

	public String getGehWaagerecht() {
		return gehWaagerecht;
	}
	
	
}
