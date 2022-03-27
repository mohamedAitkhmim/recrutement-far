package com.far.recrutement.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6436967100089888657L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userID;

	
	@Column(name = "EMAIL",unique=true)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String pass;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@Column(name = "ROLE")
	private String role;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		if(role.startsWith("ROLE_"))
		{
			this.role = role.substring(5);
		}
	}

	public User(String email, String pass, boolean enabled, String role) {
		super();
		this.email = email;
		this.pass = pass;
		this.enabled = enabled;
		this.role = role;
	}

	public User() {
		super();
	}
}