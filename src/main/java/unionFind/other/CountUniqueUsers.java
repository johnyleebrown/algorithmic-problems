package unionFind.other;

import java.util.*;

/**
 * CountUniqueUsers
 *
 * ======
 *
 * Task.
 *
 * Users are counted as same if they have either same name or email. Count the number of
 * unique users.
 */
public class CountUniqueUsers {

  /**
   * From the interview - didn't understand in the beginning and just used dfs + component
   * count.
   */
  public static class Solution {

    public static int solve(List<User> users) {
      UF uf = new UF(users);
      for (User u : users) {
        uf.union(u.email, u.name);
      }
      return uf.count;
    }

    static class UF {

      Map<String, String> parents;
      int count;

      public UF(List<User> users) {
        Set<String> names = new HashSet<>(), emails = new HashSet<>();
        for (User u : users) {
          names.add(u.name);
          emails.add(u.email);
        }
        count = names.size() + emails.size();
        parents = new HashMap<>();
      }

      void union(String p, String q) {
        String parentP = find(p);
        String parentQ = find(q);
        if (parentP.equals(parentQ)) return;
        parents.put(parentP, parentQ);
        count--;
      }

      String find(String x) {
        parents.putIfAbsent(x, x);
        while (!parents.get(x).equals(x)) {
          x = parents.get(x);
        }
        return x;
      }
    }

    static class User {

      String name, email;

      public User(String name, String email) {
        this.name = name;
        this.email = email;
      }
    }
  }
}