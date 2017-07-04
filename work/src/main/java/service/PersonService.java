package service;

import dao.PersonDao;
import model.Person;

public class PersonService {
	private PersonDao personDao;

	public PersonService(PersonDao personDao) {
		super();
		this.personDao = personDao;
	}
	public Person findBySsn(String ssn,int type){
		return personDao.findBySsn(ssn,type);
	}
}
