package patterns.Specification;

public class FileSearchNameCriteria extends AbstractCompositeSpecification {

    private final String nameCriteria;

    public FileSearchNameCriteria(String nameCriteria) {
        this.nameCriteria = nameCriteria;
    }

    @Override
    public boolean isSatisfiedBy(File file) {
        return nameCriteria.equals(file.getName());
    }
}
