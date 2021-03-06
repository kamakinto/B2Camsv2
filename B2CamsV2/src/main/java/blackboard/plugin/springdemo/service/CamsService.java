package blackboard.plugin.springdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.CamsCourse;
import blackboard.plugin.springdemo.model.Foo;
import blackboard.plugin.springdemo.model.Usersync;

@Component
public class CamsService {
	
	@Autowired
	private CamsDao camsDao;
	
		
	public List<CamsCourse> getEnrUserToCourses(){
		return camsDao.getEnrUserToCoursesWS();
	}
	
	public List<Usersync> getNewBBUsers(){
		return camsDao.getUsersWS();
	}
}
