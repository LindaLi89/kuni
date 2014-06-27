package rpc;

import java.net.MalformedURLException;
import java.net.URL;

import model.AbstractObject;
import model.AdvancedMouse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import util.Constants;

public class RPCClient {
	
	private static RPCClient instance = null;
	private XmlRpcClient client;

	private String className;
	
	/*
	 * initializes the mouse object at the remote serevr. Must be called before all other 
	 * server calls
	 */
	public  void callSetMouse() throws XmlRpcException {
        // make the a regular call
        Object[] params = new Object[] { className };
        Boolean result = (Boolean) client.execute("MouseImpl.setMouse", params);   
	}
	
	/*
	 * sets the mouse object at the filed with the coordinates x (horizontal) and y (vertical)
	 */
	public  void callSetMouse(int x, int y) throws XmlRpcException {
        // make the a regular call
        Object[] params = new Object[] { className, x, y };
        Boolean result = (Boolean) client.execute("MouseImpl.setMouse", params);   
	}
	
	/**
	 * asks for the next step to be executed by the mouse
	 * @param mouse: the current mouse object, not allowed to be null 
	 * @param objectEaten: object which was eaten by the mouse performing last step. May be null.
	 * @return the next step. Possible values: 	rechts, oben, links, unten, stop, waagerecht, senkrecht, 
	 * zufall, nichts, wiederhole, gehUntenZurueck, biegeImUhrzeigerSinnAb, gehZurueck
	 * @throws XmlRpcException
	 */
	public String callDoNextStep(AdvancedMouse mouse, AbstractObject objectEaten) throws XmlRpcException {
        // (int x, int y, boolean north, boolean east, boolean south, boolean west, 
		// String top, String right, String bottom, String left, String objectName
		//boolean goalAchieved)
		int x = mouse.getPositionX();
		int y = mouse.getPositionY();
		
		String nameAbove = getObjectName(mouse.getObjectAbove());		
		String nameRight = getObjectName(mouse.getObjectOnRight());
		String nameBelow = getObjectName(mouse.getObjectBelow());
		String nameLeft = getObjectName( mouse.getObjectOnLeft());
		
		String name = Constants.GUI.emptyString;
		if(objectEaten != null) {
			name = objectEaten.getName();
		}
		
        Object[] params = new Object[]	{ x, y, 
        		mouse.onTopBorder(y), mouse.onRightBorder(x), mouse.onBottomBorder(y), mouse.onLeftBorder(x),
        		nameAbove,  nameRight, nameBelow, nameLeft, 
        		name,
        		mouse.goalAchieved()
        };
        String result = (String) client.execute("MouseImpl.doNextStep", params);
        
        return result;  
	}

	private String getObjectName(AbstractObject obj) {
		String name = Constants.GUI.emptyString;
		if(obj != null) {
			name = obj.getClass().getName();
		}
		return name;
	}
	
	
	/**
	 * calls the server to get the start position of the Mouse object
	 * @param width: width of the current world
	 * @param heigth: height of the current world
	 * @return start position of the Mouse object
	 * @throws XmlRpcException
	 */
	public  int[] callDefineStartPosition(int width, int heigth) throws XmlRpcException {
        Object[] params = new Object[] { width, heigth };
        Integer result1 = (Integer) client.execute("MouseImpl.setStartPositionX", params);
        Integer result2 = (Integer) client.execute("MouseImpl.setStartPositionY", params);

        return (new int[]{result1, result2});
       
	}
	
	public RPCClient() throws MalformedURLException, XmlRpcException {
        // create configuration
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://127.0.0.1:8080/server"));
        config.setEnabledForExtensions(true);  
        config.setConnectionTimeout(60 * 1000);
        config.setReplyTimeout(60 * 1000);

        client = new XmlRpcClient();
       
        // use Commons HttpClient as transport
        client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
        // set configuration
        client.setConfig(config);
    } 
	
	public static RPCClient getInstance() {
		if(instance == null) {
			try {
				instance = new RPCClient();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlRpcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * sets the class to be used as the mouse's strategy at the server side 
	 * the class name corresponds the the selection, the users does on the GUI (Button "Strategie wählen")
	 * @param className
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	/**
	 * 
	 * @return an array of strategy names to be displayed on the GUI 
	 * (selection box at the (Button "Strategie wählen"))
	 * The array contains String objects
	 */
	public Object[] callGetAllStrategies(){
		try{
			Object[] params = new Object[]{};
			Object[] result = (Object[]) client.execute("MouseImpl.getAllStrategies", params);
			return result;
		}
		catch(XmlRpcException e){
			//TODO D�rsam: besser: Fehlermdlung an den Benutzer!
			e.printStackTrace();
		}
		return null;
	}
	
}
