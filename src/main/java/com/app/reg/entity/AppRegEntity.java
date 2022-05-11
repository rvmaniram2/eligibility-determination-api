package com.app.reg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AR_APP_REG")
public class AppRegEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APP_NUM")
	private int appNum;
	@Column(name = "FULL_NAME")
	private String fullname;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHNO")
	private String phno;
	@Column(name = "DOB")
	private Date dob;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "SSN")
	private String ssn;

	public AppRegEntity(int appNum) {
		super();
		this.appNum = appNum;
	}

}
