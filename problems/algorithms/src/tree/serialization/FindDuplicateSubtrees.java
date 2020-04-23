package tree.serialization;

import util.ds.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652
 */
public class FindDuplicateSubtrees {
    /**
     * keeping duplicates as string in a map with counters,
     * if it is 2(duplicate), we add it to result
     */
    class Solution {
        private final String sep = "s";
        private Map<String, Integer> map = new HashMap<>();
        private List<TreeNode> ans = new ArrayList<>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            if (root == null) {
                return ans;
            }

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

