package com.app.reg.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanInfoDto {

	private String holderName;
	private String holderSsn;
	private String planName;
	private String planStatus;
	private Date startDate;
	private Date endDate;
	private String benefitAmt;
	private String denialReason;
}
