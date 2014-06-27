package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.exceptions.FigurAusserhalbException;

import org.eclipse.swt.graphics.Point;

import spielbrettview.customviews.wizards.XWizardPageOne;
import util.Constants;
import xml.Obstacles;
import xml.WorldData;
import xml.XmlReader;

public class World {

	private int width;
	private int height;
	private boolean randomMushrooms;
	private Point mouseStartPoint = null;
	private Point goal = null;

	public boolean isRandomMushrooms() {
		return randomMushrooms;
	}
	public void setRandomMushrooms(boolean randomMushrooms) {
		this.randomMushrooms = randomMushrooms;
	}

	private HashMap<Point, AbstractObject> obstacles = new HashMap<Point, AbstractObject>();
	WorldData spielfeld;

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	private int northBorder;
	private int southBorder;
	private int eastBorder;
	private int westBorder;

	public int getNorthBorder() {
		return northBorder;
	}

	public int getSouthBorder() {
		return southBorder;
	}

	public int getEastBorder() {
		return eastBorder;
	}

	public int getWestBorder() {
		return westBorder;
	}

	public HashMap<Point, AbstractObject> getObstacles() {
		return obstacles;
	}

	protected HashMap<Point, Leaf> getLeafs() {
		HashMap<Point, Leaf> result = new HashMap<Point, Leaf>();
		Iterator<Point> iter = obstacles.keySet().iterator();
		while (iter.hasNext()) {
			Point o = iter.next();
			if (obstacles.get(o) instanceof Leaf) {
				result.put(o, (Leaf) obstacles.get(o));
			}
		}
		return result;
	}

	protected HashMap<Point, Mushroom> getMushrooms() {
		HashMap<Point, Mushroom> result = new HashMap<Point, Mushroom>();
		Iterator<Point> iter = obstacles.keySet().iterator();
		while (iter.hasNext()) {
			Point o = iter.next();
			if (obstacles.get(o) instanceof Mushroom) {
				result.put(o, (Mushroom) obstacles.get(o));
			}
		}
		return result;
	}

	protected HashMap<Point, Tree> getTrees() {
		HashMap<Point, Tree> result = new HashMap<Point, Tree>();
		Iterator<Point> iter = obstacles.keySet().iterator();
		while (iter.hasNext()) {
			Point o = iter.next();
			if (obstacles.get(o) instanceof Tree) {
				result.put(o, (Tree) obstacles.get(o));
			}
		}
		return result;
	}

	public AbstractObject getObjectAtPoint(int i, int j) {
		AbstractObject result = null;
		boolean found = false;
		Iterator<Point> iter = obstacles.keySet().iterator();
		while (!found && iter.hasNext()) {
			Point c = iter.next();
			if (c.x == i && c.y == j) {
				result = obstacles.get(c);
				found = true;
			}
		}
		return result;
	}

	public int[] calculateGrid() {
		int[] spaces = new int[2];
		spaces[0] = width;
		spaces[1] = height;

		return spaces;
	}

