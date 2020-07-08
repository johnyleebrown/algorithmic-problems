package patterns.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class FileSearchService {
    public List<File> findFilesByCriteria(Directory directory, ISpecification criteria) {
        return directory.contents.stream()
                .filter(criteria::isSatisfiedBy)
                .collect(Collectors.toList());
    }
}
