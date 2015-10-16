package blackboard.plugin.springdemo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import blackboard.plugin.springdemo.dao.BbDao;
import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.EnrUserToCourse;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class CamsService {
	
	@Autowired
	private CamsDao camsDao;
	
	
	public List<Foo> getFoos(){
		return camsDao.getFoos();
	}
	
	
	public String getWSHelloWorld(){
		return camsDao.getEnrUserToCoursesWSTest();
	}
}
