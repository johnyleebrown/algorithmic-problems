package design.iterator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTreeIteratorTest {
    @Test
    void backAndForthTest() {
        BinarySearchTreeIterator.TreeNodeCustom t1 = new BinarySearchTreeIterator.TreeNodeCustom(7);
        t1.left = new BinarySearchTreeIterator.TreeNodeCustom(3);
        t1.right = new BinarySearchTreeIterator.TreeNodeCustom(15);
        t1.right.left = new BinarySearchTreeIterator.TreeNodeCustom(9);
        t1.right.right = new BinarySearchTreeIterator.TreeNodeCustom(20);
        BinarySearchTreeIterator.Solution2.BSTIterator it = new BinarySearchTreeIterator.Solution2.BSTIterator(t1);

        assertEquals(it.hasNext(), true);
        assertEquals(it.next(), 3);
        assertEquals(it.next(), 7);
        assertEquals(it.next(), 9);
        assertEquals(it.next(), 15);
        assertEquals(it.next(), 20);
        assertEquals(it.hasPrevious(), true);
        assertEquals(it.previous(), 15);
        assertEquals(it.previous(), 9);
        assertEquals(it.previous(), 7);
        assertEquals(it.previous(), 3);
        assertEquals(it.hasPrevious(), false);
        assertEquals(it.hasNext(), true);
        assertEquals(it.next(), 7);
        assertEquals(it.next(), 9);
        assertEquals(it.next(), 15);
        assertEquals(it.next(), 20);
        assertEquals(it.hasNext(), false);
    }
}