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
		   
		  mv.addObject("username", username);
		  return mv;
	  }
	  
	  
	  @RequestMapping("/saveProperties")
	  public ModelAndView saveProps(
			  @RequestParam(value="enabled", required=false) String enabled,
				@RequestParam(value="username", required=false) String username,
				@RequestParam(value="password", required=false) String password,
				@RequestParam(value="webAddress", required=false) String webAddress,
				@RequestParam(value="userId", required = false) String userId,
				@RequestParam(value="frequency", required=false) int frequency) throws Exception
{

		  
		  ModelAndView mv = new ModelAndView("cams_admin");
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
		  
		  try{
			  Id userIdParam = Id.generateId(User.DATA_TYPE, userId);
			  StringTokenizer sToken = new StringTokenizer(userIdParam.toExternalString(), "_");
			  userIdInt = Integer.parseInt(sToken.nextToken());
		  }catch (PersistenceException e1){
			  e1.printStackTrace();
		  }
		  
		  props.setUserId(userIdInt);
		  
		  //save the prefs
		  _dao.save(props);
		  
//		  try{
//			  ReceiptOptions ro = new ReceiptOptions();
//			  ro.addSuccessMessage("Properties Successfully Updated."); //escape since variable is not intended to contain html
//			  InlineReceiptUtil.addReceiptToRequest(request, ro);
//			  
//			  
//		  }catch (IOException e){
//			  e.printStackTrace();
//		  }
		  
			return mv;
}
}