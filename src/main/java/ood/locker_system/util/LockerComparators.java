package ood.locker_system.util;

import java.util.Comparator;
import ood.locker_system.model.Locker;

public class LockerComparators {

	public static Comparator<Locker> DEFAULT_COMPARATOR = getSizeComparator();

	public static Comparator<Locker> getSizeComparator() {
		return new SizeComparator();
	}

	private static class SizeComparator implements Comparator<Locker> {

		@Override
		public int compare(Locker o1, Locker o2) {
			return o1.getSize().getOrderId() - o2.getSize().getOrderId();
		}
	}
}
