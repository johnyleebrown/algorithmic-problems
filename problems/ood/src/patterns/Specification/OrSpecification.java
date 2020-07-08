package patterns.Specification;

public class OrSpecification extends AbstractCompositeSpecification {

    ISpecification leftCondition, rightCondition;

    public OrSpecification(ISpecification leftCondition, ISpecification rightCondition) {
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public boolean isSatisfiedBy(File file) {
        return leftCondition.isSatisfiedBy(file) || rightCondition.isSatisfiedBy(file);
    }
}
