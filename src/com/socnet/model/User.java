package com.socnet.model;

import java.util.Date;

public class User {
	private String username;
	private String password;
	private Date birthday;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String avatar;
	private String cover;

	public User(String username, String password, Date birthday, String gender, String email, String phone,
			String address, String avatar, String cover) {
		super();
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.avatar = avatar;
		this.cover = cover;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", birthday=" + birthday + ", gender=" + gender
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", avatar=" + avatar + ", cover="
				+ cover + "]" + "\n";
	}

}
