package tree._ds.SegmentTree;

/**
 * @nochecker
 */
public interface SegmentTreeQuery {
    void increment(int i, int j, int val);

    int min(int i, int j);

    int max(int i, int j);

    int sum(int i, int j);
}
