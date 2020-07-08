package patterns.Specification;

public class AndSpecification extends AbstractCompositeSpecification {

    ISpecification leftCondition, rightCondition;

    public AndSpecification(ISpecification leftCondition, ISpecification rightCondition) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public boolean isSatisfiedBy(File file) {
        return leftCondition.isSatisfiedBy(file) && rightCondition.isSatisfiedBy(file);
    }
}
