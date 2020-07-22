package sort._ds.externalSort;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
public class Generator {
    public static void main(String[] s) {
        int i = 1_000_000;
        if (s.length!=0) {
            i = Integer.parseInt(s[0]);
        }
        Random r = new Random();
        BufferedWriter w=null;
        try {
            w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.txt")));
            for (int j = 0;j<i;j++) {
                int num=r.nextInt(1_000_000);
                w.write(String.valueOf(num));
                w.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}









