package com.example.imageUpDown.entity;


public class JWTRequest {
    public JWTRequest() {
		super();
	}
	
	private String userName;
    private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "JWTRequest [userName=" + userName + ", password=" + password + "]";
	}
}
