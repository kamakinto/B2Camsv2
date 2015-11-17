package blackboard.plugin.springdemo.spring.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.spring.web.annotations.IdParam;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.service.BbService;
import blackboard.plugin.springdemo.service.CamsService;


@Controller
public class HelloCourseController
{// Annotates a variable so that the Spring container will 
  // attempt to wire the reference for you automatically.
  @Autowired
  private CourseMembershipDbLoader _membershipLoader;

  @Autowired
  private UserDbLoader _userLoader;
  
  @Autowired
  private CourseDbLoader _courseLoader;
  
  @Autowired 
  private BbService bbService;
  
  @Autowired
  private CamsService camsService;
  
  @RequestMapping("/allCourses")
  public ModelAndView listAllCourseUsers()
		  throws KeyNotFoundException, PersistenceException{
	  ModelAndView mv = new ModelAndView("allCourses");
	  HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
	  HashMap<String, ArrayList<String>> courseEnrollmentMapIds = new HashMap<String, ArrayList<String>>();
	  List<CamsCourse> usersToEnroll = camsService.getEnrUserToCourses();

	  courseEnrollmentMap = bbService.getBbCourseEnrollments();
	  courseEnrollmentMapIds = bbService.getCourseEnrollmentIDs(courseEnrollmentMap);
	  List<EnrUserToCourse> bbUsersToEnroll = bbService.generateDiffCourseEnrollments(courseEnrollmentMap, usersToEnroll);
	  bbService.enrollUsersToCourses(bbUsersToEnroll);
	  
	  mv.addObject("courseEnrollmentMap", courseEnrollmentMapIds);
	  mv.addObject("courses", bbService.getAllBbCourses());
	  mv.addObject("courseUsersToEnroll", bbUsersToEnroll);
	return mv;
  }

  @RequestMapping( "/course_users" )
  /**
   *  @IdParam will take the string in the request parameter listed and use it as an Id object
   to  look up the object based on the type.   In this case, it will convert the string to 
   an Id and look up the Course based on the Id.*/
  public ModelAndView listCourseUsers( @IdParam( "cid" ) Course course )
    throws KeyNotFoundException, PersistenceException
  {
    ModelAndView mv = new ModelAndView( "course_users" );
    mv.addObject( "course", course );

    // Load some data and put it in the model
    List<CourseMembership> members = _membershipLoader.loadByCourseId( course.getId() );
    List<User> users = new LinkedList<User>();

   //Load all the courses , later configure it to list each course, and its enrollement
    List<Course> courses = _courseLoader.loadAllCourses();
	    for ( CourseMembership member : members )
	    {
	      users.add( _userLoader.loadById( member.getUserId() ) );
	    }
    
    mv.addObject( "users", users );
    mv.addObject("courses", courses);
    return mv;
  }
}
