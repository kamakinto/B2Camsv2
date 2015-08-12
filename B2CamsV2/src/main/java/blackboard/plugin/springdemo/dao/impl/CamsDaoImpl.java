package blackboard.plugin.springdemo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackboard.plugin.springdemo.dao.CamsDao;
import blackboard.plugin.springdemo.model.Foo;

@Component
public class CamsDaoImpl implements CamsDao{
	
	@PersistenceContext(unitName="CamsHibernatePersistenceUnit")
	private EntityManager entityManagerCams;

	@Override
	@Transactional
	public List<Foo> getFoos() {
		String qlString = "SELECT p FROM Foo p";
		TypedQuery<Foo> query = entityManagerCams.createQuery(qlString, Foo.class);
		return query.getResultList();
	}
	
	public Map<String, Object> getConnectionInfo(){
	Map<String,Object> connectionInfo = entityManagerCams.getProperties();
		return connectionInfo;
	}

	

}
