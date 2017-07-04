package dao;

import java.util.HashMap;

import model.Person;
import model.Professor;;

public interface PersonDao {
	
	public HashMap<String, Professor> findAllProfessors();
	public Person findBySsn(String ssn,int type);
	

}
