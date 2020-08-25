package unionFind._ds;

import org.junit.jupiter.api.Test;
import util.utils.reader.InputReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.utils.Other.getReader;

class UnionFindImplTest {
    @Test
    void test1() throws IOException {
        String[] inputs = new String[]{"tinyUF.txt", "mediumUF.txt", "largeUF.txt"};
        int[] ans = new int[]{2, 66, 79956};

        for (int i = 0; i < inputs.length; i++) {
            InputReader r = new InputReader(getReader(this.getClass(), inputs[i]));

            int n = r.nextInt();
            UnionFindImpl uf = new UnionFindImpl(n);

            for (int j = 0; j < n; j++) {
                uf.union(r.nextInt(), r.nextInt());
            }

            assertEquals(ans[i], uf.count());
        }
    }
}