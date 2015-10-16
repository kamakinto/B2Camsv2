package blackboard.plugin.springdemo.spring.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.course.Course;
import blackboard.data.user.User;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;
import blackboard.plugin.springdemo.service.BbService;
import blackboard.plugin.springdemo.service.CamsService;

@Controller
public class CamsController {
	
	@Autowired
	BbService bbService;
	
	@Autowired
	CamsService camsService;
	
	  @RequestMapping( "/camsController" )
	  public ModelAndView cams() throws Exception
	  {
		  //camsCourseEnrollments = getcamsCourseEnrollments(); TODO: GET CREDENTIALS TO ACCURATELY TEST CAMS PART
		  HashMap<String, ArrayList<String>> camsCourseEnrollments = null;
		  HashMap<Course, ArrayList<User>> bbCourseEnrollments = bbService.getBbCourseEnrollments();
		  List<EnrUserToCourse> diffCourseEnrollments = bbService.generateDiffCourseEnrollments(bbCourseEnrollments,camsCourseEnrollments);
		  bbService.enrollUsersToCourses(diffCourseEnrollments);
		  ModelAndView mv = new ModelAndView("camsAdmin");

		  mv.addObject("enrollmentList", diffCourseEnrollments);
		return mv;
	  }

}
