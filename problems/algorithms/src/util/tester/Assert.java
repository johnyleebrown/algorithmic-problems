package util.tester;

public class Assert {
	public static boolean assertEquals(Object o1, Object o2, boolean show) {
		if (!show) return o1.equals(o2);
		else return assertEquals(o1, o2);
	}

	public static boolean assertEquals(Object o1, Object o2) {
		if (!o1.equals(o2)) {
//			throw new AssertionError(o1.toString() + " is not equals to " + o2.toString());
			System.out.print("- ");
			System.out.print(o1.toString() + " != " + o2.toString());
			System.out.println();
			return false;
		} else {
			System.out.print("[+] "); System.out.print(o1.toString() + " == " + o2.toString());
			System.out.println();
			return true;
		}
	}
}
