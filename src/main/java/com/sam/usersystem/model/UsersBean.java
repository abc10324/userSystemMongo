package com.sam.usersystem.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class UsersBean {
	private Integer no;
	
	@Indexed(unique=true)
	private String	userID;
	private Byte[]	pwd;
	
	@Indexed(unique=true)
	private String	userName;
	
	
	@Override
	public String toString() {
		return "UsersBean [no=" + no + ", userID=" + userID + ", userName=" + userName + "]";
	}
	
	
	@Id
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Byte[] getPwd() {
		return pwd;
	}
	public void setPwd(Byte[] pwd) {
		this.pwd = pwd;
	}
	
	public void setPwd(String pwd) {
		byte[] bytePwd = pwd.getBytes();
		int length = bytePwd.length;
		
		Byte[] objBytePwd = new Byte[length];
		
		for(int i=0 ; i<length ; i++ ) {
			objBytePwd[i] = bytePwd[i];
		}
		
		this.pwd = objBytePwd;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
