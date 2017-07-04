package model;
// Transcript.java - Chapter 14, Java 5 version.

import java.io.Serializable;

// Copyright 2005 by Jacquie Barker - all rights reserved.

// A MODEL class.


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Transcript implements Serializable{
	//------------
	// Attributes.
	//------------
	@Id
	private int transcript_id;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="transcriptEntry_id")
	private List<TranscriptEntry> transcriptEntries; 
	@OneToOne(mappedBy="transcript")
	private Student studentOwner;

	//----------------
	// Constructor(s).
	//----------------
	public Transcript(){
		
	}
	public Transcript(Student s) {
		setStudentOwner(s);

		// Note that we're instantiating empty support Collection(s).

		transcriptEntries = new ArrayList<TranscriptEntry>();
	}

	//------------------
	// Accessor methods.
	//------------------



	public List<TranscriptEntry> getTranscriptEntries() {
		return transcriptEntries;
	}

	public int getTranscript_id() {
		return transcript_id;
	}

	public void setTranscript_id(int transcript_id) {
		this.transcript_id = transcript_id;
	}

	public void setTranscriptEntries(List<TranscriptEntry> transcriptEntries) {
		this.transcriptEntries = transcriptEntries;
	}

	public void setStudentOwner(Student s) {
		studentOwner = s;
	}

	public Student getStudentOwner() {
		return studentOwner;
	}

	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	public boolean verifyCompletion(Course c) {
		boolean outcome = false;

		// Step through all TranscriptEntries, looking for one
		// which reflects a Section of the Course of interest.

//		for (TranscriptEntry te : transcriptEntries) {
//			Section s = te.getSection();
//
//			if (s.isSectionOf(c)) {
//			    // Ensure that the grade was high enough.
//
//			    if (TranscriptEntry.passingGrade(te.getGrade())) {
//				outcome = true;
//
//				// We've found one, so we can afford to
//				// terminate the loop now.
//
//				break;
//			    }
//			}
//		}

		return false;
	}

	public void addTranscriptEntry(TranscriptEntry te) {
		transcriptEntries.add(te);
	}

	public void display() {
		System.out.println("Transcript for:  " +
				   getStudentOwner().toString());

		if (transcriptEntries.size() == 0) {
			System.out.println("\t(no entries)");
		}
		else for (TranscriptEntry te : transcriptEntries) {
			Section sec = te.getSection();

			Course c = sec.getRepresentedCourse();

			ScheduleOfClasses soc = sec.getOfferedIn();

			System.out.println("\tSemester:        " +
					   soc.getSemester());
			System.out.println("\tCourse No.:      " +
					   c.getCourseNo());
			System.out.println("\tCredits:         " +
					   c.getCredits());
			System.out.println("\tGrade Received:  " +
					   te.getGrade());
			System.out.println("\t-----");
		}
	}
}
