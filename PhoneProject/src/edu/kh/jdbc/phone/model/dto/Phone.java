package edu.kh.jdbc.phone.model.dto;

import java.sql.Date;

public class Phone {

	private String phoneNo;
	private String name;
	private Date insertDate;
	private char favorit;
	private char deleteFl;
	
	public Phone() {}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public char getFavorit() {
		return favorit;
	}

	public void setFavorit(char favorit) {
		this.favorit = favorit;
	}

	public char getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(char deleteFl) {
		this.deleteFl = deleteFl;
	}
	
	
}
