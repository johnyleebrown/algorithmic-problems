package recursion.other;

public class PowXN
{
	// regulat solution produces SO
	// multiplying halves of n
	// extra juice with n less than zero
	class Solution {
		public double myPow(double x, int n) 
		{
			if (n == 0) return 1;
			double half = myPow(x, n / 2);
			if (Math.abs(n) % 2 == 1) return n < 0 ? 1 / x * half * half : x * half * half;
			return half * half;
		}
	}
/*
2.00000
10
0.00001
2147483647
*/
}
