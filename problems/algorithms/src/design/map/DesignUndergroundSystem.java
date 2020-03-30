package design.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 1396
 *
 * ======
 *
 * Task.
 *
 * Implement the class UndergroundSystem that supports three methods:
 *
 * 1. checkIn(int id, string stationName, int t)
 *
 * A customer with id card equal to id, gets in the station stationName at time
 * t. A customer can only be checked into one place at a time.
 *
 * 2. checkOut(int id, string stationName, int t)
 *
 * A customer with id card equal to id, gets out from the station stationName at
 * time t.
 *
 * 3. getAverageTime(string startStation, string endStation)
 *
 * Returns the average time to travel between the startStation and the
 * endStation. The average time is computed from all the previous traveling from
 * startStation to endStation that happened directly. Call to getAverageTime is
 * always valid.
 *
 * You can assume all calls to checkIn and checkOut methods are consistent. That
 * is, if a customer gets in at time t1 at some station, then it gets out at
 * time t2 with t2 > t1. All events happen in chronological order.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DesignUndergroundSystem {
	/**
	 * SF.
	 */
	public static class UndergroundSystem {
		private static Map<Integer, Info> start;
		private static Map<String, Map<String, double[]>> m;

		public UndergroundSystem() {
			start = new HashMap<>();
			m = new HashMap<>();
		}

		public void checkIn(int id, String stationName, int t) {
			if (!m.containsKey(stationName)) {
				m.put(stationName, new HashMap<>());
			}
			start.put(id, new Info(stationName, t));
		}

		public void checkOut(int id, String stationName, int t) {
			Info x = start.get(id);
			start.remove(id);
			if (!m.get(x.s).containsKey(stationName)) {
				m.get(x.s).put(stationName, new double[2]);
			}
			m.get(x.s).get(stationName)[0] += t - x.t;
			m.get(x.s).get(stationName)[1]++;
		}

		public double getAverageTime(String startStation, String endStation) {
			return m.get(startStation).get(endStation)[0] / m.get(startStation).get(endStation)[1];
		}

		private static class Info {
			String s;
			int t;

			Info(String s, int t) {
				this.s = s;
				this.t = t;
			}
		}
	}
}