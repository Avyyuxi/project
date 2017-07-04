package dao;

import model.Student;

public interface StudentDao {
	public Student findBySsn(String ssn);
}
