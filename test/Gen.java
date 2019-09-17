package test;

import java.util.Random;

// generator
public class Gen
{
	public static int[][] gen2DArray(int n, int m)
	{
		final Random r = new Random();
		int[][] a = new int[n][m];
		int max = n*m;
		for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) a[i][j] = r.nextInt(max);
		return a;
	}
}

