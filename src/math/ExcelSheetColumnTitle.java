package math;

/**
 * 168
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 1 -> A ; 2 -> B ; 3 -> C ... 26 -> Z ; 27 -> AA ; 28 -> AB
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder str = new StringBuilder();
        while (n > 0){
            n--;
            str.insert(0, (char) ('A' + n % 26 ));
            n /= 26 ;
        }
        return str.toString();
    }
}
