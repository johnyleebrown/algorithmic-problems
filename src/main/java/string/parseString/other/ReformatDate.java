package string.parseString.other;

/**
 * 1507
 */
public class ReformatDate {

	/**
	 * SF
	 */
	public static class Solution {

		public String reformatDate(String date) {
			int n = date.length();
			String year = date.substring(n - 4, n);
			String month = date.substring(n - 8, n - 5);
			String day = date.substring(0, n - 11);
			String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec"};
			int monNum = 0;
			for (int i = 0; i < months.length; i++) {
				if (months[i].equals(month)) {
					monNum = i + 1;
				}
			}
			String monthStr = monNum < 10 ? "0" + monNum : "" + monNum;
			String dayStr = day.length() == 1 ? "0" + day : day;
			return year + "-" + monthStr + "-" + dayStr;
		}
	}
}
