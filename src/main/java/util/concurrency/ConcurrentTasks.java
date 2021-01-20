package util.concurrency;

import java.util.List;
import java.util.concurrent.Future;

public class ConcurrentTasks {
	public static void waitUntilDone(List<Future<?>> futures) {
		boolean allDone = false;
		while (!allDone) {
			System.out.println("waiting..");

			// A) Await all runnables to be done (blocking)
			for (Future<?> future : futures) {
				try {
					// get will block until the future is done
					future.get();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// B) Check if all runnables are done (non-blocking)
			for (Future<?> future : futures) {
				if (!future.isDone()) {
					allDone = false;
					break;
				} else allDone = true;
			}
		}
	}
}
