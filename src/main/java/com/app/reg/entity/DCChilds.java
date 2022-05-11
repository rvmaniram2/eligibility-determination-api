package com.app.reg.entity;

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
@Table(name = "dc_childs")
public class DCChilds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "child_name")
	private String childName;

	@Column(name = "child_age")
	private int childAge;

	@Column(name = "child_ssn")
	private String childSsn;

	@Column(name = "CASE_NUM")
	private int caseNum;
}
