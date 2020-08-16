package java;

public interface ITest1 {
	default void f() {
		System.out.println("f");
	}

	static void f2() {
		System.out.println("f2");
	}
}
