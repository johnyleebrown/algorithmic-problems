package design.queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 379
 */
public class DesignPhoneDirectory {
	/**
	 * Set + List.
	 */
	public static class S1 {
		class PhoneDirectory {
			private Set<Integer> used;
			private List<Integer> available;
			private int max;

			public PhoneDirectory(int maxNumbers) {
				used = new HashSet<>();
				available = new LinkedList<>();
				max = maxNumbers;

				for (int i = 0; i < maxNumbers; i++) {
					available.add(i);
				}
			}

			public int get() {
				if (available.size() == 0) {
					return -1;
				}

				Integer next = available.remove(0);
				used.add(next);
				return next;
			}

			public boolean check(int number) {
				if (number >= max || number < 0) {
					return false;
				}

				return !used.contains(number);
			}

			public void release(int number) {
				if (used.contains(number)) {
					used.remove(number);
					available.add(number);
				}
			}
		}
	}

	/**
	 * Using array next to store next id that can be used.
	 */
	public static class S2 {
	}
}