package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

// TranscriptEntry.java - Chapter 14, Java 5 version.

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.

@Entity
public class TranscriptEntry implements Serializable {
	//------------
	// Attributes.
	//------------
	@Id
	private int transcriptEntry_id;
	private String grade;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="ssn")
	private Student student;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="sectionNo")
	private Section section;
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="transcript_id")
	private Transcript transcript;

	//----------------
	// Constructor(s).
	//----------------
	public TranscriptEntry(){
		
	}
	public TranscriptEntry(Student s, String grade, Section se) {
		this.setStudent(s);
		this.setSection(se);
		this.setGrade(grade);

		// Obtain the Student's transcript ...

		Transcript t = s.getTranscript();

		// ... and then link the Transcript and the TranscriptEntry
		// together bidirectionally.

		this.setTranscript(t);
		t.addTranscriptEntry(this);
	}

	//------------------
	// Accessor methods.
	//------------------


	public TranscriptEntry(String grade, Student student, Section section, Transcript transcript) {
		super();
		this.grade = grade;
		this.student = student;
		this.section = section;
		this.transcript = transcript;
	}

	public int getTranscriptEntry_id() {
		return transcriptEntry_id;
	}
	public void setTranscriptEntry_id(int transcriptEntry_id) {
		this.transcriptEntry_id = transcriptEntry_id;
	}
	public void setStudent(Student s) {
		student = s;
	}

	public Student getStudent() {
		return student;
	}

	public void setSection(Section s) {
		section = s;
	}

	public Section getSection() {
		return section;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	// These next two methods are declared to be static, so that they
	// may be used as utility methods.

	public static boolean validateGrade(String grade) {
		boolean outcome = false;

		if (grade.equals("F") ||
		    grade.equals("I")) {
			outcome = true;
		}

		if (grade.startsWith("A") ||
		    grade.startsWith("B") ||
		    grade.startsWith("C") ||
		    grade.startsWith("D")) {
			if (grade.length() == 1) outcome = true;
			else if (grade.length() == 2) {
				if (grade.endsWith("+") ||
				    grade.endsWith("-")) {
					outcome = true;
				}
			}
		}

		return outcome;
	}

	public static boolean passingGrade(String grade) {
		boolean outcome = false;

		// First, make sure it is a valid grade.

		if (validateGrade(grade)) {
			// Next, make sure that the grade is a D or better.

			if (grade.startsWith("A") ||
			    grade.startsWith("B") ||
			    grade.startsWith("C") ||
			    grade.startsWith("D")) {
				outcome = true;
			}
		}

		return outcome;
	}
}
