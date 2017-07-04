package specification;

import model.Course;
import model.EnrollmentStatus;
import model.PlanOfStudy;
import model.Section;
import model.Student;
import model.Transcript;

public class InPlanOfStudySpecification implements ISpecification<Section> {
	private PlanOfStudy palnOfStudy;

	
	public InPlanOfStudySpecification(PlanOfStudy palnOfStudy) {
		super();
		this.palnOfStudy = palnOfStudy;
	}


	@Override
	public EnrollmentStatus isSatisfiedBy(Section section) {
		// TODO Auto-generated method stub
		for(Course c : palnOfStudy.getCourses()){
			System.out.println("section:"+section.getRepresentedCourse().getCourseNo());
			System.out.println("courseNo:"+c.getCourseNo());
			if(section.getRepresentedCourse().getCourseNo().equals(c.getCourseNo())){
				return EnrollmentStatus.success;
			}
		}
		return EnrollmentStatus.notInPlan;
	}

}
