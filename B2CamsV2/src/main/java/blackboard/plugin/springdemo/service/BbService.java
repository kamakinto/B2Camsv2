package blackboard.plugin.springdemo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.plugin.springdemo.dao.BbDao;
import blackboard.plugin.springdemo.model.CamsStudent;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.CamsCourse;

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
		List<CamsCourse> camsCourseEnrollments){
	List<EnrUserToCourse> syncList = new ArrayList<EnrUserToCourse>();
	
	/**
	 * 1. Iterate through the bb courses
	 * 2. find the corresponding course in cams
	 * 3. get the list of users in cams that are NOT in blackboard for the course. (go through cams list and
	 * if the user does not show up the blackboard list, add that username and info to the diff list. if user is in both, do nothing
	 * we only want a list of users that are in cams, but not in bb. with that list. we have the updated "added" users. 
	 */
	
	//1 - loops through each entry in BB entries. 
	for(Map.Entry<Course, ArrayList<User>> entry: bbCourseEnrollments.entrySet()){
		
		for(CamsCourse camsCourse : camsCourseEnrollments){
			
			if(entry.getKey().getCourseId().contentEquals(camsCourse.getCourseNum())
					&& (entry.getKey().getCourseId().startsWith(camsCourse.getDepartment())
							|| entry.getKey().getCourseId().startsWith(camsCourse.getCrossListedID()))){
				//if we find a match, iterate over camsCourse's students. if you dont find the student id in 
				//the 'entry's array list of users, then add it to the diff list we are building. 
				for(Map.Entry<String, CamsStudent> camsStudent: camsCourse.getCourseEnrollment().entrySet()){
					//iterate every student, if that student doesnt exist in the blackboard course list. add to master 
					//list to return. 
						for(User bbUser: entry.getValue()){
							if(!bbUser.getUserName().contains(camsStudent.getKey())){
								EnrUserToCourse userToAdd = new EnrUserToCourse();
								//populate user data with info for blackboard
								
								
								//add the cams user to the master list of students to enroll
								syncList.add(userToAdd);
							}
						}
				}
				
			}
		}
		
		
	}
	return syncList;
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
