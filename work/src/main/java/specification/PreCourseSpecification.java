package specification;

import java.util.List;

import model.Course;
import model.EnrollmentStatus;
import model.Person;
import model.Section;
import model.Student;
import model.Transcript;
public class PreCourseSpecification implements ISpecification<Section> {
	
	private Student student;	

	public PreCourseSpecification(Student student) {
		super();
		this.student = student;
	}

	@Override
	public EnrollmentStatus isSatisfiedBy(Section section) {
		// TODO Auto-generated method stub
		Transcript transcript = student.getTranscript();
		Course c = section.getRepresentedCourse();
		if (c.hasPrerequisites()) {
			for (Course pre : c.getPrerequisites()) {
				// See if the Student's Transcript reflects
				// successful completion of the prerequisite.

				if (!transcript.verifyCompletion(pre)) {
					return EnrollmentStatus.prereq;
				}
			}
		}
		if (student.isCurrentlyEnrolledInSimilar(section) || 
		    transcript.verifyCompletion(section.getRepresentedCourse())) {
			return EnrollmentStatus.prevEnroll;
		}
		else{
			return EnrollmentStatus.success;
		}
	} 

}
