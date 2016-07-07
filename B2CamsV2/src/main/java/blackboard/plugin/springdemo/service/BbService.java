package blackboard.plugin.springdemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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
	
	public List<Course> getSemesterBbCourses(List<Course> allBbCourses){ 
		
		return bbDao.getCoursesInSemester(allBbCourses);
	}
	
	public List<CourseMembership> getCourseMembers(Id courseId){
		return bbDao.getCourseMembers(courseId);
	}
	
	public User getUserById(Id id){
		return bbDao.getUserById(id);
	}
	
	public void createUser(String username, String role, String firstName, String lastName){
		 bbDao.createUser(username, role, firstName, lastName);
	}
	
	public void enrollUser(String username, String role, String courseId){
		bbDao.enrollUser(username, role, courseId);
	}
	
	
	
	public HashMap<Course, ArrayList<User>> getBbCourseEnrollments(){
		HashMap<Course, ArrayList<User>> courseEnrollmentMap = new HashMap<Course, ArrayList<User>>();
		List<Course> courses = getAllBbCourses();
		List<Course> semCourses = getSemesterBbCourses(courses);//call function to convert list of courses into current ones for this semester
		 
			for(Course course : semCourses){
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
	
	public List<EnrUserToCourse> generateDropList(HashMap<Course, ArrayList<User>> bbCourseEnrollments,
		List<CamsCourse> camsCourseEnrollments){
		

		List<EnrUserToCourse> syncList = new CopyOnWriteArrayList<EnrUserToCourse>();
		
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
			
				if(coursesMatch(entry, camsCourse)){
					
					for(Map.Entry<String, CamsStudent> camsStudent: camsCourse.getCourseEnrollment().entrySet()){
						//pick a Blackboard student, then iterate over every student in Cams, if that student doesnt exist in the CAMS
						//course list. add to master 
						//list to return. 
					
							for(User bbUser: entry.getValue()){//TODO: FIX TO CHECK FOR USER EXISTANCE IN BB COURSE,
								if(!bbUser.getUserName().toUpperCase().contains(camsStudent.getKey().toUpperCase())){
									EnrUserToCourse userToAdd = new EnrUserToCourse();
									//populate user data with info for blackboard
									userToAdd.setCourseName(camsCourse.getCourseName());
									userToAdd.setCourse(camsCourse.getCourseNum());
									userToAdd.setCourseDescription(camsCourse.getCourseDescription());
									userToAdd.setCourse(camsCourse.getCourseName());
									userToAdd.setCourseType(camsCourse.getCourseType());
									userToAdd.setCourseURL(camsCourse.getCourseURL());
									userToAdd.setDepartment(camsCourse.getDepartment());
									userToAdd.setFacultyID(camsCourse.getFacultyID());
									userToAdd.setGrouping(camsCourse.getGrouping());
									userToAdd.setInstructor(camsCourse.getInstructor());
									userToAdd.setSection(camsCourse.getSection());
									userToAdd.setStudentID(camsStudent.getValue().getStudentId());
									userToAdd.setStudentName(camsStudent.getValue().getStudentName());
									userToAdd.setTermCalendarID(camsCourse.getTermCalendarID());
									userToAdd.setBbcourse(entry.getKey());
									
									//add the cams user to the master list of students to enroll
								
									if(syncList.isEmpty() || syncList == null){// makes sure list isnt empty
										syncList.add(userToAdd);
									}
									if(!existsInMasterList(userToAdd, syncList)){ // makes sure user isnt already in list
										syncList.add(userToAdd);
									}
									
								}
							}
					}
					
				}
			}
			
			
		}
		return syncList;
	}
	
