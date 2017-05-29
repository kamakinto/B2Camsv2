package blackboard.plugin.springdemo.dao;
import java.util.List;
import java.util.Optional;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.dao.impl.SimpleDAO;
import blackboard.persist.impl.SimpleSelectQuery;
import blackboard.plugin.springdemo.model.Properties;
import blackboard.util.StringUtil;
import blackboard.platform.spring.jdbc.LearnDataSource;


public class CamsPropertiesDAO extends SimpleDAO<Properties>{

	
	public CamsPropertiesDAO(){
		super(Properties.class);
	}
	
	public CamsPropertiesDAO(Class<Properties> cls){
		super(cls);
	}
	
	public List<Properties> loadAll(){
		return getDAOSupport().loadAll();
	}
	
	public List<Properties> searchById(String id) throws KeyNotFoundException{
		
		if(!StringUtil.isEmpty(id)){
			SimpleSelectQuery query = new SimpleSelectQuery(getDAOSupport().getMap());
			query.addWhere("pk1", id);
			return getDAOSupport().loadList(query);
		}
		return null;
		
	}
	
	public Properties load(){
		List<Properties> globalSettings;
		globalSettings = getDAOSupport().loadAll();
		if(globalSettings != null && !globalSettings.isEmpty()){
			return globalSettings.get(0);
		}else{
			return null;
		}
	}
	
	
	public void save(Properties globalSettings){
		getDAOSupport().persist(globalSettings);
	}
	
	public boolean isInstalled(){
		List<Properties> globalSettings = null;
		globalSettings = getDAOSupport().loadAll();
		
		if(globalSettings.size() > 0 ){
			return true;
		}else{
			return false;
		}
	}
	
	public void writeDefaults(){
		Properties globalSettings = new Properties();
		getDAOSupport().persist(globalSettings);
	}
	
}
