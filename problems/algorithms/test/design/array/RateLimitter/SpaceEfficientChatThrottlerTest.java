package design.array.RateLimitter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceEfficientChatThrottlerTest {
	@Test
	void simpleTest() {
//		SpaceEfficientChatThrottler throttler = new SpaceEfficientChatThrottler(3, 1000);
//		SingleTokenBucket throttler = new SingleTokenBucket(3,1000);
		TokenBucketFilter throttler = new TokenBucketFilter(3,1000);
		assertEquals(true, throttler.throttle(0));
		assertEquals(true, throttler.throttle(400));
		assertEquals(true, throttler.throttle(700));
		assertEquals(false, throttler.throttle(800));
		assertEquals(false, throttler.throttle(900));
//		assertEquals(false, throttler.throttle(1000));
		assertEquals(true, throttler.throttle(1100));
		assertEquals(false, throttler.throttle(1200));
		assertEquals(true, throttler.throttle(1500));
	}
}