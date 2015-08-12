package blackboard.plugin.springdemo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.plugin.springdemo.dao.BbDao;

@Component
public class BbService {
	
	@Autowired
	private BbDao bbDao;
	
	
	public List<Course> getAllBbCourses(){
		return bbDao.getAllBbCourses();
	}
	
	public List<CourseMembership> getCourseMembers(Id courseId){
		return bbDao.getCourseMembers(courseId);
	}
	
	public User getUserById(Id id){
		return bbDao.getUserById(id);
	}
	
	public HashMap<Course, ArrayList<User>> getBbCourseEnrollments(){
		HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
		List<Course> courses = getAllBbCourses();
			for(Course course : courses){
				courseEnrollmentMap.put(course, new ArrayList<User>());
				List<CourseMembership> members = bbDao.getCourseMembers(course.getId());
					for(CourseMembership member: members){
						courseEnrollmentMap.get(course).add(bbDao.getUserById(member.getUserId()));
					}
			}
		return courseEnrollmentMap;
	}
	
	public HashMap<String, ArrayList<String>> getCourseEnrollmentIDs(HashMap<Course, ArrayList<User>> objectEnrollments){
		HashMap<String, ArrayList<String>> courseEnrollmentIds = new HashMap<String, ArrayList<String>>();
		for(Course course: objectEnrollments.keySet()){
			courseEnrollmentIds.put(course.getCourseId(), new ArrayList<String>());
				for( User users: objectEnrollments.get(course)){
					courseEnrollmentIds.get(course.getCourseId()).add(users.getEmailAddress());
				}
		}
		return courseEnrollmentIds;
}
	

}
