package graph.topological_sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2115
 */
public class FindAllPossibleRecipesFromGivenSupplies {

	/**
	 * $INSERT_EXPLANATION
	 */
	public static class Solution {

		// Map<String, Boolean> seen = new HashMap<>();
		public List<String> findAllRecipes(String[] recipes,
				List<List<String>> ings,
				String[] supplies) {
			Map<String, Integer> inds = new HashMap<>();
			for (int i = 0; i < recipes.length; i++) {
				inds.put(recipes[i], i);
			}
			Set<String> sup = new HashSet<>();
			for (String s : supplies) {
				sup.add(s);
			}
			Set<String> ans = new HashSet<>();
			for (int i = 0; i < recipes.length; i++) {
				if (dfs(recipes[i], ans, ings, inds, sup)) {
					ans.add(recipes[i]);
				}
			}
			return new ArrayList<>(ans);
		}

		private boolean dfs(String s, Set<String> ans, List<List<String>> ings,
				Map<String, Integer> inds, Set<String> sup) {
			// if (seen.containsKey(s)) {
			//     return seen.get(s);
			// }
			boolean ret = true;
			Integer ind = inds.get(s);
			if (ind != null) {
				for (String ing : ings.get(ind)) {
					ret &= dfs(ing, ans, ings, inds, sup);
				}
			} else {
				ret = sup.contains(s);
			}
			// seen.put(s, ret);
			return ret;
		}
	}
}
