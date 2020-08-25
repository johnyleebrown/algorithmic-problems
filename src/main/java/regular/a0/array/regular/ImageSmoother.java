package a0.array.regular;

/**
 * 661
 * Given a 2D integer matrix M representing the gray scale of an image,
 * you need to design a smoother to make the gray scale of each cell
 * becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself.
 * If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 */
public class ImageSmoother {

    public int[][] imageSmoother(int[][] M) {
        int[][] temp = new int[M.length][];
        for (int i = 0 ; i < M.length ; i++){
            temp[i] = new int[M[i].length];
            for (int j = 0 ; j < M[i].length ; j++){
                int n = 0;
                int d = 0;
                for (int k = i - 1 ; k <= i + 1 ; k++){
                    if (k >= 0 && k <= M.length - 1){
                        if (j > 0){
                            n += M[k][j-1];
                            d++;
                        }
                        {
                            n += M[k][j];
                            d++;
                        }
                        if (j < M[i].length - 1){
                            n += M[k][j+1];
                            d++;
                        }
                    }

                }
                temp[i][j] = d == 0 ? 0 : n/d;
            }
        }
        return temp;
    }
}
