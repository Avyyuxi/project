package model;
// ScheduleOfClasses.java - Chapter 14, Java 5 version.

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class ScheduleOfClasses implements Serializable{
	//------------
	// Attributes.
	//------------
	@Id
	private int scheduleOfClasses_id;
	private String semester;

	// This HashMap stores Section object references, using
	// a String concatenation of course no. and section no. as the
	// key, e.g., "MATH101 - 1".
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="sectionNo")
	private List<Section> sectionsOffered; 

	//----------------
	// Constructor(s).
	//----------------


	//------------------
	// Accessor methods.
	//------------------

	public void setSemester(String semester) {
		this.semester = semester;
	}


	public ScheduleOfClasses(){
		
	}
	public ScheduleOfClasses(int scheduleOfClasses_id, String semester, List<Section> sectionsOffered) {
		super();
		this.scheduleOfClasses_id = scheduleOfClasses_id;
		this.semester = semester;
		this.sectionsOffered = sectionsOffered;
	}



	public int getScheduleOfClasses_id() {
		return scheduleOfClasses_id;
	}



	public void setScheduleOfClasses_id(int scheduleOfClasses_id) {
		this.scheduleOfClasses_id = scheduleOfClasses_id;
	}



	public List<Section> getSectionsOffered() {
		return sectionsOffered;
	}

	public void setSectionsOffered(List<Section> sectionsOffered) {
		this.sectionsOffered = sectionsOffered;
	}

	public ScheduleOfClasses(String semester, List<Section> list) {
		super();
		this.semester = semester;
		this.sectionsOffered = list;
	}

	public void setSectionsOffered(ArrayList<Section> sectionsOffered) {
		this.sectionsOffered = sectionsOffered;
	}

	public String getSemester() {
		return semester;
	}


	//-----------------------------
	// Miscellaneous other methods.
	//-----------------------------

	public void display() {
		System.out.println("Schedule of Classes for " + getSemester());
		System.out.println();

		// Iterate through all the values in the HashMap.

		for (Section s : sectionsOffered) {
			s.display();
			System.out.println();
		}
	}

	public void addSection(Section s) {
		// We formulate a key by concatenating the course no.
		// and section no., separated by a hyphen.

		String key = s.getRepresentedCourse().getCourseNo() + 
			     " - " + s.getSectionNo();
		sectionsOffered.add(s);

		// Bidirectionally link the ScheduleOfClasses back to the Section.

		s.setOfferedIn(this);
	}

	// The full section number is a concatenation of the
	// course no. and section no., separated by a hyphen;
	// e.g., "ART101 - 1".

//	public Section findSection(String fullSectionNo) {
//		return sectionsOffered.get(fullSectionNo);
//	}

	public boolean isEmpty() {
		if (sectionsOffered.size() == 0) return true;
		else return false;
	}
}
