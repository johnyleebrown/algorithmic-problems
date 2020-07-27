package design.array.RateLimitter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * SimpleChatThrottler
 *
 * Use list to keep the times of requests up to size of {@code messageLimit}.
 * Return true if the number of times (for the last {@code timeInterval}) in list is less than {@code
 * messageLimit}.
 * Clear outdated times on {@link SimpleChatThrottler#throttle} call.
 *
 * We can also use "Token Bucket" algorithm or "Leaky Bucket".
 */
public class SimpleChatThrottler extends AbstractChatThrottler {

	private final List<Long> times;

	public SimpleChatThrottler(int messageLimit, long timeInterval) {
		super(messageLimit, timeInterval);
		times = new LinkedList<>();
	}

	@Override
	boolean throttle(long ts) {
		Iterator<Long> it = times.iterator();
		while (it.hasNext()) {
			long time = it.next();
			if (time < ts - timeInterval) {
				it.remove();
			} else {
				break;
			}
		}
		if (times.size() < messageLimit) {
			times.add(ts);
			return true;
		}
		return false;
	}
}
