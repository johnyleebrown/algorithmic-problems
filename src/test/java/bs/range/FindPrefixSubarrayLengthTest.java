package bs.range;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FindPrefixSubarrayLengthTest {

  @Test
  void test1() {
    String[] ar = new String[]{"a", "b", "b", "ba", "baa", "bab", "badac", "baeaac", "bb",
        "bc", "de", "z"};
    String prefix = "ba";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(5, ans);
  }

  @Test
  void test2() {
    String[] ar = new String[]{"a", "b", "b", "bb", "bb", "bc", "de", "z"};
    String prefix = "ba";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(0, ans);
  }

  @Test
  void test3() {
    String[] ar = new String[]{"a", "b", "b", "ba", "bb", "bc", "de", "z"};
    String prefix = "ba";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(1, ans);
  }

  @Test
  void test4() {
    String[] ar = new String[]{"a", "b", "b", "ba", "ba"};
    String prefix = "ba";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(2, ans);
  }

  @Test
  void test5() {
    String[] ar = new String[]{"ba", "ba", "bc", "bd"};
    String prefix = "ba";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(2, ans);
  }

  @Test
  void test6() {
    String[] ar = new String[]{"ba", "bc", "bd", "da", "efrqcvg"};
    String prefix = "ba";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(1, ans);
  }

  @Test
  void test7() {
    String[] ar = new String[]{"ba", "bc", "bd", "da", "efrqcvg"};
    String prefix = "efrqcvg";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(1, ans);
  }

  @Test
  void test8() {
    String[] ar = new String[]{"efrqcvg"};
    String prefix = "efrqcvg";
    FindPrefixSubarrayLength.Solution1 s = new FindPrefixSubarrayLength.Solution1();
    int ans = s.findPrefixLength(ar, prefix);
    assertEquals(1, ans);
  }

}