package com.bean;

import java.io.Serializable;
import java.util.*;

public class Member implements Serializable {
	private int id;
	private String uname;
	private String upass;
	private String tname;
	private String tel;
	private String email;
	private String filename;
	private String delstatus;
	private String isjy;
	
	
	
	public String getIsjy() {
		return isjy;
	}
	public void setIsjy(String isjy) {
		this.isjy = isjy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDelstatus() {
		return delstatus;
	}
	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}
	
	
	

	
}
