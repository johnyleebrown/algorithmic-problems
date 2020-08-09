public class Test extends Test2 implements ITest1, ITest2 {

	private static boolean x;

	static {
		System.out.println("as");
	}

	public static void f2(int x) {
		System.out.println("asas");
	}

	public static void f2(int x, String y) {

	}

	protected void doSmth4() {

	}

	protected String doSmth4(int x) {
		return "";
	}

	public void doSmth2() {
		System.out.println("1");
	}

	@Override
	public void f() {
		ITest2.super.f();
		System.out.println("here");
	}

	public static void main(String[] args) {
		Test2 t = new Test();
		t.doSmth2();
		Test tt = new Test();
		tt.f();
	}
}
