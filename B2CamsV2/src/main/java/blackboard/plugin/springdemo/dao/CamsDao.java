package blackboard.plugin.springdemo.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;

public interface CamsDao {
	
	public List<Foo> getFoos();

	List<EnrUserToCourse> getEnrUserToCoursesWS();

	String getEnrUserToCoursesWSTest();
	

}
