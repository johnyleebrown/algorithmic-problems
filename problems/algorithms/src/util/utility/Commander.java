package util.utility;

import util.utility.reader.InputReader;

public class Commander {
	public static void main(String[] a) {
		InputReader in = new InputReader(System.in);
		while (true) {
			int x = in.nextInt();
			int y = in.nextInt();
			if (x == 1) {
				System.out.println("DO_THING_1");
				// DO_THING_1
			}
			else if (x == 2) {
				System.out.println("DO_THING_2");
				// DO_THING_2
			}
			else if (x == 3) {
				System.out.println("DO_THING_3");
				// DO_THING_3
			}
			else if (x == 4) {
				System.out.println("DO_THING_4");
				// DO_THING_4
			}
			else return;
		}
	}
}
