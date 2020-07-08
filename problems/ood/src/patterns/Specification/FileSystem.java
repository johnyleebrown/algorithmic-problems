package patterns.Specification;

import java.util.List;

public class FileSystem {
    public static void main(String[] args) {
        Directory mainDirectory = new Directory("main");
        File document = new File("new document", FileExtension.TXT);
        document.setContents("contents");
        mainDirectory.addFile(document);
        File image = new File("new image", FileExtension.PNG);
        image.setContents("contents");
        mainDirectory.addFile(image);

        ISpecification nameCriteria = new FileSearchNameCriteria("new document");
        ISpecification sizeCriteria = new FileSearchSizeCriteria("contents".length());
        ISpecification fileSearchCriteria = nameCriteria.or(sizeCriteria);
        List<File> searchResult = new FileSearchService()
                .findFilesByCriteria(mainDirectory, fileSearchCriteria);

        System.out.println(searchResult);
    }
}
