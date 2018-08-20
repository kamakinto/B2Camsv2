package blackboard.plugin.springdemo.spring.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.plugin.springdemo.dao.CamsPropertiesDAO;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Properties;
import blackboard.plugin.springdemo.model.Usersync;
import blackboard.plugin.springdemo.service.BbService;
import blackboard.plugin.springdemo.service.CamsService;

@Controller
public class HelloFooJPAController
{
	@Autowired
	private CamsPropertiesDAO _dao; 
	
	@Autowired
	CamsService camsService;
	
	@Autowired 
	private BbService bbService;
	
	public String frequency_sync_cron = "";

  // EM factory reference for our code
	
	@RequestMapping( "/createUser" )
	  public ModelAndView CreateUser() throws Exception
	  {
		  ModelAndView mv = new ModelAndView("user_creation"); 		  
		  //Get a list of users to upload
		 // ArrayList<Usersync> users = camsService.getNewBBUsers();
		  Usersync user1 = new Usersync("a123", "STUDENT", "testUser123", "creation", "");
		  Usersync user2 = new Usersync("a456", "STUDENT", "testUser456", "creation", "");
		  Usersync user3 = new Usersync("a789", "STUDENT", "testUser789", "creation", "");
		  
		  ArrayList<Usersync> users = new ArrayList<Usersync>();
		  users.add(user1);
		  users.add(user2);
		  users.add(user3);		  
		  
		  //loop through that list and run the service below
		  for(Usersync newUser: users){
			  bbService.createUser(newUser.getUsername(), newUser.getRole(), newUser.getFirstName(), newUser.getLastName());
		  }
		  // get service and pass it the correct variables to create a dummy user
		  //bbService.createUser("a1234567", "STUDENT", "TestUser", "creation");

		
		  
		  return mv;
	  }
	  
 
  @RequestMapping( "/fooJPAController" )
  public ModelAndView helloFoo() throws Exception
  {
	HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
	
	Properties props = _dao.load();
	String term = props.getTerm() + props.getYear();
	courseEnrollmentMap = bbService.getBbCourseEnrollments(); // Blackboard Course Enrollment Map
	
	List<CamsCourse> result = camsService.getEnrUserToCourses(); //Cams Course Enrollment Map
	List<EnrUserToCourse> syncList = bbService.generateDiffCourseEnrollments(courseEnrollmentMap, result);
	ModelAndView mv = new ModelAndView("foo_jpa");
	int numOfCamsRecords = result.size();
	int numOfBbRecords = courseEnrollmentMap.size();
	int numOfRecordsForSync = syncList.size();
	
	
	mv.addObject("CamsRecordsSize", numOfCamsRecords);
	mv.addObject("bbRecordSize", numOfBbRecords);
	mv.addObject("syncListSize", numOfRecordsForSync);
	mv.addObject("helloWS", result);
	mv.addObject("bbCourseEnrollmentMap", courseEnrollmentMap);
	mv.addObject("syncList", syncList);
	mv.addObject("term", term);
	bbService.enrollUsersToCourses(syncList); 
	
	  
	  return mv;
  }
  
  @RequestMapping( "/BatchUnenroll" )
  public ModelAndView BatchUnenrollment() throws Exception
  {
	HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
	
	Properties props = _dao.load();
	String term = props.getTerm() + props.getYear();
	courseEnrollmentMap = bbService.getBbCourseEnrollments(); // Blackboard Course Enrollment Map
	
	List<CamsCourse> result = camsService.getEnrUserToCourses(); //Cams Course Enrollment Map
	List<EnrUserToCourse> syncList = bbService.generateUnenrollmentList(courseEnrollmentMap, result);
	ModelAndView mv = new ModelAndView("unenroll_results");
	int numOfCamsRecords = result.size();
	int numOfBbRecords = courseEnrollmentMap.size();
	int numOfRecordsForSync = syncList.size();
	
	
	mv.addObject("CamsRecordsSize", numOfCamsRecords);
	mv.addObject("bbRecordSize", numOfBbRecords);
	mv.addObject("syncListSize", numOfRecordsForSync);
	  mv.addObject("helloWS", result);
	  mv.addObject("bbCourseEnrollmentMap", courseEnrollmentMap);
	  mv.addObject("syncList", syncList);
	  mv.addObject("term", term);
	//bbService.UnenrollUsersToCourses(syncList);
	
	  
	  return mv;
  }
  
  
 
  
  
  //@Scheduled(cron="*/5 * * * * MON-FRI") // set to twice a day
  public void executeCamsSync(){
	  
	  
	  HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
		HashMap<String, ArrayList<String>> courseEnrollmentMapIds = new HashMap<String, ArrayList<String>>();
		
		courseEnrollmentMap = bbService.getBbCourseEnrollments(); // Blackboard Course Enrollment Map
		courseEnrollmentMapIds = bbService.getCourseEnrollmentIDs(courseEnrollmentMap);
		List<CamsCourse> result = camsService.getEnrUserToCourses(); //Cams Course Enrollment Map
		List<EnrUserToCourse> syncList = bbService.generateDiffCourseEnrollments(courseEnrollmentMap, result);
		bbService.enrollUsersToCourses(syncList);
  }
  
  public void unenrollCourseSync(){
	HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
	HashMap<String, ArrayList<String>> courseEnrollmentMapIds = new HashMap<String, ArrayList<String>>();
	List<CamsCourse> result = camsService.getEnrUserToCourses(); //Cams Course Enrollment Map

		
	courseEnrollmentMap = bbService.getBbCourseEnrollments(); // Blackboard Course Enrollment Map
	List<EnrUserToCourse> syncList = bbService.generateUnenrollmentList(courseEnrollmentMap, result);
	bbService.UnenrollUsersToCourses(syncList);
			  
  }
  
  
}
