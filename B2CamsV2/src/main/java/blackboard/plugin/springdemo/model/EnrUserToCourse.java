package blackboard.plugin.springdemo.model;

import blackboard.data.course.Course;

public class EnrUserToCourse {
	
	private String studentName;
	private String studentID;
	private String department;
	private String course;
	private String section;
	private String courseName;
	private String instructor;
	private String termCalendarID;
	private String facultyID;
	private String grouping;
	private String courseURL;
	private String courseType;
	private String courseDescription;
	private Course bbcourse;

	public EnrUserToCourse() {
		// TODO Auto-generated constructor stub
	}
	public EnrUserToCourse(CamsCourse camsCourse, String studentId, String studentName, Course bbcourse ){
		
		
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
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
	public Course getBbcourse() {
		return bbcourse;
	}
	public void setBbcourse(Course bbcourse) {
		this.bbcourse = bbcourse;
	}
	

}
