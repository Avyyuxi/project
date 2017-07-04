package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import dao.CourseDao;
import model.Course;
import util.dataAccess.ConnectionManager;

public class CourseDaoImpl implements CourseDao {

	@Override
	public HashMap<String, Course> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findByNo(String courseNo) {
		// TODO Auto-generated method stub
		
	   	Course course = new Course();
	   	Connection conn = null;
	   	ResultSet rs1 = null;
	   	PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement("select * from Course as c  where c.CourseNumber='"+courseNo+"'") ;
			rs1 = pstmt.executeQuery(); 
		   	 while(rs1.next()){ 
		   		 course.setCourseNo(rs1.getString(1));
		   		 course.setCourseName(rs1.getString(2));
		   		 course.setCredits(rs1.getDouble(3));
		   	 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {  
			    DbUtils.closeQuietly(rs1);  
			    DbUtils.closeQuietly(pstmt);  
			    DbUtils.closeQuietly(conn);  
			}  
		return course;
	}

}
