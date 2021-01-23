package math.bits;

import java.util.*;

/**
 * 1452. People Whose List of Favorite Companies Is Not a Subset of Another List
 *
 * ======
 *
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies
 * for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset of any other list
 * of favorites companies. You must return the indices in increasing order.
 *
 * ======
 *
 * https://leetcode.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list/
 */
public class PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList {
	/**
	 * The idea is get a set of people for each company.
	 * Then for each person determine an intersection of sets of people where each company is used.
	 * Company1=[0,1,2]
	 * Company2=[1,3]
	 * Intersection=[1]
	 * If the resulting set has > 1 set bits it means that there a more than 1 place that
	 * contains this set of companies.
	 */
	public static class Solution {
		public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
			Map<String, BitSet> companyToPeople = new HashMap<>();
			for (int i = 0; i < favoriteCompanies.size(); i++) {
				for (String company : favoriteCompanies.get(i)) {
					companyToPeople.putIfAbsent(company, new BitSet());
					companyToPeople.get(company).set(i);
				}
			}
			List<Integer> ans = new ArrayList<>();
			for (int i = 0; i < favoriteCompanies.size(); i++) {
				BitSet result = new BitSet();
				// set all 1s
				result.set(0, favoriteCompanies.size());
				for (String company : favoriteCompanies.get(i)) {
					// AND with every set of people
					result.and(companyToPeople.get(company));
				}
				if (result.cardinality() == 1) {
					ans.add(i);
				}
			}
			return ans;
		}
	}
}