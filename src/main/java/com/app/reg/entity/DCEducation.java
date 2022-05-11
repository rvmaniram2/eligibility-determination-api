package com.app.reg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dc_education")
public class DCEducation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "graduation_status")
	private String graduationStatus;

	@Column(name = "graduation_year")
	private String graduationYear;

	@Column(name = "university")
	private String university;
	
	@Column(name = "highest_degree")
	private String highestDegree;

	@Column(name = "CASE_NUM")
	private int caseNum;
}
