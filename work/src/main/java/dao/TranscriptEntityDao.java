package dao;

import java.util.HashMap;

import model.Section;
import model.Student;
import model.TranscriptEntry;

public interface TranscriptEntityDao {
	public HashMap<Student,TranscriptEntry> findBySection(Section section);
	public void addSection(Section sec,Student s);
}
