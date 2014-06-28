package xml;

import static org.junit.Assert.*;

import org.eclipse.swt.graphics.Point;
import org.junit.Test;



public class XmlReaderTest { 

	@Test
	public void testReadXml() {
		final String filePath = "src/xml/XML_Beispiel_Daten/MiniWorld.xml";

		// assume empty elements (would be overwritten otherwise)
		final WorldData worldData = new WorldData();
		worldData.setName("test world");
		/**
		 * Obstacles are specified to be Extensions of the abstract class
		 * "AbstractObject", which is not implemented. Instead we've got
		 * constructors with super()-calls, although, the super class does
		 * not exist.
		 */ 
		final Obstacles leafs = new Obstacles("leaf", "Blatt"); //@todo
		final Obstacles shrooms = new Obstacles("mushroom", "Pilz"); //@todo
		final Obstacles trees = new Obstacles ("trees", "Baum"); //@todo
		  
		assertNull(leafs.getObstacleDistribution());
		Point point = XmlReader.readXML(filePath, worldData, leafs, shrooms, trees);
		assertNotNull(leafs.getObstacleDistribution());

	}
}
