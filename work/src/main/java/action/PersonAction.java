package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DaoFactory;
import model.Person;
import model.Professor;
import model.Student;
import service.ProfessorService;
import service.StudentService;

public class PersonAction extends ActionSupport{
	private String ssn;
	private String pwd;
	private boolean result;
	private int type;
	private StudentService studentService ;
	private ProfessorService professorService = new ProfessorService(DaoFactory.createProfessorDao());


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}





	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public ProfessorService getProfessorService() {
		return professorService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String login(){
		
		System.out.println(ssn);
		studentService = new StudentService(DaoFactory.createStudentDao(),ssn);
		result = false;
		Student s = new Student();
		Professor p = new Professor();
		System.out.println(type);
		try{
			if(type==0){
				s = studentService.findBySsn(ssn);
				if(s.login(pwd)){
					result = true;				
					ActionContext.getContext().getSession().put("person",ssn);
					ActionContext.getContext().getSession().put("role","student");
					return "JSONRESULT";
				}
			}
			else{
				p = professorService.findBySsn(ssn);
				if(p.login(pwd)){
					result = true;
					ActionContext.getContext().getSession().put("person",ssn);
					ActionContext.getContext().getSession().put("role","professor");
					return "JSONRESULT";
				}
			}
			return "JSONRESULT";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "JSONRESULT";
		}		

		
	}
}
