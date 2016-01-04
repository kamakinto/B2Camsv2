package blackboard.plugin.springdemo.spring.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.ReceiptOptions;
import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.platform.servlet.InlineReceiptUtil;
import blackboard.platform.spring.beans.annotations.ContextValue;
import blackboard.plugin.springdemo.dao.CamsPropertiesDAO;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.CamsSettings;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;
import blackboard.plugin.springdemo.model.Properties;
import blackboard.plugin.springdemo.service.BbService;
import blackboard.plugin.springdemo.service.CamsService;

@Controller
public class CamsController {
	
	@Autowired
	BbService bbService;
	
	@Autowired
	CamsService camsService;
	@Autowired
	  private SessionFactory _sessionFactory;
	
	@Autowired
	private CamsPropertiesDAO _dao;
	
	  @RequestMapping( "/CamsController" )
	  public ModelAndView cams(){
		  //Create a new ModelandView Object.
		  ModelAndView mv = new ModelAndView("cams_admin");  
		  
		  Properties props = _dao.load();
		  
		//Set enabled equal to the state specified in the database
		  boolean enabled = props.isEnabled();
		  
		  int frequency = props.getFrequency();
		  int userId = props.getUserId();
		  String username = props.getUsername();
		  String password = props.getPassword();
		  String webaddress = props.getWebAddress();
		  String term = props.getTerm();
		  String year = props.getYear();
		   
		  mv.addObject("username", username);
		  mv.addObject("password", password);
		  mv.addObject("enabled", enabled);
		  mv.addObject("webAddress", webaddress);
		  mv.addObject("year", year);
		  mv.addObject("term", term);
		  mv.addObject("frequency", props.frequencyToString(frequency));
		  return mv;
	  }
	  
	  
	  @RequestMapping("/saveProperties")
	  public ModelAndView saveProps(
			  @RequestParam(value="enabled", required=false) String enabled,
				@RequestParam(value="username", required=false) String username,
				@RequestParam(value="password", required=false) String password,
				@RequestParam(value="webAddress", required=false) String webAddress,
				@RequestParam(value="term", required=false) String term,
				@RequestParam(value="year", required=false) String year,
				@RequestParam(value="frequency", required=false) int frequency) throws Exception
{

		  
		  ModelAndView mv = new ModelAndView("cams_admin_success");
		  // dao is already instantiated, so load the current instance, since properties only has one record that we 
		  //maintain. 
		  
		  Properties props = _dao.load();
		  int userIdInt = 0;
		  
		  // set all properties to incoming parameter values
		  props.enable(Boolean.valueOf(enabled));
		  props.setFrequency(frequency);
		  props.setPassword(password);
		  props.setUsername(username);
		  props.setWebAddress(webAddress);		  
		  props.setUserId(userIdInt);
		  props.setTerm(term);
		  props.setYear(year);
		  
		  //save the prefs
		  _dao.save(props);
		  

		  
			return mv;
}
	  
	  @RequestMapping( "/cams_admin_success" )
	  public ModelAndView camsSuccess(@ContextValue User user){
		  //Create a new ModelandView Object.
		  
		  
		  String username = user.getUserName();
		  ModelAndView mv = new ModelAndView("cams_admin_success");
		  mv.addObject("user", username);
		  
		  
		  return mv;
	  }
	  
	  //@Scheduled(cron="0 5,19 * * *") // set to twice a day
	  public void executeCamsSync(){
		  Properties props = _dao.load();
		  
		  if(props.isEnabled()){
			  
			  HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
				//HashMap<String, ArrayList<String>> courseEnrollmentMapIds = new HashMap<String, ArrayList<String>>();
				courseEnrollmentMap = bbService.getBbCourseEnrollments(); // Blackboard Course Enrollment Map
				//courseEnrollmentMapIds = bbService.getCourseEnrollmentIDs(courseEnrollmentMap);
				List<CamsCourse> result = camsService.getEnrUserToCourses(); //Cams Course Enrollment Map
				List<EnrUserToCourse> syncList = bbService.generateDiffCourseEnrollments(courseEnrollmentMap, result);
				bbService.enrollUsersToCourses(syncList);
		  }
}
	  
	   
	  
}