package patterns.Specification;

public abstract class AbstractFile {
    private final String name;
    protected long size;

    public AbstractFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "name='" + name + "', size='" + size + "'";
    }
}
