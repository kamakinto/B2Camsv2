package blackboard.plugin.springdemo.model;

import blackboard.data.AbstractIdentifiable;
import blackboard.persist.DataType;
import blackboard.persist.impl.mapping.annotation.Table;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Column;

@Table("bb_cams_props_a")
public class Properties extends AbstractIdentifiable {
		
		public static final DataType DATA_TYPE = new DataType(Properties.class);
		
		public DataType getDataType(){
			return DATA_TYPE;
		}
		
		@PrimaryKey
		private int pk1;
		
		@Column(value = "cams_enabled_ind")
		private String enabled;
		
		@Column(value = "cams_frequency")
		private int frequency;
		
		@Column(value = "cams_user_id")
		private int userId;
		
		@Column(value = "cams_username")
		private String username;
		
		@Column(value = "cams_password")
		private String password;
		
		@Column(value = "cams_web_address")
		private String webAddress;
		
		@Column(value = "cams_term")
		private String term;
		
		@Column(value = "cams_year")
		private String year;
		
		@Column(value = "cams_setting_a")
		private String setting_a;
		
		@Column(value = "cams_setting_b")
		private String setting_b;
		
		@Column(value = "cams_setting_c")
		private String setting_c;
		
		@Column(value = "cams_setting_d")
		private String setting_d;
		
		
		
		
/**
 * @return the cams_id
 */
		public int getPropertyId(){
			return pk1;
		}
		
/**
 * @return enabled
 */
		public Boolean isEnabled(){
			if(this.enabled.equals("Y")){
				return true;
			}else{
				return false;
			}
		}
		
	/**
	 * @param flag : If true, set enabled to 'Y', else 'N'	
	 */
		public void enable(Boolean flag){
				if(flag){
					this.enabled = "Y";
				}else{
					this.enabled = "N";
				}
			
		}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	public String frequencyToString(int freqency){
		String frequencyString ="";
		
		switch (frequency){
		case 0:
			frequencyString = "Hourly";
			break;
		case 1: 
			frequencyString = "Twice Per Day";
			break;
		case 2:
			frequencyString = "Daily";
			break;
		
		}
		
		return frequencyString;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the webAddress
	 */
	public String getWebAddress() {
		return webAddress;
	}

	/**
	 * @param webAddress the webAddress to set
	 */
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the setting_a
	 */
	public String getSetting_a() {
		return setting_a;
	}

	/**
	 * @param setting_a the setting_a to set
	 */
	public void setSetting_a(String setting_a) {
		this.setting_a = setting_a;
	}

	/**
	 * @return the setting_b
	 */
	public String getSetting_b() {
		return setting_b;
	}

	/**
	 * @param setting_b the setting_b to set
	 */
	public void setSetting_b(String setting_b) {
		this.setting_b = setting_b;
	}

	/**
	 * @return the setting_c
	 */
	public String getSetting_c() {
		return setting_c;
	}

	/**
	 * @param setting_c the setting_c to set
	 */
	public void setSetting_c(String setting_c) {
		this.setting_c = setting_c;
	}

	/**
	 * @return the setting_d
	 */
	public String getSetting_d() {
		return setting_d;
	}

	/**
	 * @param setting_d the setting_d to set
	 */
	public void setSetting_d(String setting_d) {
		this.setting_d = setting_d;
	}
		
		
	
	

}
