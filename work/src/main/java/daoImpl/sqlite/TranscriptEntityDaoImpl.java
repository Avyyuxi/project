package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.dbutils.DbUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.TranscriptEntityDao;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import util.HibernateUtil;
import util.dataAccess.ConnectionManager;

public class TranscriptEntityDaoImpl implements TranscriptEntityDao{

	@Override
	public HashMap<Student, TranscriptEntry> findBySection(Section section) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void addSection(Section sec, Student s) {
		// TODO Auto-generated method stub
		Transcript trans = s.getTranscript();
		TranscriptEntry transcriptEntry = new TranscriptEntry(null, s, sec, trans);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(transcriptEntry);
		session.close();
		tx.commit();
	}

}
