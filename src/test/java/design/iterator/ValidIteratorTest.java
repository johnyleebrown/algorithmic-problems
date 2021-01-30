package design.iterator;

import org.junit.jupiter.api.Test;
import util.ds.Cell;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidIteratorTest {
    @Test
    void randomTest() {
        List<Cell> l = new LinkedList<>();
        Random r = new Random();
        for (int k = 0; k < 200; k++) {
            l.add(new Cell(r.nextInt(20), r.nextInt(20)));
        }
//        System.out.println(l);
        ValidIterator<Cell> validIterator = new ValidIterator<>(l.iterator(), x -> x.getI() == x.getJ());
        while (validIterator.hasNext()) {
            Cell c = validIterator.next();
            assertTrue(c.getI() != c.getJ());
        }
    }

    @Test
    void goodCase() {
        List<Cell> l = new LinkedList<>();
        l.add(new Cell(0, 1));
        l.add(new Cell(1, 0));
        ValidIterator<Cell> validIterator = new ValidIterator<>(l.iterator(), x -> x.getI() == x.getJ());
        while (validIterator.hasNext()) {
            Cell c = validIterator.next();
            assertTrue(c.getI() != c.getJ());
        }
    }

    @Test
    void badCase() {
        List<Cell> l = new LinkedList<>();
        l.add(new Cell(0, 0));
        l.add(new Cell(1, 1));
        l.add(new Cell(2, 2));
        ValidIterator<Cell> validIterator = new ValidIterator<>(l.iterator(), x -> x.getI() == x.getJ());
        assertFalse(validIterator.hasNext());
        while (validIterator.hasNext()) {
            Cell c = validIterator.next();
            assertTrue(c.getI() != c.getJ());
        }
    }

    private class Cell {
        private int i, j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public String toString() {
            return "Cell{" +
                   "i=" + i +
                   ", j=" + j +
                   '}';
        }
    }
}