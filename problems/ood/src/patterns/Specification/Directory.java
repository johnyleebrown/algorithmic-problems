package patterns.Specification;

import java.util.ArrayList;
import java.util.List;

public class Directory extends AbstractFile {
    List<File> contents;

    public Directory(String name) {
        super(name);
        contents = new ArrayList<>();
    }

    public void addFile(File file) {
        contents.add(file);
    }
}
