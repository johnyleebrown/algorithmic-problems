package string.other;

import java.util.HashSet;
import java.util.Set;

/**
 * 929
 *
 * ======
 *
 * Every email consists of a local name and a domain name, separated by the @ sign.
 *
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain
 * name.
 *
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 *
 * If you add periods ('.') between some characters in the local name part of an email address, mail
 * sent there will be forwarded to the same address without dots in the local name.  For example,
 * "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that
 * this rule does not apply for domain names.)
 *
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored.
 * This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to
 * my@email.com.  (Again, this rule does not apply for domain names.)
 *
 * It is possible to use both of these rules at the same time.
 *
 * Given a list of emails, we send one email to each address in the list.  How many different
 * addresses actually receive mails?
 *
 * ======
 *
 * https://leetcode.com/problems/unique-email-addresses/
 */
public class UniqueEmailAddresses {
	public static class Solution {
		public int numUniqueEmails(String[] emails) {
			Set<String> set = new HashSet<>();
			for (String e : emails) {
				StringBuilder sb = new StringBuilder();
				int i = 0;
				while (e.charAt(i) != '+' && e.charAt(i) != '@') {
					if (e.charAt(i) != '.') {
						sb.append(e.charAt(i));
					}
					i++;
				}
				while (e.charAt(i) != '@') {
					i++;
				}
				while (i != e.length()) {
					sb.append(e.charAt(i));
					i++;
				}
				set.add(sb.toString());
			}
			return set.size();
		}
	}
}