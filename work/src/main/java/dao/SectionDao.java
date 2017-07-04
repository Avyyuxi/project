package dao;

import java.util.HashMap;
import java.util.List;

import model.Section;

public interface SectionDao {

	public List<Section> findAll();
	public List<Section> findBySemester(String semester);
	public Section findByNo(int no);
}
