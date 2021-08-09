package com.boot.web.smart.contact.Entitys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_userLogin",schema = "appuser")
public class TblUserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@NotBlank(message = "Name can not be empty!!")
	@Size(min = 3,max = 12,message = "Name contain 3 to 12 charactors !!")
	private String userName;

	@Email(regexp = "^(.+)@(.+)$", message = "Invalid email !!")
	@NotBlank(message = "Please enter email id!!")
    @Column(unique = true)
	private String email;

    private String password;
	private String userRole;
	private boolean isActive;
	private String imageUrl;
	@Column(length = 300)
	private String about;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "tblUserLogin")
	private Set<TblContactDetails> tblContactDetails;


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Set<TblContactDetails> getTblContactDetails() {
		return tblContactDetails;
	}
	public void setTblContactDetails(Set<TblContactDetails> tblContactDetails) {
		this.tblContactDetails = tblContactDetails;
	}




}