	public List<Point> getMushroomPoints() {
		ArrayList<Point> result = new ArrayList<Point>();
		HashMap<Point, Mushroom> hm = getMushrooms();
		Iterator<Point> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	public List<Point> getLeafPoints() {
		ArrayList<Point> result = new ArrayList<Point>();
		HashMap<Point, Leaf> hm = getLeafs();
		Iterator<Point> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	public List<Point> getTreePoints() {
		ArrayList<Point> result = new ArrayList<Point>();
		HashMap<Point, Tree> hm = getTrees();
		Iterator<Point> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	public void removeObstacle(int x, int y) {
		obstacles.remove(new Point(x, y));
	}

	public World(String pfad) {
		spielfeld = new WorldData();
		Obstacles leafs = new Obstacles("Leafs", "Blatt");
		Obstacles mushrooms = new Obstacles("Mushrooms", "Pilz");
		Obstacles trees = new Obstacles("Trees", "Baum");
		spielfeld.setName(pfad
				.substring(XWizardPageOne.getXmlPfad().length() + 1));

		mouseStartPoint = XmlReader.readXML(pfad, spielfeld, leafs, mushrooms, trees);
		
		goal = spielfeld.getGoal();
		
		setWidth(spielfeld.getWidth());
		setHeight(spielfeld.getHeight());
		northBorder = 0;
		southBorder = northBorder + height - 1;
		westBorder = 0;
		eastBorder = westBorder + width - 1;
		setRandomMushrooms(spielfeld.isRandomMushrooms());

		String message = Constants.GUI.emptyString;
		try {
			figurSetzen(spielfeld, mushrooms);
		} catch (FigurAusserhalbException e) {
			message = message.concat(e.getMessage() + ", ");
		}
		try {
			figurSetzen(spielfeld, leafs);
		} catch (FigurAusserhalbException e) {
			message = message.concat(e.getMessage() + ", ");
		}
		try {
			figurSetzen(spielfeld, trees);
		} catch (FigurAusserhalbException e) {
			message = message.concat(e.getMessage() + ", ");
		}

		if (message.length() > 0) {
			message = message.substring(0, message.lastIndexOf(',') - 1);
		}
		// TODO hier noch eine Fehlermeldung an der GUI generieren
	}

	private void figurSetzen(WorldData spielfeld, Obstacles figur)
			throws FigurAusserhalbException {
		ArrayList<Point> points = figur.getFigurPoints();
		String figurBeschreibungKinder = figur.getChildrenDescription();
		String fehlermeldung = Constants.GUI.emptyString;

		// Koordinaten manuell gesetzt
		Iterator<Point> i = points.iterator();
		int anzahlGesetzt = points.size();
		while (i.hasNext()) {
			Point p = (Point) i.next();
			int x = p.x;
			int y = p.y;

			if (x >= width || y >= height) {
				fehlermeldung = fehlermeldung
						.concat("Die Koordinaten ["
								+ x
								+ ", "
								+ y
								+ "] ("
								+ figurBeschreibungKinder
								+ ") liegen au\u00DFerhalb des Spielfeldes. Bitte Spielfeld vergr\u00F6\u00DFern oder dem "
								+ figurBeschreibungKinder
								+ " eine andere Position zuweisen." + "\n");
				throw new FigurAusserhalbException(fehlermeldung);

				// Properties prop = System.getProperties();
				// String os_name = prop.getProperty("os.name");
				// os_name = os_name.substring(0, 3);
				// if (os_name.equals("Mac")) {
				//
				// } else if (os_name.equals("Win")) {
				// JFrame frame = new JFrame("Falsche Koordinaten angegeben");
				// JOptionPane.showMessageDialog(frame, fehlermeldung);
				//
				// }

			} else {
				figurAufSpielfeldSetzen(spielfeld, figur.getType(), x, y);
			}

		}

		// nur wenn bisher zu wenige Figuren gesezt wurden (anzahlGesetzt < max)
		// werden weitere Figuren gesetzt
		if (anzahlGesetzt < figur.getMaxObstacleNumber()) {
			zufallsFigurSetzen(spielfeld, figur, anzahlGesetzt);
		}

		if (fehlermeldung.length() > 0) {
			throw new FigurAusserhalbException(fehlermeldung);
		}

	}

	private void zufallsFigurSetzen(WorldData spielfeld, Obstacles figur,
			int anzahlGesetzt) {
		int width = spielfeld.getWidth();
		int height = spielfeld.getHeight();

		// Koordinaten Bereich abh�ngig von width und height
		int widthBereichMin = 0;
		int widthBereichMax = width - 1;
		int heightBereichMin = 0;
		int heightBereichMax = height - 1;

		int min = figur.getMinObstacleNumber();
		int max = figur.getMaxObstacleNumber();
		int zufallAnzahl = ((int) (Math.random() * (max - min + 1) + min));

		if (anzahlGesetzt < zufallAnzahl) {
			zufallAnzahl = zufallAnzahl - anzahlGesetzt;

			if (figur.getObstacleDistribution().equals("zufaellig")) {
				for (int j = 0; j < zufallAnzahl; j++) {
					int x = ((int) (Math.random()
							* (widthBereichMax - widthBereichMin + 1) + widthBereichMin));
					int y = ((int) (Math.random()
							* (heightBereichMax - heightBereichMin + 1) + heightBereichMin));

					figurAufSpielfeldSetzen(spielfeld, figur.getType(), x, y);
				}
			} else if (figur.getObstacleDistribution().equals("symmetrisch")) {
				int anzahlReihen = 0;
				int jedeXteReihe = 0;
				int jedeXteSpalte = 0;
				int zufallTeiler = ((int) (Math.random()
						* ((zufallAnzahl / 2) - 2 + 1) + 2));
				int anzahlProReihe = zufallTeiler;

				if (anzahlProReihe != 0) {
					anzahlReihen = zufallAnzahl / anzahlProReihe;
					jedeXteSpalte = (width / anzahlProReihe);
				} else {
					anzahlReihen = zufallAnzahl;
					jedeXteSpalte = width;
				}

				if (anzahlReihen != 0) {
					jedeXteReihe = (height / anzahlReihen);
				} else {
					jedeXteReihe = height;
				}

				for (int i = 0; i < anzahlReihen; i++) {
					for (int j = 0; j < anzahlProReihe; j++) {

						int x = jedeXteSpalte - 1;
						int y = jedeXteReihe - 1;

						figurAufSpielfeldSetzen(spielfeld, figur.getType(), x, y);

						if (anzahlProReihe != 0) {
							jedeXteSpalte += (width / anzahlProReihe);
						} else {
							jedeXteSpalte = width;
						}
					}
					if (anzahlProReihe != 0) {
						jedeXteSpalte = (width / anzahlProReihe);
					} else {
						jedeXteSpalte = width;
					}
					if (anzahlReihen != 0) {
						jedeXteReihe += (height / anzahlReihen);
					} else {
						jedeXteReihe = height;
					}
				}

			} else if (figur.getObstacleDistribution().equals("gleichmaessig")) {
				int anzahlFelder = spielfeld.getHeight() * spielfeld.getWidth();
				int jedesXteFeld = anzahlFelder / zufallAnzahl;

				int kaestchen = 1;
				// Zeilen durchgehen
				for (int i = 0; i < spielfeld.getHeight(); i++) {
					// Spalten durchgehen
					for (int j = 0; j < spielfeld.getWidth(); j++) {
						if (kaestchen == jedesXteFeld) {
							int x = j;
							int y = i;

							figurAufSpielfeldSetzen(spielfeld, figur.getType(),
									x, y);
							kaestchen = 0;
						}
						kaestchen++;

					}

				}

			}
		}
	}

	private void figurAufSpielfeldSetzen(WorldData spielfeld,
			String figurTypGruppe, int x, int y) {
		width = spielfeld.getWidth();
		height = spielfeld.getHeight();

		if (getObjectAtPoint(x, y) != null) {

			if ((y + 1) < height) {
				y++;
				figurAufSpielfeldSetzen(spielfeld, figurTypGruppe, x, y);
			} else if ((x + 1) < width) {
				x++;
				figurAufSpielfeldSetzen(spielfeld, figurTypGruppe, x, y);

			}

		} else {

			if (figurTypGruppe.equals("Leafs")) {
				obstacles.put(new Point(x, y), new Leaf());
			}
			if (figurTypGruppe.equals("Mushrooms")) {
				obstacles.put(new Point(x, y), new Mushroom());
			}
			if (figurTypGruppe.equals("Trees")) {
				obstacles.put(new Point(x, y), new Tree());
			}
		}
	}

	public void randomMushroom() {
		figurAufSpielfeldSetzen(spielfeld, "Leafs", 1, 1);
	}

	public Point getMouseStartPoint() {
		return mouseStartPoint;
	}

	public Point getGoal() {
		return goal;
	}
	
}
