package patterns.Specification;

public class FileSearchSizeCriteria extends AbstractCompositeSpecification {

    private final long fileSizeCriteria;

    public FileSearchSizeCriteria(long fileSize) {
        this.fileSizeCriteria = fileSize;
    }

    @Override
    public boolean isSatisfiedBy(File file) {
        return fileSizeCriteria == file.getSize();
    }
}
