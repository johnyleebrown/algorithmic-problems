import java.util.Arrays;
import java.util.List;

public class Test2 implements Cloneable {
	public int x = 1;
	List<Integer> l = Arrays.asList(1,2);

	public void doSmth2() {
		System.out.println("2");
	}

	public native void doSmth3();

	public static void main(String[] args) {

	}

	public static void f2(int x) {
		System.out.println("");
	}
}
