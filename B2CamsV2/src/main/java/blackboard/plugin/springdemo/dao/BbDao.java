package blackboard.plugin.springdemo.dao;

import java.util.List;
import java.util.Map;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.plugin.springdemo.model.Foo;

public interface BbDao {
	
	public List<Foo> getFoos();
	/**
	 * Gets all of the courses stored within blackboard
	 * @return
	 */
	public List<Course> getAllBbCourses();
	
	/**
	 * gets the Users enrolled in the courses
	 * @param courseId
	 * @return
	 */
	public List<CourseMembership> getCourseMembers(Id courseId);
	/**
	 * get the User by their Id
	 * @param id
	 * @return
	 */
	public User getUserById(Id id);
	
	public void enrollUser(String username, String role, String courseId);
	

	

}
