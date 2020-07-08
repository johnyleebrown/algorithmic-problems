package patterns.Specification;

public class File extends AbstractFile {

    private FileExtension extension;
    private String contents;

    public File(String name, FileExtension extension) {
        super(name);
        this.extension = extension;
    }

    public void setContents(String contents) {
        this.contents = contents;
        size = contents.length();
    }

    @Override
    public String toString() {
        return "{ " + super.toString() + ", ext: " + extension.toString() + " }";
    }
}
