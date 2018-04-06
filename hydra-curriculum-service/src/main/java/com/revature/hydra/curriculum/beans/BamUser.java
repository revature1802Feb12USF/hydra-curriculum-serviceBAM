package com.revature.hydra.curriculum.beans;

/**
 * Non-persistent bean for BamUser object
 */
public class BamUser {

	private int userId;

	private String fName;

	private String mName;

	private String lName;

	private String email;

	private String pwd;
	
	// Role 1 is for associates // Role 2 is for trainers & QC
	private Role role; // Role 3 is for admins

	/*
	 *  Batch ID should only be used for associates. DO NOT use this
	 *	field to assign a batch to a trainer. It should be null for
	 *	trainers and admins. A trainer is assigned in the Batches table.
	 */
	private Integer batch; 
	private String phone;

	private String phone2;

	private String skype;

	// This is a backup password that will be used when
	private String pwd2; // the user needs to reset their password.

	private Integer assignForceID;
	
	public BamUser() {
		super();
	}

	public BamUser(String fName, String mName, String lName, String email, String pwd, Role role, Integer batch,
			String phone, String phone2, String skype, String pwd2) {//NOSONAR
		super();
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.email = email;
		this.pwd = pwd;
		this.role = role;
		this.batch = batch;
		this.phone = phone;
		this.phone2 = phone2;
		this.skype = skype;
		this.pwd2 = pwd2;
	}

	public BamUser(int userId, String fName, String mName, String lName, String email, String pwd, Role role,
			Integer batch, String phone, String phone2, String skype, String pwd2) {//NOSONAR
		super();
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.email = email;
		this.userId = userId;
		this.pwd = pwd;
		this.role = role;
		this.batch = batch;
		this.phone = phone;
		this.phone2 = phone2;
		this.skype = skype;
		this.pwd2 = pwd2;
	}

	public BamUser(int userId, String fName, String mName, String lName, String email, String pwd, Role role,
			Integer batch, String phone, String phone2, String skype, String pwd2, Integer AssignForceID) {//NOSONAR
		super();
		this.userId = userId;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.email = email;
		this.pwd = pwd;
		this.role = role;
		this.batch = batch;
		this.phone = phone;
		this.phone2 = phone2;
		this.skype = skype;
		this.pwd2 = pwd2;
		assignForceID = AssignForceID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getBatch() {
		return batch;
	}

	public void setBatch(Integer batch) {
		this.batch = batch;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public Integer getAssignForceID() {
		return assignForceID;
	}

	public void setAssignForceID(Integer assignForceID) {
		this.assignForceID = assignForceID;
	}

	@Override
	public String toString() {
		return "BamUser [\n" + "(User ID) \t userId=" + userId + ",\n"
				+ "(First Namae) \t fName=" + fName + ",\n"
				+ "(Middle Name) \t mName=" + mName + ",\n"
				+ "(Last Name) \t lName=" + lName + ",\n "
				+ "(Email) \t email=" + email + ",\n"
				+ "(Password) \t pwd=" + pwd + ",\n"
				+ "(Role) \t role=" + role + ",\n"
				+ "(Batch) \t batch=" + batch + ",\n "
				+ "(Phone) \t phone=" + phone + ",\n"
				+ "(Second Phone) \t phone2=" + phone2 + ",\n"
				+ "(Skype) \t skype=" + skype + ",\n"
				+ "(Backup Password) \t pwd2=" + pwd2 + "\n]";
	}
}
