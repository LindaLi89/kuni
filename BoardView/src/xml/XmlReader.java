package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.graphics.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Constants;

public class XmlReader {

	public static Point readXML(String pfad, WorldData spielfeld,
			Obstacles leafs, Obstacles mushrooms, Obstacles trees) {
		try {

			File fXmlFile = new File(pfad);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// Aus Wurzelelement die h�he und breite des Spielfelds lesen
			NodeList spielfeldRootElement = doc
					.getElementsByTagName("Spielfeld");
			Node spielfeldNode = spielfeldRootElement.item(0);
			Element spielfeldElement = (Element) spielfeldNode;
			String height = spielfeldElement.getAttribute("height");
			String width = spielfeldElement.getAttribute("width");
			String randomMushrooms = spielfeldElement
					.getAttribute("randomMushrooms");

			readObjectType(doc, leafs, Constants.Objects.leaf);
			readObjectType(doc, mushrooms, Constants.Objects.mushroom);
			readObjectType(doc, trees, Constants.Objects.tree);

			readObjectAttributes(doc, leafs, "Leafs");
			readObjectAttributes(doc, mushrooms, "Mushrooms");
			readObjectAttributes(doc, trees, "Trees");

			spielfeld.setWidth(Integer.parseInt(width));
			spielfeld.setHeight(Integer.parseInt(height));

			if (randomMushrooms.equals("yes")) {
				spielfeld.setRandomMushrooms(true);
			} else {
				spielfeld.setRandomMushrooms(false);
			}
			
			spielfeld.setGoal(readGoalPoition(doc));
			
			return readMousePoition(doc);

		} catch (Exception e) {
			//TODO �ndern: keine allgemeine Exception abfangen 
			//Reaktion bei Fehlern?
			e.printStackTrace();
			return null;
		}
	}
	
	private static Point readMousePoition(Document doc) {
		NodeList nlFigurTyp = doc.getElementsByTagName("Mouse");
		int xInt = -1, yInt = -1;
		for (int temp = 0; temp < nlFigurTyp.getLength(); temp++) {
			Node nNode = nlFigurTyp.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String x = eElement.getElementsByTagName("PositionX").item(0)
						.getTextContent();
				String y = eElement.getElementsByTagName("PositionY").item(0)
						.getTextContent();
				xInt = Integer.parseInt(x);
				yInt = Integer.parseInt(y);
			}
		}
		return new Point(xInt, yInt);
	}
	
	private static Point readGoalPoition(Document doc) {
		NodeList nlFigurTyp = doc.getElementsByTagName("Goal");
		int xInt = -1, yInt = -1;
		for (int temp = 0; temp < nlFigurTyp.getLength(); temp++) {
			Node nNode = nlFigurTyp.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String x = eElement.getElementsByTagName("PositionX").item(0)
						.getTextContent();
				String y = eElement.getElementsByTagName("PositionY").item(0)
						.getTextContent();
				xInt = Integer.parseInt(x);
				yInt = Integer.parseInt(y);
			}
		}
		return new Point(xInt, yInt);
	}

	private static void readObjectType(Document doc, Obstacles figur,
			String figurTyp) {

		NodeList nlFigurTyp = doc.getElementsByTagName(figurTyp);

		for (int temp = 0; temp < nlFigurTyp.getLength(); temp++) {
			Node nNode = nlFigurTyp.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				// X und Y Koordinate aus den jeweiligen Tags lesen
				String x = eElement.getElementsByTagName("PositionX").item(0)
						.getTextContent();
				String y = eElement.getElementsByTagName("PositionY").item(0)
						.getTextContent();

				// Die X und Y Koordinaten werden als Punkt erstellt und in die
				// Klasse Figuren gespeichert
				Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
				figur.addObstaclePoint(p);

			}
		}

	}

	private static void readObjectAttributes(Document doc, Obstacles figur,
			String figurTypGruppe) {

		NodeList nlFigurTypGruppe = doc.getElementsByTagName(figurTypGruppe);
		for (int temp = 0; temp < nlFigurTypGruppe.getLength(); temp++) {
			Node nNode = nlFigurTypGruppe.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				figur.setMinObstacleNumber(Integer.parseInt(eElement.getAttribute("min")));
				figur.setMaxObstacleNumber(Integer.parseInt(eElement.getAttribute("max")));
				figur.setObstacleDistribution(eElement.getAttribute("verteilung"));

			}
		}
	}
}
