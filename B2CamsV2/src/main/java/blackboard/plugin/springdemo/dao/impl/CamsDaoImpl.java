package blackboard.plugin.springdemo.dao.impl;


import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.CamsStudent;
import blackboard.plugin.springdemo.model.CamsStudentRecord;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



@Component
public class CamsDaoImpl implements CamsDao{
	
	private final static String server_url = "";
	private final static String test_server_url = "http://www.advogato.org/XMLRPC";
	/**
     * Name of remote functions used by this example
     */
    public static final String METHOD_DRUPAL_CAMS_GETENROLLMENT = "blackboard_ws.getEnrollments";

	
	
	
	@PersistenceContext(unitName="CamsHibernatePersistenceUnit")
	private EntityManager entityManagerCams;

	@Override
	@Transactional
	public List<Foo> getFoos() {
		String qlString = "SELECT p FROM Foo p";
		TypedQuery<Foo> query = entityManagerCams.createQuery(qlString, Foo.class);
		return query.getResultList();
		
}

	
	
	
public List<EnrUserToCourse> decodeEnrollmentList(Object element){
		
		return null;
	}
	
	@Override
	public List<CamsCourse> getEnrUserToCoursesWS(){
	
		String result= null;
		List<CamsCourse> courses = new CopyOnWriteArrayList<CamsCourse>();
		String client_username = "";
		String client_password = "";
		 String server_url = "";
		 
		 
		try{
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(server_url)); //update with Drupal WS URL
			XmlRpcClient server = new XmlRpcClient();
			server.setConfig(config);
			Vector params = new Vector();
			params.add(client_username);
			params.add(client_password);
			result = (String) server.execute("blackboard_ws.getEnrollments", params);
			
			Type listType = new TypeToken<List<CamsStudentRecord>>() {}.getType();
			List<CamsStudentRecord> studentRecords = new Gson().fromJson(result, listType);
		
			
			if(courses.isEmpty()){
				CamsCourse firstCourseAndUser = createCamsCourse(studentRecords.get(0));
				courses.add(firstCourseAndUser);
			}
			
			for(CamsStudentRecord sr: studentRecords){
					if(courseExists(sr, courses)){
						CamsCourse camsC = findExistingCourse(sr, courses); //get the existing course
						if(!userExists(sr, camsC)){// check if user exists
							//add user
							CamsStudent newStudent = new CamsStudent();
							newStudent.setStudentId(sr.getStudentID());
							newStudent.setStudentName(sr.getStudentName());
							for(int i = 0; i < courses.size();i++){
								if(camsC.getCourseName().toUpperCase().contentEquals(courses.get(i).getCourseName().toUpperCase())
									&& camsC.getCourseNum().toUpperCase().contentEquals(courses.get(i).getCourseNum().toUpperCase())
									&& camsC.getSection().toUpperCase().contentEquals(courses.get(i).getSection().toUpperCase())
									&& camsC.getDepartment().toUpperCase().contentEquals(courses.get(i).getDepartment().toUpperCase())
									&& camsC.getInstructor().toUpperCase().contentEquals(courses.get(i).getInstructor().toUpperCase())){
									
									courses.get(i).getCourseEnrollment().put(newStudent.getStudentId(), newStudent);
								}
							}
						}
					}else{
						//create course and add user
						CamsCourse newCourseWithUser = createCamsCourse(sr);
						courses.add(newCourseWithUser);
					}
			}
			
			for(int i= 0; i < courses.size(); i++){
				System.out.println("Course number " + (i+1) + ": " + courses.get(i).getCourseName()
				+ "  Users: " + courses.get(i).getCourseEnrollment().keySet());
			}
		
		}catch (XmlRpcException exception){
			System.err.println("javaClient: " + exception);
			exception.printStackTrace();
		} catch (MalformedURLException e){
			System.err.println("javaClient: You put in an incorrect URL for XML-RPC server format:" + e);
			result = "javaClient: You put in an incorrect URL for XML-RPC server format:" + e;
		};
		
		if (result == null){
			result = "The result is null";
		}
	return courses;
	}
	

	

	
	public static CamsCourse findExistingCourse(CamsStudentRecord studentRecord, List<CamsCourse> courses){
		for(CamsCourse course : courses){
			if(studentRecord.getCourseName().toUpperCase().contentEquals(course.getCourseName().toUpperCase())
					&& studentRecord.getDepartment().toUpperCase().contentEquals(course.getDepartment().toUpperCase())
					&& studentRecord.getCourse().toUpperCase().contentEquals(course.getCourseNum().toUpperCase())
					&& studentRecord.getSection().toUpperCase().contentEquals(course.getSection().toUpperCase())
					&& studentRecord.getInstructor().toUpperCase().contentEquals(course.getInstructor().toUpperCase())){
				return course;
			}
			
		}
		return null;
		
	}
	
	public static Boolean courseExists(CamsStudentRecord studentRecord, List<CamsCourse> camsCourses){
		for(CamsCourse camsC: camsCourses){
			if(studentRecord.getCourseName().toUpperCase().contentEquals(camsC.getCourseName().toUpperCase())
					&& studentRecord.getDepartment().toUpperCase().contentEquals(camsC.getDepartment().toUpperCase())
					&& studentRecord.getCourse().toUpperCase().contentEquals(camsC.getCourseNum().toUpperCase())
					&& studentRecord.getSection().toUpperCase().contentEquals(camsC.getSection().toUpperCase())
					&& studentRecord.getInstructor().toUpperCase().contentEquals(camsC.getInstructor().toUpperCase())){
				return true;
				}
		}
		return false;
	}

	public static Boolean userExists(CamsStudentRecord studentRecord, CamsCourse course){
		// check if student exists in course
		if(course.getCourseEnrollment().containsKey(studentRecord.getStudentID())){
			return true;
		}
		return false;
	}
	
	public static CamsCourse createCamsCourse(CamsStudentRecord sr){
		CamsCourse newCourse = new CamsCourse();
		newCourse.setCourseDescription(sr.getCourseDescription());
		newCourse.setCourseName(sr.getCourseName());
		newCourse.setCourseNum(sr.getCourse());
		newCourse.setCourseType(sr.getCourseType());
		newCourse.setCourseURL(sr.getCourseURL());
		newCourse.setDepartment(sr.getDepartment());
		newCourse.setFacultyID(sr.getFacultyID());
		newCourse.setGrouping(sr.getGrouping());
		newCourse.setInstructor(sr.getInstructor());
		newCourse.setTermCalendarID(sr.getTermCalendarID());
		
		//Important edge cases that shouldnt be null
		if(sr.getSection().equals(null)|| sr.getSection().isEmpty()){
			newCourse.setSection("No Section Available");
		}else{
			newCourse.setSection(sr.getSection());
		}
		
		if(sr.getCrossListedID().equals(null ) || sr.getCrossListedID().isEmpty()){
			newCourse.setCrossListedID("No Cross Listed ID Available");
		}else{
			newCourse.setCrossListedID(sr.getCrossListedID());
		}
		
		//then create a new student object with sr data
		CamsStudent newStudent = new CamsStudent();
		newStudent.setStudentId(sr.getStudentID());
		newStudent.setStudentName(sr.getStudentName());
		//add student to camscourse object
		newCourse.getCourseEnrollment().put(newStudent.getStudentId().toString(), newStudent);
		return newCourse;
	}

	

}
