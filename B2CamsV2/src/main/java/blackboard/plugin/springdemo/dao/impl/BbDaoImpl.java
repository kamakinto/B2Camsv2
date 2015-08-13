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
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.course.CourseMembershipDbPersister;
import blackboard.persist.user.UserDbLoader;
import blackboard.plugin.springdemo.dao.BbDao;
import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class BbDaoImpl implements BbDao{
	
@Autowired
	private CourseMembershipDbLoader _membershipLoader;

//@Autowired
//private CourseMembershipDbPersister _membershipPersister;

  @Autowired
  private UserDbLoader _userLoader;
  
  @Autowired
  private CourseDbLoader _courseLoader;

	@Override
	public List<Foo> getFoos() {
		// TODO Auto-generated method stub
		return null;
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
	public void enrollUser(String username, String role, String courseId) {
		try{
		CourseMembership newEnrollment = new CourseMembership();
		newEnrollment.setUserId(_userLoader.loadByUserName(username).getId());
		newEnrollment.setRole(CourseMembership.Role.STUDENT); //TODO:put conditional to cater for instructor
		newEnrollment.setCourseId(_courseLoader.loadByCourseId(courseId).getId());
	
	//	_membershipPersister.persist(newEnrollment);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} 
		
	}
	

}
