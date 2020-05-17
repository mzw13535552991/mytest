package com.bean;

import java.io.Serializable;
import java.util.*;

public class Pinlun implements Serializable {
	private int id;
	
	private String stid;
	private String kcid;
	private String content;
	private String savetime;
	private String hfcontent;
	private String flag;
	
	private Member member;
	private Kechen kechen;
	private Movie movie;
	
	
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public String getKcid() {
		return kcid;
	}
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSavetime() {
		return savetime;
	}
	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}
	public String getHfcontent() {
		return hfcontent;
	}
	public void setHfcontent(String hfcontent) {
		this.hfcontent = hfcontent;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Kechen getKechen() {
		return kechen;
	}
	public void setKechen(Kechen kechen) {
		this.kechen = kechen;
	}
	
	
	
	
	
}
