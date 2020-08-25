package design.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesignHitCounter2Test {
    @Test
    void test1() {
        DesignHitCounter2.HitCounter c = new DesignHitCounter2.HitCounter();
        c.hit(1);
        c.hit(1);
        c.hit(1);
        c.hit(2);
        c.hit(2);
        c.hit(3);
        assertEquals(0, c.getHits(5, 1));
        assertEquals(0, c.getHits(5, 2));
        assertEquals(1, c.getHits(5, 3));
        assertEquals(3, c.getHits(5, 4));
        assertEquals(6, c.getHits(5, 5));
    }
}