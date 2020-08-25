package hashtable.rollingHash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistinctEchoSubstringsTest {
    @Test
    void test1() {
        DistinctEchoSubstrings.Solution3 s = new DistinctEchoSubstrings.Solution3();
        assertEquals(2, s.distinctEchoSubstrings("leetcodeleetcode"));
    }

    @Test
    void test2() {
        DistinctEchoSubstrings.Solution3 s = new DistinctEchoSubstrings.Solution3();
        assertEquals(3, s.distinctEchoSubstrings("abcabcabc"));
    }

    @Test
    void test3() {
        DistinctEchoSubstrings.Solution3 s = new DistinctEchoSubstrings.Solution3();
        assertEquals(101, s.distinctEchoSubstrings("fbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqurotspohiuwhblnratkfbgwgqvdsbnukcpxlpifuhbvtdxhhhqur"));
    }

    @Test
    void test4() {
        DistinctEchoSubstrings.Solution3 s = new DistinctEchoSubstrings.Solution3();
        assertEquals(5, s.distinctEchoSubstrings("aaaaaaaaaa"));
    }

    @Test
    void test5() {
        DistinctEchoSubstrings.Solution3 s = new DistinctEchoSubstrings.Solution3();
        assertEquals(2, s.distinctEchoSubstrings("ibnlogcpqhuabiqtjmcpmjzzgxslnekyaiovaidcbbblnywdchfcefzbfoczkoxpypszpfptirigscjhduhysnhydtirynowynoc"));
    }
}