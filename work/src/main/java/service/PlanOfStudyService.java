package service;

import dao.PlanOfStudyDao;
import model.PlanOfStudy;

public class PlanOfStudyService {
	private PlanOfStudyDao planOfStudyDao;
	private static PlanOfStudy planOfStudy;

	public void setPlanOfStudyDao(PlanOfStudyDao planOfStudyDao) {
		this.planOfStudyDao = planOfStudyDao;
	}

	public PlanOfStudyService(PlanOfStudyDao planOfStudyDao) {
		super();
		this.planOfStudyDao = planOfStudyDao;
	}


	
}
