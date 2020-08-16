package java;

public interface ITest2 {
	default void f() {
		System.out.println("ITest2");
	}

	static void f2() {
		System.out.println("f2");
	}
}
