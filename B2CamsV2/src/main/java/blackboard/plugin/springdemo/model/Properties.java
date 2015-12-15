package blackboard.plugin.springdemo.model;

import blackboard.data.AbstractIdentifiable;
import blackboard.persist.DataType;
import blackboard.persist.impl.mapping.annotation.Table;
import blackboard.persist.impl.mapping.annotation.PrimaryKey;
import blackboard.persist.impl.mapping.annotation.Column;

@Table("bb_cams_props")
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
		return null;
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
		
		
	
	

}
