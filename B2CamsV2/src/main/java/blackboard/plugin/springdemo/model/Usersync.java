package blackboard.plugin.springdemo.model;

public class Usersync {
	private String netid;
	private String role;
	private String firstname;
	private String lastname;
	private String password;
	
	public Usersync(){
		
	}
	public Usersync(String netid, String role, String firstname, String lastname, String password){
		this.netid = netid;
		this.role = role;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}


	public String getnetid() {
		return netid;
	}


	public void setnetid(String netid) {
		this.netid = netid;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getfirstname() {
		return firstname;
	}


	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getlastname() {
		return lastname;
	}


	public void setlastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
