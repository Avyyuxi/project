package service;

import dao.ProfessorDao;
import model.Professor;

public class ProfessorService {
	private ProfessorDao professorDao;
	
	public void setProfessorDao(ProfessorDao professorDao) {
		this.professorDao = professorDao;
	}

	public ProfessorService(ProfessorDao professorDao) {
		super();
		this.professorDao = professorDao;
	}

	public Professor findBySsn(String ssn){
		return professorDao.findBySsn(ssn);
	}
}
