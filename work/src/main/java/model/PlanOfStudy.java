package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class PlanOfStudy implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int planOfStudy_id;
//	@OneToOne(mappedBy="planOfStudy")
//	private Student student;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="courseNo")
	private List<Course> courses;
//	public Student getStudent() {
//		return student;
//	}
//	public void setStudent(Student student) {
//		this.student = student;
//	}
	public List<Course> getCourses() {
		return courses;
	}

	public int getPlanOfStudy_id() {
		return planOfStudy_id;
	}

	public void setPlanOfStudy_id(int planOfStudy_id) {
		this.planOfStudy_id = planOfStudy_id;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public PlanOfStudy() {

	}
	
	public PlanOfStudy(int planOfStudy_id) {
		super();
		this.planOfStudy_id = planOfStudy_id;
	}

	public boolean VerifyPlan(Course course){
		if(this.courses.contains(course)){
			return true;
		}
		else{
			return false;
		}
	}
}
