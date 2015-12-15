package blackboard.plugin.springdemo.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.ConstructorResult;


@Entity
@Table( name = "bb_springdemo_camsSettings" )
public class CamsSettings
{

  private Long id;
  private String frequency;
  private String username;
  private String password;
  private String webAddress;
  private String syncState;



@Id
  @GeneratedValue( strategy=GenerationType.AUTO, generator="bb_springdemo_camsSetting_seq" )
  @SequenceGenerator( name="bb_springdemo_camsSetting_seq", sequenceName="bb_springdemo_camsSetting_seq" )
  @Column( name = "camsSetting_id" )
  public Long getId()
  {
    return id;
  }

  public void setId( Long id )
  {
    this.id = id;
  }

  

  @Column(name = "frequency")
  public String getFrequency() {
	return frequency;
}

public void setFrequency(String frequency) {
	this.frequency = frequency;
}

@Column(name="username")
public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}

@Column(name="password")
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Column(name="webaddress")
public String getWebAddress() {
	return webAddress;
}

public void setWebAddress(String webAddress) {
	this.webAddress = webAddress;
}

@Column(name="syncstate")
public String getSyncState() {
	return syncState;
}

public void setSyncState(String syncState) {
	this.syncState = syncState;
}
  
}
