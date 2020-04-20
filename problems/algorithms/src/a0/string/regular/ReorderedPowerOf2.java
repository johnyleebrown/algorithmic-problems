package a0.string.regular;

/**
 * 869
 *
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 */
public class ReorderedPowerOf2 {
    class Solution1 {
        public boolean reorderedPowerOf2(int N) {
            int[] powers = new int[]{
                    1
                    ,2
                    ,4
                    ,8
                    ,16
                    ,32
                    ,64
                    ,128
                    ,256
                    ,512
                    ,1024
                    ,2048
                    ,4096
                    ,8192
                    ,16384
                    ,32768
                    ,65536
                    ,131072
                    ,262144
                    ,524288
                    ,1048576
                    ,2097152
                    ,4194304
                    ,8388608
                    ,16777216
                    ,33554432
                    ,67108864
                    ,134217728
                    ,268435456
                    ,536870912
                    ,1073741824};

            String s = String.valueOf(N);
            int n1 = s.length();
            int prev = -1;
            boolean f = false;
            for (int i : powers) {
                int n2 = String.valueOf(i).length();
                if (n2 != prev && f) break;
                if (n2 == n1) {
                    if (checkAnagrams(s, String.valueOf(i))) return true;
                    prev = n2;
                    f = true;
                }
            }
            return false;
        }

        boolean checkAnagrams(String a, String b) {
            StringBuilder s = new StringBuilder(b);
            for (char i : a.toCharArray()){
                int in = s.indexOf(String.valueOf(i));
                if(in == -1) return false;
                s.deleteCharAt(in);
            }
            return true;
        }
    }
}
