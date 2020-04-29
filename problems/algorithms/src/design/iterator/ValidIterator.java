package design.iterator;

import java.util.Iterator;
import java.util.function.Predicate;

public class ValidIterator<E> implements Iterator<E> {
    Iterator<E> it;
    Predicate<E> p;
    E next;

    public ValidIterator(Iterator<E> it, Predicate<E> p) {
        this.it = it;
        this.p = p;
        preprocess();
    }

    private void preprocess() {
        if (!it.hasNext()) {
            next = null;
            return;
        }

        next = it.next();
        while (it.hasNext() && p.test(next)) {
            next = it.next();
        }
        if (p.test(next)) {
            next = null;
        }
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public E next() {
        E cur = next;
        preprocess();
        return cur;
    }
}
