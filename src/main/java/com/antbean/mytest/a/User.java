package com.antbean.mytest.a;

public class User {
	@FieldDesc("用户ID")
	private Integer userId;
	@FieldDesc("用户名")
	private String username;
	@FieldDesc("密码")
	private String password;
	@FieldDesc("姓名")
	private String realname;
	@FieldDesc("年龄")
	private Integer age;
	@FieldDesc("性别")
	private Character gender;
	@FieldDesc("地址")
	private String address;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User() {
		super();
	}
	public User(Integer userId, String username, String password, String realname, Integer age, Character gender,
			String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", age=" + age + ", gender=" + gender + ", address=" + address + "]";
	}
}
