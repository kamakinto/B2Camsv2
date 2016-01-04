package blackboard.plugin.springdemo.model;
import java.util.HashMap;


public class CamsCourse {
 private String department; 
 private String courseNum;
 private String section;
 private String courseName;
 private String instructor;
 private String termCalendarID;
 private String facultyID;
 private String grouping;
 private String courseURL;
 private String courseType;
 private String courseDescription;
 private String crossListedID;
 private HashMap<String, CamsStudent> courseEnrollment = new HashMap<String, CamsStudent>();

public CamsCourse(CamsStudentRecord sr) {
	this.setCourseDescription(sr.getCourseDescription());
	this.setCourseName(sr.getCourseName());
	this.setCourseNum(sr.getCourse());
	this.setCourseType(sr.getCourseType());
	this.setCourseURL(sr.getCourseURL());
	this.setCrossListedID(sr.getCrossListedID());
	this.setDepartment(sr.getDepartment());
	this.setFacultyID(sr.getFacultyID());
	this.setGrouping(sr.getGrouping());
	this.setInstructor(sr.getInstructor());
	this.setSection(sr.getSection());
	this.setTermCalendarID(sr.getTermCalendarID());
	this.courseEnrollment = new HashMap<String, CamsStudent>();
	
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	if(department != null && !department.isEmpty()){
		this.department = department;
	}else{
		this.department = "No Department Available";
	}
	
}
public String getCourseNum() {
	return courseNum;
}
public void setCourseNum(String courseNum) {
if(courseNum != null && !courseNum.isEmpty()){
	this.courseNum = courseNum;
}else{
	this.courseNum = "No Course Number Available";
}
	
}
public String getSection() {
	return section;
}
public void setSection(String section) {
if(section != null && !section.isEmpty()){
	this.section = section;
}else{
	this.section = "No Section Available";
}
	
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	if (courseName != null && !courseName.isEmpty()){
		this.courseName = courseName;
	}else{
		this.courseName = "No Course Name Available";
	}
	
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	if(instructor != null && !instructor.isEmpty()){
		this.instructor = instructor;
	}else{
		this.instructor = "No Instructor Available";
	}
	
}
public String getTermCalendarID() {
	return termCalendarID;
}
public void setTermCalendarID(String termCalendarID) {
	if(termCalendarID != null && !termCalendarID.isEmpty()){
		this.termCalendarID = termCalendarID;
	}else{
		this.termCalendarID = "No Term Calendar ID Available";
	}
	
}
public String getFacultyID() {
	return facultyID;
}
public void setFacultyID(String facultyID) {
	if(facultyID != null && !facultyID.isEmpty()){
		this.facultyID = facultyID;
	}else{
		this.facultyID = "No Faculty ID Available";
	}
	
}
public String getGrouping() {
	return grouping;
}
public void setGrouping(String grouping) {
	if(grouping != null && !grouping.isEmpty()){
		this.grouping = grouping;
	}else{
		this.grouping = "No Grouping Available";
	}
	
}
public String getCourseURL() {
	return courseURL;
}
public void setCourseURL(String courseURL) {
	if(courseURL != null && !courseURL.isEmpty()){
		this.courseURL = courseURL;	
	}else{
		this.courseURL = "No Course URL Available";
	}
	
}
public String getCourseType() {
	return courseType;
}
public void setCourseType(String courseType) {
	if(courseType != null && !courseType.isEmpty()){
		this.courseType = courseType;
	}else{
		this.courseType = "No Course Type Available";
	}
	
}
public String getCourseDescription() {
	return courseDescription;
}
public void setCourseDescription(String courseDescription) {
	if(courseDescription != null && !courseDescription.isEmpty()){
		this.courseDescription = courseDescription;
	}else{
		this.courseDescription = "No Course Description AVailable";
	}
	
}
public HashMap<String, CamsStudent> getCourseEnrollment() {
	return courseEnrollment;
}
public void setCourseEnrollment(HashMap<String, CamsStudent> courseEnrollment) {
	this.courseEnrollment = courseEnrollment;
}
public String getCrossListedID() {
	return crossListedID;
}
public void setCrossListedID(String crossListedID) {
	if(crossListedID != null && crossListedID.isEmpty()){
		this.crossListedID = crossListedID;
	}else{
		this.crossListedID = "No Cross Listed ID Available";
	}
	
}

}
