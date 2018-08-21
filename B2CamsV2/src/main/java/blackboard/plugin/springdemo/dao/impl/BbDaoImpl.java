package blackboard.plugin.springdemo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackboard.data.ValidationException;
import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.course.CourseMembership.Role;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.course.CourseMembershipDbPersister;
import blackboard.persist.user.UserDbLoader;
import blackboard.persist.user.UserDbPersister;
import blackboard.plugin.springdemo.dao.BbDao;
import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.dao.CamsPropertiesDAO;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.Foo;
import blackboard.plugin.springdemo.model.Properties;

@Component
public class BbDaoImpl implements BbDao{
	
@Autowired
	private CourseMembershipDbLoader _membershipLoader;

//@Autowired
//private CourseMembershipDbPersister _courseMembershipPersister;

  @Autowired
  private UserDbLoader _userLoader;
  
  @Autowired
  private CourseDbLoader _courseLoader;
  
  @Autowired 
  private CamsDao camsDao;
  
  @Autowired
	private CamsPropertiesDAO _dao;

	@Override
	public List<Foo> getFoos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Add a method to only return the courses that are available
	public List<Course> getAvailableBbCourses(List<Course> allBbCourses){
		
		//build a new list, and iterate through all the courses. only add the courses that are available to the new list.
		
		return null;
	}
	
	@Override
	public List<Course> getCoursesInSemester(List<Course> allBbCourses){
		Properties props = _dao.load();
		String semester = getSemester(props.getTerm());
		String term = semester + props.getYear();
		List<Course> bbCoursesInTerm = new ArrayList<Course>();
		
		for(Course bbcourse: allBbCourses){
			if(bbcourse.getCourseId().contains(term)){
				bbCoursesInTerm.add(bbcourse);
				
			}
		} 
		return bbCoursesInTerm;
	}

	
	@Override
	public List<Course> getAllBbCourses(){
				List <Course> allCourses = new ArrayList<Course>();
				try {
					allCourses = _courseLoader.loadAllCourses();
					
				} catch (PersistenceException e) {
					e.printStackTrace();
				}
				return allCourses;
			}

	@Override
	public List<CourseMembership> getCourseMembers(Id courseId) {
		List<CourseMembership> members = new ArrayList<CourseMembership>();
		try {
			 members = _membershipLoader.loadByCourseId(courseId);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	@Override
	public User getUserById(Id id){
		User user = new User();
		try {
			user = _userLoader.loadById(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	@Override
	public Boolean userExists(String netId){
		return false;
	}

	@Override
	public void enrollUser(String username, String role, String courseId) {
		try{
			CourseMembership newEnrollment = new CourseMembership();
			if(role.contains("INSTRUCTOR")){
				newEnrollment.setRole(CourseMembership.Role.INSTRUCTOR);	
			}else{
				newEnrollment.setRole(CourseMembership.Role.STUDENT);
			}
		newEnrollment.setUserId(_userLoader.loadByUserName(username).getId());
		newEnrollment.setCourseId(_courseLoader.loadByCourseId(courseId).getId());
		CourseMembershipDbPersister.Default.getInstance().persist(newEnrollment);
		} catch (PersistenceException | ValidationException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void UnEnrollUser(String username, String role, String courseId) {
		try{
			CourseMembership newEnrollment = new CourseMembership();
			if(role.contains("INSTRUCTOR")){
				newEnrollment.setRole(CourseMembership.Role.INSTRUCTOR);	
			}else{
				newEnrollment.setRole(CourseMembership.Role.STUDENT);
			}
		newEnrollment.setUserId(_userLoader.loadByUserName(username).getId());
		newEnrollment.setCourseId(_courseLoader.loadByCourseId(courseId).getId());
		CourseMembershipDbPersister.Default.getInstance().deleteByCourseIdAndUserId(newEnrollment.getCourseId(), newEnrollment.getUserId());
		} catch (PersistenceException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void createUser(String netid, String role, String firstName, String lastName){
		String emailAddress = netid + "@aup.edu";
		
		User user = new User();
		user.setUserName(netid);
		user.setPassword("");
		user.setEmailAddress(emailAddress);
		user.setFamilyName(firstName);
		user.setGivenName(lastName);
		
		//TODO: Finish completing the user properties for persistence
		try {
			UserDbPersister userPersister = UserDbPersister.Default.getInstance();
			
			userPersister.persist(user);
			
		} catch (PersistenceException | ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public User LoadByUsername(String username){
		User user = new User();
	try {
		 user =  _userLoader.loadByUserName(username);
	} catch (PersistenceException e) {
		e.printStackTrace();
	}
		return user;
		
	}
	
	public void syncCamsWithBB(){
		//get cams courses
		//get bb courses
		//loop through the blackboard courses to find a matching camsCourse
		//once a course is found, check the users and compare them 
	List<CamsCourse> camsCourses = camsDao.getEnrUserToCoursesWS();
		
		
	}

	public String getSemester(String semester){
		String sem = "";
		switch (semester){
		
        case "fall":  sem = "fa";
                 break;
        case "summer1":  sem = "ss";
                 break;
        case "spring":  sem = "sp";
                 break;
        }
		return sem;
	}
	

}
