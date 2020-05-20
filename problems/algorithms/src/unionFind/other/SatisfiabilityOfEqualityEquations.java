package unionFind.other;

/**
 * 990
 */
public class SatisfiabilityOfEqualityEquations {
    class Solution {
        public boolean equationsPossible(String[] a) {
            UF uf1 = new UF();
            for (String s : a)
                if (s.charAt(1) != '!')
                    uf1.union(s.charAt(0), s.charAt(3));
            for (String s : a)
                if (s.charAt(1) == '!')
                    if (uf1.areConnected(s.charAt(0), s.charAt(3)))
                        return false;
            return true;
        }

        private class UF {
            int[] parents = new int[26];

            public UF() {
                for (int i = 0; i < 26; i++)
                    parents[i] = i;
            }

            public void union(char a, char b) {
                int pa = find(getint(a));
                int pb = find(getint(b));
                if (pa != pb)
                    parents[pa] = pb;
            }

            private int getint(char c) {
                return c - 'a';
            }

            private int find(int p) {
                while (parents[p] != p)
                    p = parents[parents[p]];

                return p;
            }

            public boolean areConnected(char a, char b) {
                return find(getint(a)) == find(getint(b));
            }
        }
    }
}