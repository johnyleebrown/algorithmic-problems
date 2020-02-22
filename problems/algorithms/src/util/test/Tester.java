package util.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import static util.test.Out.sou;
import static util.test.Out.sout;

public class Tester
{
	private static final String NEW_LINE = "\n";
	private static String TESTER_S;
	private static String TESTER_SEP;
	private static final String TEXT_VERDICT = "verdict";
	private static final int DEFAULT_LINE_LEN = 40;
	private static final String TEXT_ACC = "[ACCEPTED]";
	private static final String TEXT_EXEC = "execution";

	private final Method method;
	private final List<Object> results;
	private final List<Object> expectations;
	private final Object classObject;
//	private final Class<?> methodReturnType;
	private final List<Double> execTimes;

	public static void check(Object source, String... targets)
	{
		final String s = source.toString();
		boolean successFlag = true;
		final List<String> unmatched = new ArrayList<>();

		for (String target : targets)
		{
			if (target.equals(s))
			{
				sout("OK. " + s + " = " + target);
				break;
			}
			else
			{
				successFlag = false;
				unmatched.add(target);
			}
		}
		if (!successFlag)
		{
			sout("NOK. " + s + " does not match none of these answers: " + unmatched + ".");
		}

		sout(TESTER_SEP);
	}

	private String createDefaultLongString(char c)
	{
		return createLongString(c, DEFAULT_LINE_LEN);
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

	@SuppressWarnings({"unchecked"})
	public Tester(Object obj)
	{
		TESTER_S = createDefaultLongString('=');
		TESTER_SEP = createDefaultLongString('-');

		classObject = obj;
//		for (Method m : obj.getClass().getMethods())
//		{
//			System.out.println(m.getName());
//		}
		method = obj.getClass().getMethods()[0];
		AccessController.doPrivileged((PrivilegedAction) () ->
		{
			method.setAccessible(true);
			return null;
		});

//		methodParamsLength = method.getParameterTypes().length;
//		methodReturnType = method.getReturnType();

		results = new ArrayList<>();
		expectations = new ArrayList<>();
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
			throw new RuntimeException(e.getMessage());
		}
	}

	public void run()
	{
		sout(TESTER_S);
		boolean nok = false;
		for (int i = 0; i < expectations.size(); i++)
		{
			boolean x = results.get(i).equals(expectations.get(i));
			String ind = String.valueOf(i + 1) + ". ";
			if (x)
			{
//				sout(ind + "OK");
			}
			else
			{
				nok = true;
				sou(ind + "NOK");
				sout(NEW_LINE + TESTER_SEP);
			}
		}
		if (!nok)
		{
			String emptiness = createLongString('.', DEFAULT_LINE_LEN - TEXT_VERDICT.length() - TEXT_ACC.length());
			sout(TEXT_VERDICT + emptiness + TEXT_ACC);
//			sout(TESTER_SEP);
		}
		String avgTimes = "["+ String.format("%.2f", getAvgExecTimes())+"]";
		String e2 = createLongString('.', DEFAULT_LINE_LEN - avgTimes.length() - TEXT_EXEC.length());
		sout(TEXT_EXEC + e2 + avgTimes);
		sout(TESTER_S);
	}

	private Double getAvgExecTimes()
	{
		return execTimes.stream().mapToDouble(Double::valueOf).average().orElse(Double.NaN);
	}
}