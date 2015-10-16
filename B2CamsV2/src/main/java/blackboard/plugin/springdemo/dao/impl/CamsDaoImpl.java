package blackboard.plugin.springdemo.dao.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.security.cert.X509Certificate;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class CamsDaoImpl implements CamsDao{
	
	private final static String server_url = "https://www-dev.aup.edu/xmlrpc.php";
	private final static String test_server_url = "http://www.advogato.org/XMLRPC";
	/**
     * Name of remote functions used by this example
     */
    public static final String METHOD_DRUPAL_CAMS_GETENROLLMENT = "blackboard_ws.getEnrollments";

	
	
	
	@PersistenceContext(unitName="CamsHibernatePersistenceUnit")
	private EntityManager entityManagerCams;

	@Override
	@Transactional
	public List<Foo> getFoos() {
		String qlString = "SELECT p FROM Foo p";
		TypedQuery<Foo> query = entityManagerCams.createQuery(qlString, Foo.class);
		return query.getResultList();
		
}

	
	
	
public List<EnrUserToCourse> decodeEnrollmentList(Object element){
		
		return null;
	}
	
	@Override
	public List<EnrUserToCourse> getEnrUserToCoursesWS(){
		String cookie;
		try{
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(server_url));
			config.setBasicUserName("erobinson");
			config.setBasicPassword("H!tokiru1234");
			XmlRpcClient server = new XmlRpcClient();
			server.setConfig(config);
			
			Vector params = new Vector();
			//TODO: Start with hello world, but eventually figure out the mapping that is going to be recieved from the WS.
		String result = (String) server.execute(METHOD_DRUPAL_CAMS_GETENROLLMENT, params);
		//String result = (String) server.execute("system.listMethods", params);
			//List<EnrUserToCourse> resultList = 
				//	new ArrayList<EnrUserToCourse>(decodeEnrollmentList(server.execute(METHOD_DRUPAL_CAMS_GETENROLLMENT, params)));
			//convert results to getEnrUser class and return them
		}catch (XmlRpcException exception){
			System.err.println("javaClient: " + exception);
		} catch (MalformedURLException e){
			System.err.println("javaClient: You put in an incorrect URL for XML-RPC server format:" + e);
		};
		return null;
	}
	@Override
	public String getEnrUserToCoursesWSTest() {
		String result= null;
		String client_username = "blackboard_ws_client";
		String client_password = "bl@ckboardws123";
		String server_url = "https://www-dev.aup.edu/xmlrpc.php";
		
		try{
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(server_url)); //update with Drupal WS URL
			//config.setServerURL(new URL("http://www.advogato.org/XMLRPC")); //update with Drupal WS URL
			XmlRpcClient server = new XmlRpcClient();
			server.setConfig(config);
			Vector params = new Vector();
			params.add(client_username);
			params.add(client_password);
			result = (String) server.execute("blackboard_ws.getEnrollments", params);
			return result;
		}catch (XmlRpcException exception){
			System.err.println("javaClient: " + exception);
			exception.printStackTrace();
		} catch (MalformedURLException e){
			System.err.println("javaClient: You put in an incorrect URL for XML-RPC server format:" + e);
			result = "javaClient: You put in an incorrect URL for XML-RPC server format:" + e;
		};
		
		return result;
	}
	


	

}
