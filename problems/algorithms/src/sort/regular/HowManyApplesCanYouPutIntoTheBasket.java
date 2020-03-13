package sort.regular;

import java.util.Arrays;

/**
 * 1196
 */
public class HowManyApplesCanYouPutIntoTheBasket
{
    class Solution
    {
        public int maxNumberOfApples(int[] arr)
        {
            Arrays.sort(arr);
            int res = 0, sum = 0;

            for (int i = 0; i < arr.length && sum <= 5000; i++)
            {
                sum += arr[i];

                if (sum <= 5000)
                {
                    res++;
                }
            }

            return res;
        }
    }
}