package com.antbean.mytest.a;
public class MyUser {
		@FieldDesc("用户ID")
		private Integer userId;
		@FieldDesc("用户名")
		private String username;
		@FieldDesc("密码")
		private String password;
		@FieldDesc("学校")
		private String school;

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
		public String getSchool() {
			return school;
		}
		public void setSchool(String school) {
			this.school = school;
		}
		@Override
		public String toString() {
			return "MyUser [userId=" + userId + ", username=" + username + ", password=" + password + ", school="
					+ school + "]";
		}
	}