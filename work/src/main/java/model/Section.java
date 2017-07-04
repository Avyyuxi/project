package model;
// Section.java - Chapter 14, Java 5 version.

import java.io.Serializable;
import java.util.ArrayList;

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import specification.HasReachMaxSpecification;
import specification.ISpecification;
import specification.InPlanOfStudySpecification;
import specification.PreCourseSpecification;
@Entity
public class Section implements Serializable {
	//------------
	// Attributes.
	//------------
	@Id
	private int sectionNo;
	private String dayOfWeek;
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="courseNo")
	private Course representedCourse;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="scheduleOfClassesid")
	private ScheduleOfClasses offeredIn;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="ssn")
	private Professor instructor;

	// The enrolledStudents HashMap stores Student object references,
	// using each Student's ssn as a String key.
//	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
//	@JoinColumn(name="ssn")
//	private List<Student> enrolledStudents; 

	// The assignedGrades HashMap stores TranscriptEntry object
	// references, using a reference to the Student to whom it belongs 
	// as the key.
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name="transcriptEntry_id")
	private List<TranscriptEntry> assignedGrades; 
	
	//----------------
	// Constructor(s).
	//----------------



	public Section() {
		super();

	}





	public Section(int sectionNo, String dayOfWeek, String timeOfDay, String room, int seatingCapacity,
			Course representedCourse, ScheduleOfClasses offeredIn, Professor instructor,
			List<TranscriptEntry> assignedGrades) {
		super();
		this.sectionNo = sectionNo;
		this.dayOfWeek = dayOfWeek;
		this.timeOfDay = timeOfDay;
		this.room = room;
		this.seatingCapacity = seatingCapacity;
		this.representedCourse = representedCourse;
		this.offeredIn = offeredIn;
		this.instructor = instructor;
		this.assignedGrades = assignedGrades;
	}





	//------------------
	// Accessor methods.
	//------------------
