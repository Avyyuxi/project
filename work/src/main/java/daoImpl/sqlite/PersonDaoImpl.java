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

import dao.PersonDao;
import model.Course;
import model.Person;
import model.Professor;
import model.Student;
import util.HibernateUtil;
import util.dataAccess.ConnectionManager;

public class PersonDaoImpl implements PersonDao {

	@Override
	public HashMap<String, Professor> findAllProfessors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findBySsn(String ssn,int type) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = null;
		Person person = null;
		if(type==0){
			query = session.createQuery("select p from Student s where s.ssn="+ssn);
			Student s = (Student)query.uniqueResult();
			person.setName(s.getName());
			person.setPwd(s.getPwd());
			person.setSsn(s.getSsn());

		}
		else{
			query = session.createQuery("select p from Professor p where p.ssn="+ssn);
			Professor p = (Professor)query.uniqueResult();
			person.setName(p.getName());
			person.setPwd(p.getPwd());
			person.setSsn(p.getSsn());

		}
		System.out.println(person.getName()+"sss");
		session.close();
		tx.commit();
		return person;
		

	}

}
