package blackboard.plugin.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class CamsService {
	
	@Autowired
	private CamsDao camsDao;
	
	
	public List<Foo> getFoos(){
		return camsDao.getFoos();
	}
	
	
	public List<CamsCourse> getWSHelloWorld(){
		return camsDao.getEnrUserToCoursesWS();
	}
	
	public List<CamsCourse> getEnrUserToCourses(){
		return camsDao.getEnrUserToCoursesWS();
	}
}
