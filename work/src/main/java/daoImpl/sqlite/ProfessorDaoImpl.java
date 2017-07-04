package daoImpl.sqlite;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ProfessorDao;
import model.Professor;
import model.Student;
import util.HibernateUtil;

public class ProfessorDaoImpl implements ProfessorDao {

	@Override
	public Professor findBySsn(String ssn) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select p from Professor p where p.ssn="+ssn);
		Professor s = (Professor)query.uniqueResult();
		session.close();
		tx.commit();
		return s;
	}

}
