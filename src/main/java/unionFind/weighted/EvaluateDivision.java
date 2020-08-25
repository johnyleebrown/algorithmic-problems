package unionFind.weighted;

import java.util.HashMap;
import java.util.List;

/**
 * 399
 *
 * ======
 *
 * Task.
 *
 * Equations are given in the format A / B = k, where A and B are variables
 * represented as strings, and k is a real number (floating point number). Given
 * some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return vector<double>.
 *
 * ======
 *
 * Source: Leetcode
 */
public class EvaluateDivision {
    /**
     * DFS find op is logarithmic, with UF and path compression it is O(1).
     */
    public static class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            UnionFind uf = new UnionFind();

            for (int i = 0; i < values.length; i++) {
                List<String> cur = equations.get(i);
                uf.union(cur.get(0), cur.get(1), values[i]);
            }

            double[] ans = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                String p = queries.get(i).get(0), q = queries.get(i).get(1);
                ans[i] = canComputeEquation(uf, p, q) ? uf.vals.get(p) / uf.vals.get(q) : -1;
            }

            return ans;
        }

        private boolean canComputeEquation(UnionFind uf, String p, String q) {
            return uf.vals.containsKey(p) && uf.vals.containsKey(q) && uf.find(p).equals(uf.find(q));
        }

        private static class UnionFind {
            HashMap<String, String> parents = new HashMap<>();
            HashMap<String, Double> vals = new HashMap<>();

            String find(String p) {
                addIfAbsent(p);

                while (!parents.get(p).equals(p)) {
                    compress(p); // path compression
                    p = parents.get(p);
                }
                return p;
            }

            void compress(String p) {
                String pParent = parents.get(p);

                // update parent of current node to parent's parent
                parents.put(p, parents.get(pParent));

                // update val
                vals.put(p, vals.get(p) * vals.get(pParent));
            }

            void union(String p, String q, double val) {
                String pParent = find(p);
                String qParent = find(q);

                if (pParent.equals(qParent)) return;

                parents.put(qParent, pParent);

                // p/q=x => q relates to p like 1/x
                double relationQToP = 1 / val;
                double relationQtoParent = vals.get(q);
                double relationPtoParent = vals.get(p);

                // parentQ relates to parentP like relationQToP * relationPtoParent / relationQtoParent
                vals.put(qParent, relationQToP * relationPtoParent / relationQtoParent);
            }

            void addIfAbsent(String x) {
                if (vals.containsKey(x)) return;
                vals.put(x, 1.0);
                parents.put(x, x);
            }
        }
    }
}