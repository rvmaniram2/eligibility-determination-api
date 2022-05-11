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
@Table(name = "CO_TRIGGERS")
public class CorrespondenceTrigger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CO_TRG_ID")
	private int id;

	@Column(name = "CASE_NUM")
	private int caseNum;

	@Column(name = "CO_PDF")
	private String cOPdf;

	@Column(name = "TRG_STATUS")
	private String TriggerStatus;

}
