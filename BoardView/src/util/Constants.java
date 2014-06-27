package util;

public class Constants {

	public static class Modi {
		public static String simple = "Einfacher Modus";
		public static String advanced = "Erweiterter Modus";
		public static String tournament = "Turniermodus";
		public static String none = null;
	}
	
	public static class WorldSize {
		public static int minWidth = 500;
		public static int minHeight = 500;
	}
		
	public static class Times {
		public static int milli = 1000;
		public static int sec = 60;
	}
	
	public static class Paths {
		public static String iconPath = "..\\icons\\";
		public static String mouseIconPath = "..\\icons\\spielfigurIcons\\";
	}
	
	public static class Icons {
		public static String debugIcon = "Debug.png";
		public static String playIcon = "Play.png";
		public static String recordIcon = "Record.png";
		public static String recordInactiveIcon = "Record_Inactive.png";
		public static String resetIcon = "Reset.png";
	}
	
	public static class Random {
		public static double limit = 0.2;
	}

	public static class Arrows {
		public static final String moveRight = "nach rechts";
		public static final String moveLeft = "nach links";
		public static final String moveUp = "nach oben";
		public static final String moveDown = "nach unten";
	}
	
	public static class GUI {
		public static String setMouse = "Spielfigur setzen";
		public static String scoreGrp = "Punktestand ";
		public static String mouseNameLabel = "Name: ";
		public static String pointsLabel = "Punkte: ";
		public static String emptyString = "";
		public static String canvas = "Garten";
	}
	
	public static class Objects {
		public final static String mouse = "Mouse";
		public final static String leaf = "Leaf";
		public final static String mushroom = "Mushroom";
		public final static String tree = "Tree";
	}
	
	public static class Actions {
		public final static String loadNewWorld = "loadNewWorld";
		public final static String setMouse = "setMouse";
		public final static String unsetMouse = "unsetMouse";
		public final static String setObstacle = "setObstacle";
		public final static String arrow = "arrowAction";
		public final static String play = "playAction";
		public final static String stop = "stopAction";
		public final static String record = "recordAction";
		public final static String stopRecord = "stopRecordAction";
		public final static String reset = "resetAction";
		public final static String okButton = "okButton";
	}
	
	public static class Ifs {
		public final static String treeLeft = "Baum links";
		public final static String treeAbove = "Baum oben";
		public final static String treeRight = "Baum rechts";
		public final static String treeBelow = "Baum unten";
		public final static String mushroomLeft = "Pilz links";
		public final static String mushroomAbove = "Pilz oben";
		public final static String mushroomRight = "Pilz rechts";
		public final static String mushroomBelow = "Pilz unten";
		public final static String leafLeft = "Blatt links";
		public final static String leafAbove = "Blatt oben";
		public final static String leafRight = "Blatt rechts";
		public final static String leafBelow = "Blatt unten";
		public final static String leftBorder = "am linken Rand";
		public final static String topBorder = "am oberen Rand";
		public final static String rightBorder = "am rechten Rand";
		public final static String downBorder = "am unteren Rand";
		public final static String defaultStep = "immer";
		public final static String goalAchiewed = "Ziel erreicht";
	}
	
	public static class Thens {
		public final static String left = Constants.Arrows.moveLeft;
		public final static String up = Constants.Arrows.moveUp;
		public final static String right = Constants.Arrows.moveRight;
		public final static String down = Constants.Arrows.moveDown;
		public final static String horizontal = "nach rechts oder nach links";
		public final static String vertical = "nach oben oder nach unten";
		public final static String random = "zufällig";
		public final static String stop = "stop";
	}
	
	public static class FileNames {
		public final static String strategy = "strategy.txt";
		public final static String rules = "rules.txt";
	}
	
}
