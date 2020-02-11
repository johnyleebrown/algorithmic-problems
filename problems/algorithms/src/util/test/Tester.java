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
//	private static final String NEW_LINE = "\n";
//	private static final String TESTER_START = "[============== Tester ==============]";
	private static final String TESTER_S   = "======================================";
	private static final String TESTER_SEP = "--------------------------------------";

	private final Method method;
	private final List<Object> results;
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

	@SuppressWarnings({"unchecked"})
	public Tester(Object obj)
	{
		classObject = obj;
		method = obj.getClass().getDeclaredMethods()[0];
		AccessController.doPrivileged((PrivilegedAction) () ->
		{
			method.setAccessible(true);
			return null;
		});
//		methodParamsLength = method.getParameterTypes().length;
//		methodReturnType = method.getReturnType();
		results = new ArrayList<>();
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

	public void run(Object... expectations)
	{
		sout(TESTER_S);
		boolean nok = false;
		for (int i = 0; i < expectations.length; i++)
		{
			boolean x = results.get(i).equals(expectations[i]);
			String ind = String.valueOf(i + 1) + ". ";
			if (x)
			{
//				sout(ind + "OK");
			}
			else
			{
				nok = true;
				sou(ind + "NOK");
			}
		}
		if (!nok)
		{
			sout("Verdict: " + "Accepted");
		}
		sout(TESTER_SEP);
		sout("Execution: " + getAvgExecTimes());
		sout(TESTER_S);
	}

	private Double getAvgExecTimes()
	{
		return execTimes.stream().mapToDouble(Double::valueOf).average().orElse(Double.NaN);
	}
}