//
//	public List<Student> getEnrolledStudents() {
//		return enrolledStudents;
//	}
//
//
//	public void setEnrolledStudents(List<Student> enrolledStudents) {
//		this.enrolledStudents = enrolledStudents;
//	}


	public List<TranscriptEntry> getAssignedGrades() {
		return assignedGrades;
	}


	public void setAssignedGrades(List<TranscriptEntry> assignedGrades) {
		this.assignedGrades = assignedGrades;
	}


	public void setSectionNo(int no) {
		sectionNo = no;
	}
	
	public int getSectionNo() {
		return sectionNo;
	}
	

		
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public void setTimeOfDay(String time) {
		timeOfDay = time;
	}
	
	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setInstructor(Professor prof) {
		instructor = prof;
	}
	
	public Professor getInstructor() {
		return instructor;
	}
	
	public void setRepresentedCourse(Course c) {
		representedCourse = c;
	}
	
	public Course getRepresentedCourse() {
		return representedCourse;
	}		

	public void setRoom(String r) {
		room = r;
	}

	public String getRoom() {
		return room;
	}

	public void setSeatingCapacity(int c) {
		seatingCapacity = c;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setOfferedIn(ScheduleOfClasses soc) {
		offeredIn = soc;
	}

	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}	

	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	// For a Section, we want its String representation to look
	// as follows:
	//
	//	MATH101 - 1 - M - 8:00 AM

	@Override
	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " +
		       getSectionNo() + " - " +
		       getDayOfWeek() + " - " +
		       getTimeOfDay();
	}

	// The full section number is a concatenation of the
	// course no. and section no., separated by a hyphen;
	// e.g., "ART101 - 1".

	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + 
		       " - " + getSectionNo();
	}
	
	public String getFullSectionInfo() {
		return getRepresentedCourse().getCourseName() + 
			   "-" + getDayOfWeek() + "-" +
		       "" + getTimeOfDay() +
		       "-" + this.getRoom();
	}
	

	// We use an enum -- EnrollmentStatus -- to return an indication of the
	// outcome of the request to enroll Student s.  See EnrollmentStatus.java
	// for details on this enum.

	public EnrollmentStatus enroll(Student s,PlanOfStudy planOfStudy) {
		// First, make sure that this Student is not already
		// enrolled for this Section, and that he/she has
		// NEVER taken and passed the course before.
		List<ISpecification> ispecifications = new ArrayList<>();
		ispecifications.add(new InPlanOfStudySpecification(planOfStudy));
		ispecifications.add(new HasReachMaxSpecification());
		ispecifications.add(new PreCourseSpecification(s));
		for(ISpecification i :ispecifications){
			if(!i.isSatisfiedBy(this).equals(EnrollmentStatus.success)){
				return i.isSatisfiedBy(this);
			}
		}
		return EnrollmentStatus.success;
	}
	
	// A private "housekeeping" method.

	private boolean confirmSeatAvailability() {
		if (assignedGrades.size() < getSeatingCapacity()) return true;
		else return false;
	}

	public boolean drop(Student s) {
		// We may only drop a student if he/she is enrolled.

		if (!s.isEnrolledIn(this)) return false;
		else {
			// Find the student in our HashMap, and remove it.

			assignedGrades.remove(s.getSsn());

			// Note bidirectionality.

			s.dropSection(this);
			return true;
		}
	}

	public int getTotalEnrollment() {
		return this.getAssignedGrades().size();
	}	

	public void display() {
		System.out.println("Section Information:");
		System.out.println("\tSemester:  " + getOfferedIn().getSemester());
		System.out.println("\tCourse No.:  " + 
				   getRepresentedCourse().getCourseNo());
		System.out.println("\tSection No:  " + getSectionNo());
		System.out.println("\tOffered:  " + getDayOfWeek() + 
				   " at " + getTimeOfDay());
		System.out.println("\tIn Room:  " + getRoom());
		if (getInstructor() != null) 
			System.out.println("\tProfessor:  " + 
				   getInstructor().getName());
		displayStudentRoster();
	}
	
	public void displayStudentRoster() {
		System.out.print("\tTotal of " + getTotalEnrollment() + 
				   " students enrolled");

		// How we punctuate the preceding message depends on 
		// how many students are enrolled (note that we used
		// a print() vs. println() call above).

		if (getTotalEnrollment() == 0) System.out.println(".");
		else System.out.println(", as follows:");

		// Iterate through all of the values stored in the HashMap.

//		for (TranscriptEntry s : assignedGrades) {
//			System.out.println("\t\t" + s.getName());
//		}
	}
	public int displayleft(){
		return seatingCapacity - assignedGrades.size();
	}

	// This method returns the value null if the Student has not
	// been assigned a grade.

	public String getGrade(Student s) {
		String grade = null;


		return grade;
	}

	public boolean postGrade(Student s, String grade) {
		// First, validate that the grade is properly formed by calling
		// a utility method provided by the TranscriptEntry class.

		if (!TranscriptEntry.validateGrade(grade)) return false;

		// Make sure that we haven't previously assigned a
		// grade to this Student by looking in the HashMap
		// for an entry using this Student as the key.  If
		// we discover that a grade has already been assigned,
		// we return a value of false to indicate that
		// we are at risk of overwriting an existing grade.  
		// (A different method, eraseGrade(), can then be written
		// to allow a Professor to change his/her mind.)

	//	if (assignedGrades.get(s) != null) return false;

		// First, we create a new TranscriptEntry object.  Note
		// that we are passing in a reference to THIS Section,
		// because we want the TranscriptEntry object,
		// as an association class ..., to maintain
		// "handles" on the Section as well as on the Student.
		// (We'll let the TranscriptEntry constructor take care of
		// linking this T.E. to the correct Transcript.)

		TranscriptEntry te = new TranscriptEntry(s, grade, this);

		// Then, we "remember" this grade because we wish for
		// the connection between a T.E. and a Section to be
		// bidirectional.

	//	assignedGrades.put(s, te);

		return true;
	}
	
	public boolean isSectionOf(Course c) {
		if (c == representedCourse) return true;
		else return false;
	}
}
