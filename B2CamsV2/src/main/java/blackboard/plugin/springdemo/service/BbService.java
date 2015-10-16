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
import blackboard.plugin.springdemo.model.EnrUserToCourse;

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
	
	public void enrollUser(String username, String role, String courseId){
		bbDao.enrollUser(username, role, courseId);
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
	
public List<EnrUserToCourse> generateDiffCourseEnrollments(HashMap<Course, ArrayList<User>> bbCourseEnrollments,
		HashMap<Course, ArrayList<User>> camsCourseEnrollments){
	return null;
}


public void enrollUsersToCourses(List<EnrUserToCourse> courseUserMap){
	for (EnrUserToCourse record : courseUserMap){
//		if (bbDao.LoadByUsername(record.getUsername()) == null){
//		//TODO: If user doesnt exist in the system, create the user	
//		}
		//bbDao.enrollUser(record.getUsername(), record.getRole(), record.getCourse());
	}
}
	

}
