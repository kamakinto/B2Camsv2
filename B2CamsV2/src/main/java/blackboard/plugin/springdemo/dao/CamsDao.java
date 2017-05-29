package blackboard.plugin.springdemo.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;

public interface CamsDao {
	
	List<CamsCourse> getEnrUserToCoursesWS();
	
	

}
