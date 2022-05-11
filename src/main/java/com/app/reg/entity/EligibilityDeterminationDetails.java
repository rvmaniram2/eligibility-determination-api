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
@Table(name = "ED_ELIG_DTLS")
public class EligibilityDeterminationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ED_TRACE_ID")
	private int eDTraceId;

	@Column(name = "CASE_NUM")
	private int caseNum;

	@Column(name = "HOLDER_NAME")
	private String holderName;

	@Column(name = "HOLDER_SSN")
	private String holderSsn;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "PLAN_STATUS")
	private String planStatus;

	@Column(name = "PLAN_START_DATE")
	private Date planStartDate;

	@Column(name = "PLAN_END_DATE")
	private Date planEndDate;

	@Column(name = "BENEFIT_AMT")
	private String benefitAmount;

	@Column(name = "DENIAL_REASON")
	private String denialReason;

}
