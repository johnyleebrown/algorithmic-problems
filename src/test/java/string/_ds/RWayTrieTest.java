package string._ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RWayTrieTest {
    @Test
    void test_SearchWordsWithPrefix_shouldFindAllMatchingWords() {
        RWayTrie t = new RWayTrie();
        List<String> input = Arrays.asList("car", "cards", "computer", "cars", "ca", "c", " ");
        for (String s : input) {
            t.put(s, 0);
        }

        assertEquals(t.getWordsWithPrefix("cards"), Arrays.asList("cards"));
    }

    @Test
    void test_SearchWordsWithPrefix_shouldFindNoWords() {
        RWayTrie t = new RWayTrie();
        List<String> input = Arrays.asList("apple", "bike", "computer", "deer", "multiply", "mous");
        for (String s : input) {
            t.put(s, 0);
        }

        assertEquals(t.getWordsWithPrefix("mouse"), Collections.emptyList());
    }

    @Test
    void test_SearchWordsWithPrefix_shouldFindAllWordsForEmptyString() {
        RWayTrie t = new RWayTrie();
        List<String> input = Arrays.asList("apple", "bike", "computer", "deer", "multiply", "mous");
        Collections.sort(input);
        for (String s : input) {
            t.put(s, 0);
        }

        List<String> ans = t.getWordsWithPrefix("");
        Collections.sort(ans);
        assertEquals(ans, input);
    }
}