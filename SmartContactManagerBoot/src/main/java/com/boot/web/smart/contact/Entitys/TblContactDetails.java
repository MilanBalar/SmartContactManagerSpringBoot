package com.boot.web.smart.contact.Entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_contactDetails",schema = "appcontact")
public class TblContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
	private String Name;
	private String SecondName;
	private String Work;
	private String email;
	private String phone;
	private String image;
	@Column(length = 400)
	private String description;

	@ManyToOne
	@JoinColumn(name = "userId")
	private TblUserLogin tblUserLogin;

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSecondName() {
		return SecondName;
	}

	public void setSecondName(String secondName) {
		SecondName = secondName;
	}

	public String getWork() {
		return Work;
	}

	public void setWork(String work) {
		Work = work;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TblUserLogin getTblUserLogin() {
		return tblUserLogin;
	}

	public void setTblUserLogin(TblUserLogin tblUserLogin) {
		this.tblUserLogin = tblUserLogin;
	}


}
