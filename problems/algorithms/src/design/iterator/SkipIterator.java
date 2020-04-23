package design.iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Google_Interview_9
 */
public class SkipIterator implements Iterator<Integer> {
    // map to count collection integers
    private Map<Integer, Integer> skipItems;
    private Iterator<Integer> it;
    private Integer nextElement;

    public SkipIterator(Iterator<Integer> it) {
        skipItems = new HashMap<>();
        this.it = it;

        prefill();
    }

    private void prefill() {
        nextElement = null;
        while (nextElement == null && it.hasNext()) {
            Integer element = it.next();
            if (skipItems.containsKey(element) && skipItems.get(element) != 0) {
                skipItems.put(element, skipItems.get(element) - 1);
            } else {
                nextElement = element;
            }
        }
    }

    public Integer next() {
        if (!hasNext()) throw new RuntimeException();

        Integer element = nextElement;
        prefill();

        return element;
    }

    public boolean hasNext() {
        return nextElement != null;
    }

    public void skip(int x) {
        if (!hasNext()) throw new RuntimeException();

        // since we are prefilling next element
        // we need to check if our prepared element is not the next next
        if (nextElement == x) {
            prefill();
        } else {
            skipItems.put(x, skipItems.getOrDefault(x, 0) + 1);
        }
    }
}

