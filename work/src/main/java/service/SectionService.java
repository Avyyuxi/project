package service;

import dao.SectionDao;
import model.Section;

public class SectionService {
	private static Section section;
	private SectionDao sectionDao = null;
	public SectionService(int No,SectionDao dao){
		section = dao.findByNo(No);
	}
	public static Section getSection() {
		return section;
	}
	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	
}
