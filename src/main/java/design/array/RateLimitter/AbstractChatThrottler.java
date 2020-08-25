package design.array.RateLimitter;

/**
 * AbstractChatThrottler
 */
public abstract class AbstractChatThrottler {
	protected final int messageLimit;
	protected final long timeInterval;

	public AbstractChatThrottler(int messageLimit, long timeInterval) {
		this.messageLimit = messageLimit;
		this.timeInterval = timeInterval;
	}

	abstract boolean throttle(long ts);
}