public List<EnrUserToCourse> generateDiffCourseEnrollments(HashMap<Course, ArrayList<User>> bbCourseEnrollments,
		List<CamsCourse> camsCourseEnrollments){
	List<EnrUserToCourse> syncList = new CopyOnWriteArrayList<EnrUserToCourse>();
	
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
		
			if(coursesMatch(entry, camsCourse)){
				
				for(Map.Entry<String, CamsStudent> camsStudent: camsCourse.getCourseEnrollment().entrySet()){
					//pick a cams student, then iterate over every student in bb, if that student doesnt exist in the blackboard 
					//course list. add to master 
					//list to return. 
					/**
					 * TODO: CHECK FOR EXISTANCE OF A USER WITHIN THE BLACKBOARD COURSE. CURRENTLY 
					 * IF A USER DOES NOT ALREADY EXIST IN A COURSE, THEN THAT COURSE GETS SKIPPED FOR ENROLLMENT
					 * DESIRED FUNCTIONALITY IS IF A USER DOES NOT EXIST IN THE COURSE, ADD THE FIRST USER RECORD 
					 * TO THE COURSE, THEN CONTINUE LOOPING THROUGH. 
					 * SN: MAY BE BETTER TO LEAVE THIS HOW IT IS SO THAT A COURSE CANT BE ENROLLED WITH STUDENTS UNTIL WE 
					 * ENROLL THE PROFESSOR FIRST.
					 */
						for(User bbUser: entry.getValue()){//TODO: FIX TO CHECK FOR USER EXISTANCE IN BB COURSE,
							if(!bbUser.getUserName().toUpperCase().contains(camsStudent.getKey().toUpperCase())){
								EnrUserToCourse userToAdd = new EnrUserToCourse();
								//populate user data with info for blackboard
								userToAdd.setCourseName(camsCourse.getCourseName());
								userToAdd.setCourse(camsCourse.getCourseNum());
								userToAdd.setCourseDescription(camsCourse.getCourseDescription());
								userToAdd.setCourse(camsCourse.getCourseName());
								userToAdd.setCourseType(camsCourse.getCourseType());
								userToAdd.setCourseURL(camsCourse.getCourseURL());
								userToAdd.setDepartment(camsCourse.getDepartment());
								userToAdd.setFacultyID(camsCourse.getFacultyID());
								userToAdd.setGrouping(camsCourse.getGrouping());
								userToAdd.setInstructor(camsCourse.getInstructor());
								userToAdd.setSection(camsCourse.getSection());
								userToAdd.setStudentID(camsStudent.getValue().getStudentId());
								userToAdd.setStudentName(camsStudent.getValue().getStudentName());
								userToAdd.setTermCalendarID(camsCourse.getTermCalendarID());
								userToAdd.setBbcourse(entry.getKey());
								
								//add the cams user to the master list of students to enroll
							
								if(syncList.isEmpty() || syncList == null){// makes sure list isnt empty
									syncList.add(userToAdd);
								}
								if(!existsInMasterList(userToAdd, syncList)){ // makes sure user isnt already in list
									syncList.add(userToAdd);
								}
								
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
		if (bbDao.LoadByUsername("a"+ record.getStudentID()) != null){
			bbDao.enrollUser("a"+ record.getStudentID(),"Student" , record.getBbcourse().getCourseId());
		}
	}
}

public void removeUsersFromCourses(List<EnrUserToCourse> courseUserMap){
	for (EnrUserToCourse record : courseUserMap){
		if (bbDao.LoadByUsername("a"+ record.getStudentID()) != null){
			bbDao.removeUser("a"+ record.getStudentID(),"Student" , record.getBbcourse().getCourseId());
		}
	}
}

public Boolean existsInMasterList(EnrUserToCourse userToEnroll,List<EnrUserToCourse> List ){
	for(EnrUserToCourse user : List){
		if(user.getCourse().equalsIgnoreCase(userToEnroll.getCourse())
	
		&& user.getCourseName().equalsIgnoreCase(userToEnroll.getCourseName())
		&& user.getCourseType().equalsIgnoreCase(userToEnroll.getCourseType())
		&& user.getDepartment().equalsIgnoreCase(userToEnroll.getDepartment())
		&& user.getSection().equalsIgnoreCase(userToEnroll.getSection())
		&& user.getStudentID().equalsIgnoreCase(userToEnroll.getStudentID())
		&& user.getStudentName().equalsIgnoreCase(userToEnroll.getStudentName())
		&& user.getTermCalendarID().equalsIgnoreCase(userToEnroll.getTermCalendarID())){
			return true;
		}
	}
	return false;
}

public Boolean sectionMatch(Map.Entry<Course, ArrayList<User>> entry, CamsCourse camsCourse ){
	String bbCourseId = entry.getKey().getCourseId();
	String camsCourseNum = camsCourse.getCourseNum();
	String camsSection = camsCourse.getSection();
	int camsCourseNumLength = camsCourse.getCourseNum().length();
	
	//Return true : if Cams has no section and Blackboard has no section. cams has a section, and blackboard has a section
	if((camsSection.startsWith("No Section") && ((bbCourseId.contains(camsCourseNum + "fa") 
				|| bbCourseId.contains(camsCourseNum + "ss")
				|| bbCourseId.contains(camsCourseNum + "sp")))) 
	//If cams has a section, and Blackboards section portion begins with what is in cams
		||	(!camsSection.startsWith("No Section") 
				 && (bbCourseId.substring(bbCourseId.indexOf(camsCourseNum)+camsCourseNumLength).contains(camsSection))
			)){
		return true;
	}else{
		return false;
	}
}

/**
 * Checks to see if the Blackboard Course name has the Cams record's Department code in the name.
 * 
 * @param entry
 * @param camsCourse
 * @return true: if the Blackboard Course has the Cams Department code in the course id
 * 
 */
public Boolean departmentMatch(Map.Entry<Course, ArrayList<User>> entry, CamsCourse camsCourse ){
	if(entry.getKey().getCourseId().contains("-"+camsCourse.getDepartment())
			//|| entry.getKey().getCourseId().contains("-"+camsCourse.getDepartment()+"-") only happens in rare circumstances
			|| entry.getKey().getCourseId().startsWith(camsCourse.getDepartment())
			){
		return true;
	}
	return false;
}


/**
 * Matches the number of the cams record with the course id of the blackboard course
 * @param entry: Blackboard course record
 * @param camsCourse: Cams course record
 * @return
 */
public Boolean courseNumMatch(Map.Entry<Course, ArrayList<User>> entry, CamsCourse camsCourse ){
	if(entry.getKey().getCourseId().toUpperCase().contains(camsCourse.getCourseNum().toUpperCase())){
		return true;
	}
	return false;
}

public Boolean coursesMatch(Map.Entry<Course, ArrayList<User>> entry, CamsCourse camsCourse){
	if(courseNumMatch(entry,camsCourse)&& sectionMatch(entry, camsCourse)&& departmentMatch(entry, camsCourse)){
		return true;
	}
	return false;
	}



}
