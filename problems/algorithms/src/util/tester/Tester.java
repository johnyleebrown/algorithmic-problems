package util.tester;

import util.utils.Timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Known bugs:
 *
 * - orExpected should be the same length as expected - solve with map, where
 * key is a test number, and value - map with list of test case parameters,
 * result, expected etc.
 *
 * - orExpected should be a list, or just list of expected values; what about
 * any order switcher thou.
 *
 * TODO
 *
 * - TestCase class
 *
 * - create output queue - add first, then decide to print or not
 *
 * - add compare solutions by times(the idea is to call sol method n times and
 * get avg)
 *
 * - add debug mode - show inp and outp
 */
public class Tester {
	private final Method method;
	private final List<Object> results = new ArrayList<>();
	private final List<Object> expectations = new ArrayList<>();
	private final List<Object> orExpectations = new ArrayList<>();
	private static boolean EXPECT_ANY_ORDER_FLAG = false;
	private Object classObject;
	private final Class solutionClass;
	private final List<Double> execTimes = new ArrayList<>();

	public Tester(Object obj) {
		classObject = obj;
		solutionClass = classObject.getClass();
		method = getCorrectMethod(obj);
		setMethodAccess();
	}

	@SuppressWarnings({"unchecked"})
	private void setMethodAccess() {
		AccessController.doPrivileged((PrivilegedAction) () ->
		{
			method.setAccessible(true);
			return null;
		});
	}

	private Object getNewSolutionInstance() {
		try {
			return solutionClass.getConstructors()[0].newInstance();
		}
		catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({"unchecked"})
	private Object exec(Object... o) {
		try {
            util.utils.Timer t = new Timer().start();
            // invoke public solution method
            Object result = method.invoke(classObject, o);
            execTimes.add(t.end().getTotal());
            // re-instantiate solution object
            classObject = getNewSolutionInstance();
            return result;
        }
		catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	private Method getCorrectMethod(Object o) {
		for (Method m : o.getClass().getMethods())
			if (!m.getName().equals("main"))
				return m;
		return null;
	}

	public Tester debug() {

		return this;
	}

	public Tester add(Object... params) {
		results.add(exec(params));
		return this;
	}

	public Tester add(String[] param) {
		results.add(exec((Object) param));
		return this;
	}

	public Tester add(int[][] param) {
		results.add(exec((Object) param));
		return this;
	}

	public Tester expect(Object param) {
		expectations.add(param);
		return this;
	}

	public Tester orExpect(Object param) {
		orExpectations.add(param);
		return this;
	}

	public Tester expectAnyOrder() {
		EXPECT_ANY_ORDER_FLAG = true;
		return this;
	}

	class TestCase {
		String input;
		String output;
		List<Object> expectations;
		boolean result;
	}

	public void run() {
		//todo: get len of the longest string in results, expectations, orExpectations for the separator
		TesterOutput out = new TesterOutput(solutionClass, null);
		out.printMainSeparator();

		boolean nok = false;
		int size = getExpectationSize();

		TesterComparator comp = new TesterComparator(results, expectations, orExpectations, EXPECT_ANY_ORDER_FLAG);
		for (int i = 0; i < size; i++) {
			if (!comp.compareResults(i)) {
				nok = true;
				out.printIntermediateNok(i, results, expectations, orExpectations);
			}
			else {
//				out.printIntermediateOk(i);
			}
		}

		if (!nok) out.printOk();
		out.printTime(execTimes);
		out.printMainSeparator();
	}

	private int getExpectationSize() {
		int size = expectations.isEmpty() ? orExpectations.size() : expectations.size();
		if (size != results.size())
			throw new RuntimeException("Results size is different from expectations size.");

		return size;
	}
}