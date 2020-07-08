package patterns.Specification;

public abstract class AbstractCompositeSpecification implements ISpecification {

    @Override
    public ISpecification and(ISpecification other) {
        return new AndSpecification(this, other);
    }

    @Override
    public ISpecification or(ISpecification other) {
        return new OrSpecification(this, other);
    }
}
