package util.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

import static util.test.Out.sou;
import static util.test.Out.sout;

public class Tester
{
	private static String TESTER_S;
	private static String TESTER_SEP;
	private static int DEFAULT_LINE_LEN = 30;
	private static final String TEXT_ACC = "[ACCEPTED]";
	private static boolean DEFAULT_FEATURE_FLAG = false;

	private final Method method;
	private final List<Object> results;
	private final List<Object> expectations;
	private final List<Object> orExpectations;
	private final Object classObject;
	private final List<Double> execTimes;

	private static boolean EXPECT_ANY_ORDER_FLAG = false;

	private String createDefaultLongString(char c)
	{
		return createLongString(c, getLineLen());
	}

	private String createLongString(char c, int n)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
		{
			sb.append(c);
		}
		return sb.toString();
	}

	private int getLineLen()
	{
		if (DEFAULT_FEATURE_FLAG)
		{
			return DEFAULT_LINE_LEN;
		}

		int classNameLen = calculateLineLenFromClassName(classObject.getClass());
		return classNameLen > TEXT_ACC.length() ? classNameLen : DEFAULT_LINE_LEN;
	}

	private int calculateLineLenFromClassName(Class c)
	{
		String enclosingClass = c.getEnclosingClass() != null
				? c.getEnclosingClass().getSimpleName()
				: c.getSimpleName();
		int extra = 5; // depends on the java run alias
		return extra + enclosingClass.length();
	}

	/**
	 * Custom methods that are public always go first. If we have an inner class
	 * 'Solution'(most of the times) - then we take first. Otherwise we iterate
	 * to find the right one.
	 */
	private Method getCorrectMethod(Object o)
	{
		for (Method m : o.getClass().getMethods())
			if (!m.getName().equals("main"))
				return m;
		return null;
	}

	@SuppressWarnings({"unchecked"})
	public Tester(Object obj)
	{
		classObject = obj;
		TESTER_S = createDefaultLongString('=');
		TESTER_SEP = createDefaultLongString('-');

		method = getCorrectMethod(obj);
		AccessController.doPrivileged((PrivilegedAction) () ->
		{
			method.setAccessible(true);
			return null;
		});

		results = new ArrayList<>();
		expectations = new ArrayList<>();
		orExpectations = new ArrayList<>();
		execTimes = new ArrayList<>();
	}

	public Tester init(Object[]... params)
	{
		for (Object[] param : params)
		{
			results.add(exec(param));
		}

		return this;
	}

	public Tester add(Object... params)
	{
		results.add(exec(params));
		return this;
	}

	public Tester add(String[] param)
	{
		results.add(exec((Object) param));
		return this;
	}

	public Tester expect(Object param)
	{
		expectations.add(param);
		return this;
	}

	public Tester orExpect(Object param)
	{
		orExpectations.add(param);
		return this;
	}

	private boolean areAnagrams(int[] a, int[] b)
	{
		Map<Integer, Integer> c = new HashMap<>();
		for (int i : a)
			c.put(i, c.getOrDefault(i, 0) + 1);
		for (int i : b)
			c.put(i, c.getOrDefault(i, 0) - 1);
		for (int i : c.keySet())
			if (c.get(i) != 0)
				return false;
		return true;
	}

	private Object exec(Object... o)
	{
		try
		{
			Timer t = new Timer().start();
			Object result = method.invoke(classObject, o);

			execTimes.add(t.end().getTotal());
			return result;
		}
		catch (IllegalAccessException | InvocationTargetException e)
		{
			throw new RuntimeException(e.getCause());
		}
	}

	private boolean isIntArr(Object o)
	{
		return o.getClass().getSimpleName().equals("int[]");
	}

	private boolean isArr(Object o)
	{
		return o.getClass().getSimpleName().contains("[]");
	}

	private boolean compareResults(int i)
	{
		if (EXPECT_ANY_ORDER_FLAG && isIntArr(results.get(i)))
		{
			boolean ans1 = areAnagrams((int[]) results.get(i), (int[]) expectations.get(i));
			if (!orExpectations.isEmpty() && !ans1)
			{
				return areAnagrams((int[]) results.get(i), (int[]) orExpectations.get(i)); // ans2
			}
			return ans1;
		}

		if (isIntArr(results.get(i)) && isIntArr(expectations.get(i)))
		{
			boolean ans1 = Arrays.equals((int[]) results.get(i), (int[]) expectations.get(i));
			if (!orExpectations.isEmpty() && !ans1)
			{
				return Arrays.equals((int[]) results.get(i), (int[]) orExpectations.get(i)); // ans2
			}
			return ans1;
		}

		boolean ans1 = results.get(i).equals(expectations.get(i));
		if (!orExpectations.isEmpty() && !ans1)
		{
			return results.get(i).equals(orExpectations.get(i));
		}
		return ans1;
	}

	public void run()
	{
		sout(TESTER_S);
		boolean nok = false;
		for (int i = 0; i < expectations.size(); i++)
		{
			if (!compareResults(i))
			{
				nok = true;
				printIntermediateNok(i);
			}
			else
			{
				printIntermediateOk(i);
			}
		}
		if (!nok) printOk();
		printTime();
		sout(TESTER_S);
	}

	// todo - record to list ,then if all ok, don't print ok's
	private void printIntermediateOk(int i)
	{
		String NUM = String.valueOf(i + 1);
		String OK = "OK";
		String E = createLongString('.', getLineLen() - NUM.length() - OK.length());
		sout(NUM + E + OK);
		sout(TESTER_SEP);
	}

	private void printIntermediateNok(int i)
	{
		String NUM = String.valueOf(i + 1);
		String NOK = "NOK";
		String E = createLongString('.', getLineLen() - NUM.length() - NOK.length());
		sout(NUM + E + NOK);
		sou("got: ");
		print(results.get(i));
		sout();
		sou("expected: ");
		print(expectations.get(i));
		if (!orExpectations.isEmpty())
		{
			sou("or: ");
			print(orExpectations.get(i));
		}
		sout();
		sout(TESTER_SEP);
	}

	private void print(Object o)
	{
		if (isArr(o))
			System.out.print(Arrays.toString(new Object[]{o}));
		else
			System.out.println(o);
	}

	private void printOk()
	{
		String emptiness = createLongString('.', getLineLen() - TEXT_ACC.length());
		sout(emptiness + TEXT_ACC);
	}

	private void printTime()
	{
		String avgTimes = "[" + String.format("%.2f", getAvgExecTimes()) + "]";
		String e2 = createLongString('.', getLineLen() - avgTimes.length());
		sout(e2 + avgTimes);
	}

	private Double getAvgExecTimes()
	{
		return execTimes.stream().mapToDouble(Double::valueOf).average().orElse(Double.NaN);
	}

	public Tester expectAnyOrder()
	{
		EXPECT_ANY_ORDER_FLAG = true;
		return this;
	}
}