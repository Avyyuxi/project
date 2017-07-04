package model;
// Course.java - Chapter 14, Java 5 version.

import java.io.Serializable;

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Course implements Serializable{
	//------------
	// Attributes.
	//------------
	@Id
	@Column(length=8)
	private String courseNo;
	private String courseName;
	private double credits;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="sectionNo")
	private List<Section> offeredAsSection; 
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="planOfStudy_id",referencedColumnName="planOfStudy_id")
	private PlanOfStudy planOfStudy;
	@ManyToMany
	@JoinTable(
			name="course_precourse",
			joinColumns={@JoinColumn(name="courseNo")},
			inverseJoinColumns={@JoinColumn(name="precourseNo")}
			)
	private List<Course> prerequisites; 
	
	//----------------
	// Constructor(s).
	//----------------
	public Course(){

	}
	public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);

		// Note that we're instantiating empty support Collection(s).

	}

	//------------------
	// Accessor methods.
	//------------------

	public void setCourseNo(String cNo) {
		courseNo = cNo;
	}
	

	public PlanOfStudy getPlanOfStudy() {
		return planOfStudy;
	}
	public void setPlanOfStudy(PlanOfStudy planOfStudy) {
		this.planOfStudy = planOfStudy;
	}

	public List<Section> getOfferedAsSection() {
		return offeredAsSection;
	}
	public void setOfferedAsSection(List<Section> offeredAsSection) {
		this.offeredAsSection = offeredAsSection;
	}

	public String getCourseNo() {
		return courseNo;
	}
	
	public void setCourseName(String cName) {
		courseName = cName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCredits(double c) {
		credits = c;
	}
	
	public double getCredits() {
		return credits;
	}
	
	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	public void display() {
		System.out.println("Course Information:");
		System.out.println("\tCourse No.:  " + getCourseNo());
		System.out.println("\tCourse Name:  " + getCourseName());
		System.out.println("\tCredits:  " + getCredits());
		System.out.println("\tPrerequisite Courses:");

//		for (Course c : prerequisites) {
//			System.out.println("\t\t" + c.toString());
//		}

		// Note use of print vs. println in next line of code.

		System.out.print("\tOffered As Section(s):  ");
		for (Section s : offeredAsSection) {
			System.out.print(s.getSectionNo() + " ");
		}

		// Finish with a blank line.

		System.out.println();
	}
	
	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	@Override
	public String toString() {
		return getCourseNo() + ":  " + getCourseName();
	}

	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public boolean hasPrerequisites() {
//		if (prerequisites.size() > 0) return true;
//		else return false;
		return false;
	}

	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}

	public Section scheduleSection(String c, String time, String room,
				       int capacity) {
		// Create a new Section (note the creative way in
		// which we are assigning a section number) ...

//		Section s = new Section(offeredAsSection.size() + 1, 
//				c, time, this, room, capacity);
		
		// ... and then remember it!

//		addSection(s);
		
		return null;
	}

	public void addSection(Section s) {
		offeredAsSection.add(s);
	}
}
