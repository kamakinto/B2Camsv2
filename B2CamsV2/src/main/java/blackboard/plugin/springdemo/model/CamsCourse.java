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

public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getCourseNum() {
	return courseNum;
}
public void setCourseNum(String courseNum) {
	this.courseNum = courseNum;
}
public String getSection() {
	return section;
}
public void setSection(String section) {
	this.section = section;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public String getTermCalendarID() {
	return termCalendarID;
}
public void setTermCalendarID(String termCalendarID) {
	this.termCalendarID = termCalendarID;
}
public String getFacultyID() {
	return facultyID;
}
public void setFacultyID(String facultyID) {
	this.facultyID = facultyID;
}
public String getGrouping() {
	return grouping;
}
public void setGrouping(String grouping) {
	this.grouping = grouping;
}
public String getCourseURL() {
	return courseURL;
}
public void setCourseURL(String courseURL) {
	this.courseURL = courseURL;
}
public String getCourseType() {
	return courseType;
}
public void setCourseType(String courseType) {
	this.courseType = courseType;
}
public String getCourseDescription() {
	return courseDescription;
}
public void setCourseDescription(String courseDescription) {
	this.courseDescription = courseDescription;
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
	this.crossListedID = crossListedID;
}

}
