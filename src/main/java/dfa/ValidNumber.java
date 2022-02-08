package dfa;

import java.util.HashMap;
import java.util.Map;

/**
 * 65
 */
public class ValidNumber {

	public static class Solution {

		public boolean isNumber(String s) {
			Map<Integer, Map<Character, Integer>> state = new HashMap<>();
			for (int i = 0; i <= 10; i++) {
				state.put(i, new HashMap<>());
			}
			state.get(0).put('-', 1);
			state.get(0).put('+', 2);
			state.get(0).put('d', 3);
			state.get(0).put('.', 4);
			state.get(0).put('.', 10);
			state.get(1).put('d', 3);
			state.get(1).put('.', 10);
			state.get(2).put('d', 3);
			state.get(2).put('.', 10);
			state.get(3).put('d', 3);
			state.get(3).put('.', 4);
			state.get(3).put('e', 5);
			state.get(4).put('d', 6);
			state.get(4).put('e', 5);
			state.get(5).put('d', 8);
			state.get(5).put('+', 7);
			state.get(5).put('-', 9);
			state.get(6).put('d', 6);
			state.get(6).put('e', 5);
			state.get(7).put('d', 8);
			state.get(8).put('d', 8);
			state.get(9).put('d', 8);
			state.get(10).put('d', 6);
			int curState = 0, nextState = 0;
			s = s.trim();
			for (char c : s.toCharArray()) {
				if (Character.isDigit(c)) {
					nextState = state.get(curState).getOrDefault('d', -1);
				} else {
					nextState = state.get(curState).getOrDefault(c, -1);
				}
				if (nextState == -1) {
					return false;
				}
				curState = nextState;
			}
			return (curState == 3 || curState == 4 || curState == 6 || curState == 8);
		}
	}
}