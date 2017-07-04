package dao;

import model.Professor;

public interface ProfessorDao {
	public Professor findBySsn(String ssn);
}
