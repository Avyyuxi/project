package service;

import dao.TranscriptEntityDao;
import model.Section;
import model.Student;

public class TranscriptEntryService {
	private TranscriptEntityDao transcriptEntityDao;

	public void setTranscriptEntityDao(TranscriptEntityDao transcriptEntityDao) {
		this.transcriptEntityDao = transcriptEntityDao;
	}
	
	public TranscriptEntryService(TranscriptEntityDao transcriptEntityDao) {
		super();
		this.transcriptEntityDao = transcriptEntityDao;
	}

	public void addSection(Section sec,Student s){
		transcriptEntityDao.addSection(sec, s);
	}
	
}
