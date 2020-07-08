package patterns.Specification;

public interface ISpecification {
    boolean isSatisfiedBy(File file);

    ISpecification and(ISpecification other);

    ISpecification or(ISpecification other);
}
