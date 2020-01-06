package medium.array;

// 799
public class ChampagneTower 
{
    public static double champagneTower(int poured, int query_row, int query_glass)
	{
        double[][] res = new double[100][100];
        res[0][0] = poured;

        // iterate through all rows and all glasses in each row
        for (int i = 0 ; i < 100 ; i++)
		{
            for (int j = 0 ; j <= i ; j++) 
			{
                if (res[i][j] > 1) 
				{
                    double amountLeft = res[i][j] - 1;
                    if (i != 99) 
					{
                        res[i + 1][j] += amountLeft / 2;
                        res[i + 1][j + 1] += amountLeft / 2;
                    }

                    res[i][j] = 1;
                }
            }
        }

        return res[query_row][query_glass];
    }
}

