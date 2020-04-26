package string._ds.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import string._ds.RWayTrie;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RWayTrieTest {
    @BeforeEach
    void init() {
    }

    @Test
    void test_SearchWordsWithPrefix_shouldFindAllMatchingWords() {
        RWayTrie t = new RWayTrie();
        t.put("car", 0);
        t.put("cards", 0);
        t.put("cars", 0);
        t.put("ca", 0);
        t.put("c", 0);
        t.put(" ", 0);

        assertEquals(t.getWordsWithPrefix("cards"), Arrays.asList("cards"));
    }

    @Test
    void test_SearchWordsWithPrefix_shouldFindNoWords() {
        RWayTrie t = new RWayTrie();
        t.put("apple", 0);
        t.put("bike", 0);
        t.put("computer", 0);
        t.put("deer", 0);
        t.put("multiply", 0);
        t.put("mous", 0);

        assertEquals(t.getWordsWithPrefix("mouse"), Collections.emptyList());
    }
}