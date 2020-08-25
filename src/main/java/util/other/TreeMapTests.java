package util.other;
import java.util.*;
public class TreeMapTests {
    public static void main(String[] s) {
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        tm.put(5,5);
        System.out.println(tm.ceilingKey(6));
    }
}
