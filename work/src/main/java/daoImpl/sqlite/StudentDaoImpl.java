package daoImpl.sqlite;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.StudentDao;
import model.Course;
import model.PlanOfStudy;
import model.Student;
import util.HibernateUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student findBySsn(String ssn) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select s from Student s where s.ssn="+ssn);
		Student s = (Student)query.uniqueResult();
		session.close();
		tx.commit();
		Session session1 = HibernateUtil.getSession();
		Transaction tx1 = session1.beginTransaction();
		Query query1 = session1.createQuery("select plan from Course plan where plan.planOfStudy.planOfStudy_id="+s.getPlanOfStudy().getPlanOfStudy_id());
		List<Course> list = (List)query1.list();
		s.getPlanOfStudy().setCourses(list);
		session1.close();
		tx1.commit();
		return s;
	}

}
