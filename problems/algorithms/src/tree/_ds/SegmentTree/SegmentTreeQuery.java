package tree._ds.SegmentTree;

interface SegmentTreeQuery {

	void increment(int i, int j, int val);

	int min(int i, int j);
}
