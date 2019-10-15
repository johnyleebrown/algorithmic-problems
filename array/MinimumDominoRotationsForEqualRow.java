/*
 * 1007
 * Company:Google
 *
 * We have at most 6 types of values. For each row loop check all the values.
 * Compare rotations ot find the min, exit from loop if > min or if the number
 * we are checking doesn't exist in opponent array.
 * O(n). O(1).
 */
class Solution
{
    public int minDominoRotations(int[] A, int[] B)
    {
        int n = A.length;
        int c = Integer.MAX_VALUE;

        for (int k = 1; k <= 6; k++)
        {
            int local = 0;
            int local2 = 0;

            for (int i = 0; i < n; i++)
            {
                if (A[i] != k && B[i] != k)
                {
                    local = -1;
                    local2 = -1;
                    break;
                }

                if (A[i] != k && B[i] == k)
                {
                    local++;
                }

                if (B[i] != k && A[i] == k)
                {
                    local2++;
                }
            }

            if (local != -1)
            {
                c = Math.min(local, c);
            }

            if (local2 != -1)
            {
                c = Math.min(local2, c);
            }
        }

        return c == Integer.MAX_VALUE ? -1 : c;
    }
}

