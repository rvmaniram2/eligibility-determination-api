package com.app.reg.service.impl;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.reg.dto.PlanInfoDto;
import com.app.reg.entity.AppRegEntity;
import com.app.reg.entity.CorrespondenceTrigger;
import com.app.reg.entity.DCChilds;
import com.app.reg.entity.DCEducation;
import com.app.reg.entity.DCIncome;
import com.app.reg.entity.DCPlan;
import com.app.reg.entity.EligibilityDeterminationDetails;
import com.app.reg.repo.AppRegEntityRepository;
import com.app.reg.repo.CorrespondenceTriggerRepository;
import com.app.reg.repo.DCChildsRepository;
import com.app.reg.repo.DCEducationRepository;
import com.app.reg.repo.DCIncomeRepository;
import com.app.reg.repo.DCPlanRepository;
import com.app.reg.repo.EligibilityDeterminationDetailsRepository;
import com.app.reg.service.EligibilityDeterminationService;

@Service
public class EligibilityDeterminationServiceImpl implements EligibilityDeterminationService {

	@Autowired
	private DCChildsRepository childRepo;

	@Autowired
	private DCEducationRepository educationRepo;

	@Autowired
	private DCIncomeRepository incomeRepo;

	@Autowired
	private DCPlanRepository planRepo;

	@Autowired
	private AppRegEntityRepository appRegRepository;

	@Autowired
	private EligibilityDeterminationDetailsRepository edDetailsRepo;

	@Autowired
	private CorrespondenceTriggerRepository coTriggerRepository;

	public int getRandomNumber() {
		return new Random().nextInt(1000000000);
	}

	@Override
	public PlanInfoDto getEligibilityDetermination(int caseNumber) {

		Optional<AppRegEntity> userOpt = appRegRepository.findById(caseNumber);

		if (userOpt.isEmpty())
			return null;
		AppRegEntity user = userOpt.get();

		DCPlan plan = planRepo.findByCaseNum(caseNumber);

		DCIncome income = incomeRepo.findByCaseNum(caseNumber);

		List<DCChilds> childs = childRepo.findByCaseNum(caseNumber);

		DCEducation edu = educationRepo.findByCaseNum(caseNumber);

		if (plan.getPlanName().equalsIgnoreCase("SNAP")) {
			if (income.getSalaryIncome() <= 300)
				return updateEligibilityData(true, "SNAP", user, null);
			else
				return updateEligibilityData(false, "SNAP", user, "Employment salary is more than 300$");

		} else if (plan.getPlanName().equalsIgnoreCase("CCAP")) {

			if (income.getSalaryIncome() <= 300 && childs.stream().filter(c -> c.getChildAge() > 16).count() == 0
					&& !childs.isEmpty())
				return updateEligibilityData(true, "CCAP", user, null);
			else
				return updateEligibilityData(false, "CCAP", user,
						"Employment salary is more than 300$ or kids age is more than 16 or no kids or more than 2 kids");
		}

		else if (plan.getPlanName().equalsIgnoreCase("Medicaid")) {

			if (income.getSalaryIncome() <= 300 && income.getPropertyIncome() == 0.0)
				return updateEligibilityData(true, "Medicaid", user, null);
			else
				return updateEligibilityData(false, "Medicaid", user,
						"Employment salary is more than 300$ or Property income is there");
		}

		else if (plan.getPlanName().equalsIgnoreCase("Medicare")) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			if (getDiffYears(new Date(), user.getDob()) > 65)
				return updateEligibilityData(true, "Medicare", user, null);
			else
				return updateEligibilityData(false, "Medicare", user, "age is below 65");
		} else if (plan.getPlanName().equalsIgnoreCase("NJW")) {

			if (edu.getGraduationStatus().equals("Completed") && income.getSalaryIncome() == 0.0)
				return updateEligibilityData(true, "NJW", user, null);
			else
				return updateEligibilityData(false, "NJW", user, "graduaton is not completed or citizen is employed");
		}

		return null;
	}

	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(YEAR) - a.get(YEAR);
		if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

	public PlanInfoDto updateEligibilityData(boolean flag, String status, AppRegEntity user, String reason) {

		EligibilityDeterminationDetails details = new EligibilityDeterminationDetails();
		CorrespondenceTrigger tr = new CorrespondenceTrigger(new Random().nextInt(1000000000), user.getAppNum(), null,
				"Pending");

		details.setCaseNum(user.getAppNum());
		details.setEDTraceId(new Random().nextInt(1000000000));
		details.setHolderName(user.getFullname());
		details.setHolderSsn(user.getSsn());

		details.setPlanName(status);
		if (flag) {
			details.setPlanStatus("Approved");
			details.setBenefitAmount("3000$");

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			details.setPlanStartDate(calendar.getTime());
			calendar.add(Calendar.MONTH, 3);
			details.setPlanEndDate(calendar.getTime());

		} else {
			details.setPlanStatus("Denied");
			details.setDenialReason(reason);
		}
		edDetailsRepo.save(details);
		coTriggerRepository.save(tr);

		PlanInfoDto res = new PlanInfoDto();
		res.setBenefitAmt("3000");
		res.setDenialReason(reason);

		res.setHolderName(user.getFullname());
		res.setHolderSsn(user.getSsn());
		res.setPlanName(status);
		res.setPlanStatus(flag ? "Approved" : "Denied");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		res.setStartDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 3);
		res.setEndDate(calendar.getTime());

		return res;
	}

}
