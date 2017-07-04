package dao;

import java.util.List;

import model.Course;
import model.Student;

public interface PlanOfStudyDao {
	public List<Course> queryAll(Student student);
}
