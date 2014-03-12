package net.realqinwei.hzcrm.crm.been;

import java.sql.Timestamp;

public final class BigUser implements java.io.Serializable, Comparable<BigUser> {

	private Integer id;
	private String userPassword;
	private String userIDCard;
	private String userName;
	private String userPhone;
	private String userAddress;
	private Integer userType;
	private String userLoginID;
	private Timestamp userCreateTime;
	private Integer userReferID;

	public BigUser() {

	}

	/** minimal constructor */
	public BigUser(String userPassword, String userIDCard, String userName,
                   Integer userType, Timestamp userCreateTime) {

		this.userPassword = userPassword;
		this.userIDCard = userIDCard;
		this.userName = userName;
		this.userType = userType;
		this.userCreateTime = userCreateTime;
	}

	/** full constructor */
	public BigUser(String userPassword, String userIDCard, String userName,
                   String userPhone, String userAddress, Integer userType,
                   Timestamp userCreateTime, Integer userReferID, String userLoginID) {
		
		this(userPassword, userIDCard, userName, userType, userCreateTime);
		
		this.userName = userName;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
		this.userReferID = userReferID;
		this.userLoginID = userLoginID;
	}
	
	@Override
	public String toString() {
		return this.userName;
	}
	
	private String objectID() {
		return this.userIDCard + this.userCreateTime.toString();
	}

	@Override
	public boolean equals(Object anotherObject) {
		if (null == anotherObject) {
			return false;
		} else if (this == anotherObject) {
			return true;
		} else {
			BigUser anotherUser = (BigUser) anotherObject;
			return anotherUser.objectID().equals(this.objectID()) ? true : false;
		}
	}

	@Override
	public int hashCode() {
		return this.objectID().hashCode();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserIDCard() {
		return userIDCard;
	}

	public void setUserIDCard(String userIDCard) {
		this.userIDCard = userIDCard;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserLoginID() {
		return this.userLoginID;
	}

	public void setUserLoginID(String userLoginID) {
		this.userLoginID = userLoginID;
	}

	public Timestamp getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserCreateTime(Timestamp userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public Integer getUserReferID() {
		return userReferID;
	}

	public void setUserReferID(Integer userReferID) {
		this.userReferID = userReferID;
	}

	@Override
	public int compareTo(BigUser other) {
		return this.userCreateTime.compareTo(other.getUserCreateTime());
	}
}
