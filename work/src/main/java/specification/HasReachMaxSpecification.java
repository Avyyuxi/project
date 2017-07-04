package specification;

import model.EnrollmentStatus;
import model.Section;

public class HasReachMaxSpecification implements ISpecification<Section> {

	@Override
	public EnrollmentStatus isSatisfiedBy(Section section) {
		System.out.println("xuan:"+section.getTotalEnrollment());
		if(section.getTotalEnrollment()<=section.getSeatingCapacity()){
			return EnrollmentStatus.success;
		}
		else{
			return EnrollmentStatus.secFull;
		}
		// TODO Auto-generated method stub

		
	}

}
