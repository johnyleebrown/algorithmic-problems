package array.other;

/*
 * 551
 */
public class StudentAttendanceRecordI {
    class Solution {
        public boolean checkRecord(String s) {
            int countA = 0, countL = 0;
            int charA = 'A', charL = 'L';

            for (int i = 0; i < s.length(); i++) {
                if (countL > 0 && s.charAt(i) != charL) {
                    countL = 0;
                }

                if (s.charAt(i) == charA) {
                    countA++;
                } else if (s.charAt(i) == charL) {
                    countL++;
                }

                if (countA > 1 || countL > 2) {
                    return false;
                }
            }

            return true;
        }
    }


}
