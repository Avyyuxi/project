package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SectionDao;
import model.Course;
import model.Professor;
import model.ScheduleOfClasses;
import model.Section;
import model.TranscriptEntry;
import util.HibernateUtil;
import util.dataAccess.ConnectionManager;

public class SectionDaoImpl implements SectionDao {

	@Override
	public List<Section> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> findBySemester(String semester) {
		// TODO Auto-generated method stub
		HashMap<String,Section> sections = new HashMap<String, Section>();
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select s from Section s where s.offeredIn.semester="+semester);
		List<Section> sectionlist = query.list();
		session.close();
		tx.commit();
		return sectionlist;

	}

	@Override
	public Section findByNo(int no) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select s from Section s where s.sectionNo="+no);
		Section section = (Section)query.uniqueResult();
		Query query1 = session.createQuery("select s from TranscriptEntry s where s.section.sectionNo="+no);
		List<TranscriptEntry> list = query1.list();
		section.setAssignedGrades(list);
		session.close();
		tx.commit();
		return section;
	}

}
