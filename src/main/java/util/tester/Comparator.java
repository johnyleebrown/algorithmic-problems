package util.tester;

import java.util.List;

public class Comparator {
	public static <T> boolean compare(List<T> values) {
		if (values.isEmpty()) return false;
		T val = values.get(0);
		for (T v : values) {
			if (!v.equals(val)) return false;
		}
		return true;
	}
}
