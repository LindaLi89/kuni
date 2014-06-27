package server;

import java.net.InetAddress;

import log.XLogHandler;

import org.apache.xmlrpc.common.TypeConverterFactoryImpl;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping; 

public class JavaServer {
	   public static void main(String[] args) throws Exception
	    {
	        WebServer webServer = new WebServer(8080, InetAddress.getByName("127.0.0.1"));
	       
	        
	        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
	       
	        // use reflection for (dynamic) mapping
	        PropertyHandlerMapping dhm = new PropertyHandlerMapping();
	   
	        // add "Calculator" handler - used by regular agent
	        dhm.addHandler("MouseImpl", MouseImpl.class);
	        dhm.addHandler("XLog",XLogHandler.class);
	        // add Adder handler - using full name for use by dynamic proxy
	        //dhm.addHandler(Adder.class.getName(), AdderImpl.class);
	        xmlRpcServer.setHandlerMapping(dhm);
	       
	        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
	        serverConfig.setEnabledForExtensions(true);
	        serverConfig.setContentLengthOptional(false);

	        System.out.println("Attempting to start XML-RPC Server...");
	        webServer.start();
	        System.out.println("Started successfully.");
	    } 
}
