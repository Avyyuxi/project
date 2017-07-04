import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import model.Course;
import model.EnrollmentStatus;
import model.Person;
import model.PlanOfStudy;
import model.Professor;
import model.Section;
import model.Student;
import util.HibernateUtil;

public class hibernateTest {
	public static ArrayList<Professor> faculty; 
	public static ArrayList<Student> studentBody; 
	public static ArrayList<Course> courseCatalog; 
	@Test
	public void test1(){
		Professor p1, p2, p3;
		Student s1, s2, s3;
		Course c1, c2, c3, c4, c5;
		Section sec1, sec2, sec3, sec4, sec5, sec6, sec7;

		// Create various objects by calling the appropriate 
		// constructors.  (We'd normally be reading in such data
		// from a database or file ...)

		// -----------
		// Professors.
		// -----------

		p1 = new Professor("Jacquie Barker", "123-45-6789", 
				"123456", "Information Technology","Adjunct Professor");		

		p2 = new Professor("John Smith", "567-81-2345",
				"123456", "Chemistry","Full Professor");		

		p3 = new Professor("Snidely Whiplash", "987-65-4321",
				"123456", "Physical Education","Full Professor");		

		// Add these to the appropriate ArrayList.

		faculty = new ArrayList<Professor>();
		faculty.add(p1);
		faculty.add(p2);
		faculty.add(p3);

		// ---------
		// Students.
		// ---------
		
		s1 = new Student("Joe Blow", "111-11-1111", "Math", "M.S.","123456");

		s2 = new Student("Fred Schnurd", "222-22-2222", 
				 "Information Technology", "Ph. D.","123456");

		s3 = new Student("Mary Smith", "333-33-3333", "Physics", "B.S.","123456");

		// Add these to the appropriate ArrayList.

		studentBody = new ArrayList<Student>();
		studentBody.add(s1);
		studentBody.add(s2);
		studentBody.add(s3);

		// --------
		// Courses.
		// --------
		
		c1 = new Course("CMP101",
				"Beginning Computer Technology", 3.0);

		c2 = new Course("OBJ101", 
				"Object Methods for Software Development", 3.0);

		c3 = new Course("CMP283", 
				"Higher Level Languages (Java)", 3.0);

		c4 = new Course("CMP999",
				"Living Brain Computers", 3.0);

		c5 = new Course("ART101",
				"Beginning Basketweaving", 3.0);

		// Add these to the appropriate ArrayList.

		courseCatalog = new ArrayList<Course>();
		courseCatalog.add(c1);
		courseCatalog.add(c2);
		courseCatalog.add(c3);
		courseCatalog.add(c4);
		courseCatalog.add(c5);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(p1);
		session.save(p2);
		session.save(p3);
		
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		PlanOfStudy pl1 = new PlanOfStudy(1);
		PlanOfStudy pl2 = new PlanOfStudy(2);
		PlanOfStudy pl3 = new PlanOfStudy(3);
		c1.setPlanOfStudy(pl1);
		c2.setPlanOfStudy(pl2);
		c3.setPlanOfStudy(pl3);
		session.save(c1);
		session.save(c2);
		session.save(c3);
		session.save(c4);
		session.save(c5);
		
		tx.commit();
	}
	@Test
	public void test2(){
		SchemaExport export = HibernateUtil.getExport();
		export.create(true, true);;
	}
	@Test
	public void test3(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select s from Student s where s.ssn=111");
		Student person = (Student)query.uniqueResult();
		System.out.println(person.getName());
		tx.commit();
	}
	@Test
	public void test4(){
		String ssn = "111";
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select s from Student s where s.ssn="+ssn);
		Student s = (Student)query.uniqueResult();
		tx.commit();
		System.out.println("qqqq");
		System.out.println("sss"+s.getName());
		Session session1 = HibernateUtil.getSession();
		Transaction tx1 = session1.beginTransaction();
		Query query1 = session1.createQuery("select plan from Course plan where plan.planOfStudy.planOfStudy_id="+1);
		List<Course> list = (List)query1.list();
		s.getPlanOfStudy().setCourses(list);
		System.out.println("sizeΪ"+s.getPlanOfStudy().getCourses().size());
		tx1.commit();
	}
}
