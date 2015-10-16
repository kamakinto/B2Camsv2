package blackboard.plugin.springdemo.spring.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blackboard.plugin.springdemo.model.Foo;
import blackboard.plugin.springdemo.service.CamsService;

@Controller
public class HelloFooJPAController
{
	
	@Autowired
	CamsService camsService;

  // EM factory reference for our code
 
  @RequestMapping( "/fooJPAController" )
  public ModelAndView helloFoo() throws Exception
  {
	  
		String result= "Robbie";
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
			//String sentence = "Testing to see if the client webservice works in capitalizing the string";
			//params.add(sentence);
			//result = (String) server.execute("test.capitalize", params);
			result = (String) server.execute("blackboard_ws.getEnrollments", params);
			
			
		}catch (XmlRpcException exception){
			System.err.println("javaClient: " + exception);
			exception.printStackTrace();
		} catch (MalformedURLException e){
			e.printStackTrace();
			result = "javaClient: You put in an incorrect URL for XML-RPC server format:" + e;
		}catch (Exception e){
			e.printStackTrace();
		};
		
		
	  ModelAndView mv = new ModelAndView("foo_jpa");
	  
	  //result = camsService.getWSHelloWorld();
//	  if(result == null){
//		  result = "Did Not Reach Web Service.";
//	  }
	  mv.addObject("helloWS", result);
	 // mv.addObject("camsList", camsService.getCourseEnrollmentAndUsers());
	 // mv.addObject("fooList", camsService.getFoos());
	  return mv;
  }
  
  
  
//  @RequestMapping( "/fooJPAController" )
//  public ModelAndView helloFoo( @RequestParam( value = "n", required = false ) String name,
//                                @RequestParam( value = "v", required = false ) String value ) throws Exception
//  {
//    if ( name != null && value != null )
//    {
//      // let's create a Foo object
//      Foo f = new Foo();
//      f.setName( name );
//      f.setValue( value );
//
//      // create an entity manager
//      EntityManager em = _entityManagerFactory.createEntityManager();
//      EntityTransaction tx = em.getTransaction();
//
//      try
//      {
//        // save the Foo to the database
//        tx.begin();
//        em.persist( f );
//        tx.commit();
//      }
//      catch ( Exception err )
//      {
//        tx.rollback();
//        throw err;
//      }
//      finally
//      {
//        em.close();
//      }
//    }
//
//    // now load the Foo's
//    EntityManager em = _entityManagerFactory.createEntityManager();
//    Query q = em.createQuery( "from Foo" );
//
//    @SuppressWarnings( "unchecked" )
//    List<Foo> l = q.getResultList();
//    em.close();
//
//    // pass the list back to the JSP view
//    ModelAndView mv = new ModelAndView( "foo_jpa" );
//   mv.addObject( "fooList", l );
//    return mv;
//  }


}
