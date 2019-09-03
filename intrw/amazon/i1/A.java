package intrw.amazon.i1;

import java.util.*;
import java.lang.*;

import static test.Out.sout;
import test.Tester;

public class A
{
	public static void solution()
	{
		return null;
	}

	public static void main(String[] args)
	{
		runTests();
	}

	private static void runTests()
	{
		Tester t = new Tester();
		
		Object source; 
		String target;
	
		source = solution(); 
		target = "";
		t.check(source, target);
	}
}

