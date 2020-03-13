package util.utility;

@FunctionalInterface
public interface FailingRunnable
{
	void run() throws Exception;
}
