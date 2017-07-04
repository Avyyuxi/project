package action;

import com.opensymphony.xwork2.ActionContext;

import dao.DaoFactory;
import model.EnrollmentStatus;
import model.PlanOfStudy;
import model.Section;
import model.Student;
import service.SectionService;
import service.StudentService;
import service.TranscriptEntryService;

public class SectionAction {
	private SectionService sectionService;
	private TranscriptEntryService transcriptEntryService = new TranscriptEntryService(DaoFactory.createTranscriptEntityDao());
	private int sectionNo;
	private String result;
	public SectionService getSectionService() {
		return sectionService;
	}
	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}
	public int getSectionNo() {
		return sectionNo;
	}
	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String enroll(){
		sectionService = new SectionService(sectionNo, DaoFactory.createSectioneDao());
		Section s = sectionService.getSection();
		if(ActionContext.getContext().getSession().get("role").equals("student")){
			String ssn = (String)ActionContext.getContext().getSession().get("person");
			StudentService studentService = new StudentService(DaoFactory.createStudentDao(), ssn);
			Student student = studentService.getStudent();
			PlanOfStudy planOfStudy = student.getPlanOfStudy();
			System.out.println(planOfStudy.getPlanOfStudy_id());
			System.out.println(planOfStudy.getCourses().size());
			EnrollmentStatus status = s.enroll(student, planOfStudy);
			result = status.value();
			if(status.value().contains("successful")){
				transcriptEntryService.addSection(s, student);
			}
		}
		else{
			result = "professor can't enroll!";
		}
		return "JSONRESULT";
	}
}
