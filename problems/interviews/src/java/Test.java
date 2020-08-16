package java;

public class Test extends Test2 implements ITest1, ITest2 {

	private int x;

	public Test() {
		this.x = 0;
	}

	public static void main(String[] args) {
		Test2 t = new Test();
		t.doSmth2();
		Test tt = new Test();
		tt.f();
	}

	// need to override cuz 2 interfaces have same signature
	@Override
	public void f() {

	}

	private class InnerTest {
		void fTest() {
			System.out.println(x);
		}
	}
}
