package blackboard.plugin.springdemo.spring.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.service.BbService;
import blackboard.plugin.springdemo.service.CamsService;

@Controller
public class HelloFooJPAController
{
	
	@Autowired
	CamsService camsService;
	
	@Autowired 
	private BbService bbService;

  // EM factory reference for our code
 
  @RequestMapping( "/fooJPAController" )
  public ModelAndView helloFoo() throws Exception
  {
	HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
	HashMap<String, ArrayList<String>> courseEnrollmentMapIds = new HashMap<String, ArrayList<String>>();
	
	courseEnrollmentMap = bbService.getBbCourseEnrollments(); // Blackboard Course Enrollment Map
	courseEnrollmentMapIds = bbService.getCourseEnrollmentIDs(courseEnrollmentMap);
	
	List<CamsCourse> result = camsService.getEnrUserToCourses(); //Cams Course Enrollment Map
	List<EnrUserToCourse> syncList = bbService.generateDiffCourseEnrollments(courseEnrollmentMap, result);
	  ModelAndView mv = new ModelAndView("foo_jpa");
	  
	  mv.addObject("helloWS", result);
	  mv.addObject("bbCourseEnrollmentMap", courseEnrollmentMapIds);
	  mv.addObject("syncList", syncList);
	
	
	  
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
