package tree.serialization;

import util.ds.TreeNode;

import java.util.*;

/**
 * 652
 */
public class FindDuplicateSubtrees {
    public static class Solution {
        Map<Long, List<TreeNode>> hashes = new HashMap<>();
        Map<TreeNode, Long> hashCache = new HashMap<>();
        Map<String, Boolean> duplicates = new HashMap<>();
        Map<TreeNode, String> serializationCache = new HashMap<>();
        List<TreeNode> ans = new LinkedList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            hashTreeNodes(root);
            checkDuplicates();
            return ans;
        }

        private void checkDuplicates() {
            for (long k : hashes.keySet()) {
                if (hashes.get(k).size() > 1) {
                    for (TreeNode t : hashes.get(k)) {
                        String x = serialize(t);
                        if (!duplicates.getOrDefault(x, true)) {
                            ans.add(t);
                            duplicates.put(x, true);
                        }
                        duplicates.putIfAbsent(x, false);
                    }
                }
            }
        }

        private String serialize(TreeNode root) {
            if (root == null) return "#";
            if (serializationCache.containsKey(root))
                return serializationCache.get(root);
            String res = root.val + "," + serialize(root.left) + "," + serialize(root.right);
            serializationCache.put(root, res);
            return res;
        }

        void hashTreeNodes(TreeNode cur) {
            if (cur == null) return;
            hashTreeNodes(cur.left);
            long hash = getHash(cur);
            hashes.putIfAbsent(hash, new LinkedList<>());
            hashes.get(hash).add(cur);
            hashTreeNodes(cur.right);
        }

        long getHash(TreeNode x) { //djb2v2
            if (x == null) return 31;
            if (hashCache.containsKey(x)) return hashCache.get(x);
            long h = 5381;
            h = h * 33 + x.val;
            h = h * 33 + getHash(x.left);
            h = h * 33 + getHash(x.right);
            hashCache.put(x, h);
            return h;
        }
    }

    /**
     * Keeping duplicates as string in a map with counters,
     * if it is 2(duplicate), we add it to result
     */
    class Solution2 {
        private final String sep = "s";
        private Map<String, Integer> map = new HashMap<>();
        private List<TreeNode> ans = new ArrayList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            if (root == null) {
                return ans;
            }
            BitSet s = new BitSet();
            findDups(root);

            return ans;
        }

        private String findDups(TreeNode root) {
            if (root == null) {
                return "";
            } else {
                String left = findDups(root.left);
                String right = findDups(root.right);

                String res = root.val + sep + left + sep + right;

                int newVal = map.getOrDefault(res, 0) + 1;
                map.put(res, newVal);
                if (newVal == 2) ans.add(root);

                return res;
            }
        }
    }
}