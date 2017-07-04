package model;
// Professor.java - Chapter 14, Java 5 version.

import java.io.Serializable;

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Professor extends Person implements Serializable{
	//------------
	// Attributes.
	//------------
	private String name;
	@Id
	@Column(length=12)
	private String ssn;
	private String pwd;
	private String title;
	private String department;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="sectionNo")
	private List<Section> teaches; 

	//----------------
	// Constructor(s).
	//----------------


	public Professor(){
		
	}

	public Professor(String name, String ssn, String pwd, String title, String department) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.pwd = pwd;
		this.title = title;
		this.department = department;
	}

	public Professor(String name, String ssn, String pwd, String title, String department, ArrayList<Section> teaches) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.pwd = pwd;
		this.title = title;
		this.department = department;
		this.teaches = teaches;
	}

	//----------------
	// Accessor methods.
	//----------------

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTeaches(List<Section> teaches) {
		this.teaches = teaches;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<Section> getTeaches() {
		return teaches;
	}
	public void setTeaches(ArrayList<Section> teaches) {
		this.teaches = teaches;
	}
	public String getTitle() {
		return title;
	}

	public void setDepartment(String dept) {
		department = dept;
	}

	public String getDepartment() {
		return department;
	}

	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	public void display() {
		// First, let's display the generic Person info.

		//super.display();
		
		// Then, display Professor-specific info.

		System.out.println("Professor-Specific Information:");
		System.out.println("\tTitle:  " + getTitle());
		System.out.println("\tTeaches for Dept.:  " + getDepartment());
		displayTeachingAssignments();

		// Finish with a blank line.

		System.out.println();
	}
	
	// We are forced to program this method because it is specified
	// as an abstract method in our parent class (Person); failing to
	// do so would render the Professor class abstract, as well.
	//
	// For a Professor, we wish to return a String as follows:
	//
	// 	Josephine Blow (Associate Professor, Math)

	@Override
	public String toString() {
		return getName() + " (" + getTitle() + ", " +
		       getDepartment() + ")";
	}

	public void displayTeachingAssignments() {
		System.out.println("Teaching Assignments for " + getName() + ":");
		
		// We'll step through the teaches ArrayList, processing
		// Section objects one at a time.

		if (teaches.size() == 0) {
			System.out.println("\t(none)");
		}

		else for (Section s : teaches) {
			// Note how we call upon the Section object to do
			// a lot of the work for us!

			System.out.println("\tCourse No.:  " +
				s.getRepresentedCourse().getCourseNo());
			System.out.println("\tSection No.:  " + 
				s.getSectionNo());
			System.out.println("\tCourse Name:  " +
				s.getRepresentedCourse().getCourseName());
			System.out.println("\tDay and Time:  " +
				s.getDayOfWeek() + " - " + 
				s.getTimeOfDay());
			System.out.println("\t-----");
		}
	}
	public boolean login(String newpwd){
		System.out.println(newpwd+"+"+pwd);
		if(!pwd.equals(newpwd)){
			return false;
		}
		else{
			return true;
		}
	}
	public void agreeToTeach(Section s) {
		teaches.add(s);

		// We need to link this bidirectionally.

		s.setInstructor(this);
	}
}
