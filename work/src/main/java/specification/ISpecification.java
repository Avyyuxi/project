package specification;

import model.EnrollmentStatus;

public interface ISpecification<T> {
	public EnrollmentStatus isSatisfiedBy(T t);
}
