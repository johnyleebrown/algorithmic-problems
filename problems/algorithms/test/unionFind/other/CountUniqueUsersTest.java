package unionFind.other;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountUniqueUsersTest {
    @Test
    void test1() {
        int ans = CountUniqueUsers.Solution.solve(Arrays.asList(new CountUniqueUsers.Solution.User("A", "B@"),
                new CountUniqueUsers.Solution.User("B", "C@"),
                new CountUniqueUsers.Solution.User("A", "C@")));
        assertEquals(1, ans);
    }

    @Test
    void test2() {
        int ans = CountUniqueUsers.Solution.solve(Arrays.asList(new CountUniqueUsers.Solution.User("A", "B@"),
                new CountUniqueUsers.Solution.User("A", "B@"),
                new CountUniqueUsers.Solution.User("A", "B@"),
                new CountUniqueUsers.Solution.User("A", "B@")));
        assertEquals(1, ans);
    }

    @Test
    void test3() {
        int ans = CountUniqueUsers.Solution.solve(Arrays.asList(new CountUniqueUsers.Solution.User("A", "B@"),
                new CountUniqueUsers.Solution.User("B", "C@"),
                new CountUniqueUsers.Solution.User("C", "D@"),
                new CountUniqueUsers.Solution.User("D", "E@")));
        assertEquals(4, ans);
    }

    @Test
    void test4() {
        int ans = CountUniqueUsers.Solution.solve(Arrays.asList(new CountUniqueUsers.Solution.User("A", "B@"),
                new CountUniqueUsers.Solution.User("B", "B@"),
                new CountUniqueUsers.Solution.User("C", "D@"),
                new CountUniqueUsers.Solution.User("C", "E@")));
        assertEquals(2, ans);
    }
}