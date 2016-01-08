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
  
  
}
