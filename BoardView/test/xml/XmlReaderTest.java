package xml;

import static org.junit.Assert.*;

import org.eclipse.swt.graphics.Point;
import org.junit.Test;



public class XmlReaderTest {

	@Test
	public void testReadXml() {
		String path = "src/xml/XML_Beispiel_Daten/MiniWorld.xml";
		
		// assume empty elements (would be overwritten otherwise)
		WorldData world = new WorldData();
		world.setName("test world");
		/**
		 * Obstacles are specified to be Extensions of the abstract class
		 * "AbstractObject", which is not implemented. Instead we've got
		 * constructors with super()-calls, although, the super class does
		 * not exist.
		 */
		Obstacles leafs = new Obstacles("leaf", "Blatt"); //@todo
		Obstacles shrooms = new Obstacles("mushroom", "Pilz"); //@todo
		Obstacles trees = new Obstacles ("trees", "Baum"); //@todo
		
		assertNull(leafs.getObstacleDistribution());
		Point point = XmlReader.readXML(path, world, leafs, shrooms, trees);
		assertNotNull(leafs.getObstacleDistribution());

	}
}
