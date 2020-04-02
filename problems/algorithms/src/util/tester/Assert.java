package util.tester;

public class Assert {
	public static void assertEquals(Object o1, Object o2) {
		if (!o1.equals(o2)) {
//			throw new AssertionError(o1.toString() + " is not equals to " + o2.toString());
			System.out.println("NOK: ");
			System.out.println(o1.toString() + " != " + o2.toString());
		} else {
			System.out.print("[OK] "); System.out.println(o1.toString() + " == " + o2.toString());
		}
	}
}
