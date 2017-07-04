package service;

import dao.StudentDao;
import model.Student;

public class StudentService {
	private StudentDao studentDao;
	private Student student;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public StudentService(StudentDao studentDao,String ssn) {
		super();
		student = studentDao.findBySsn(ssn);
		this.studentDao = studentDao;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student findBySsn(String ssn){
		return studentDao.findBySsn(ssn);
	}
}
