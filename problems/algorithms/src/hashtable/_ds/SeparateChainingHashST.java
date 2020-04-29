package hashtable._ds;

import java.util.LinkedList;
import java.util.List;

/**
 * SeparateChainingHashST
 *
 * https://github.com/kevin-wayne/algs4/blob/d17cba6db8498e451c7a0423e8a14708a4ae06b9/src/main/java/edu/princeton/cs/algs4/SeparateChainingHashST.java#L43
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private List[] st;  // array of linked-list symbol tables

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            st[i] = new LinkedList<>();
        }
    }
}
