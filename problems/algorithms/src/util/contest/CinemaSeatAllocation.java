package util.contest;

import util.tester.Tester;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * $INSERT_PROBLEM_NUMBER
 *
 * ======
 *
 * Task.
 *
 * $INSERT_TASK
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CinemaSeatAllocation
{
	class Solution {
		public int maxNumberOfFamilies(int n, int[][] reservedSeats)
		{
			int res = 0;
			for (int i = 1; i <= n; i++)
			{
				int canSeat = 2;
				Set<Integer> o1 = new HashSet<>(Arrays.asList(2, 3, 4, 5)), o2 = new HashSet<>(Arrays.asList(4, 5, 6, 7)),o3 = new HashSet<>(Arrays.asList(6, 7, 8, 9));;
				boolean o = true, t = true, tt = true;
				for (int[] ttt : reservedSeats)
				{
					int x = ttt[0], y = ttt[1];
					if (i == x)
					{
						if (o1.remove(y) && o)
						{
							o = false;
							canSeat--;
						}
						if (o2.remove(y) && t)
						{
							t = false;
							canSeat--;
						}
						if (o3.remove(y) && tt)
						{
							tt = false;
							canSeat--;
						}
					}
				}
				res += (Math.max(canSeat, 0));
			}
			return res;
		}
		int count(boolean[] a) {
			int res = 0;
			boolean b = !a[1] && !a[2] && !a[3] && !a[4];
			boolean c = !a[5] && !a[6] && !a[7] && !a[8];
			boolean d = !a[3] && !a[4] && !a[5] && !a[6];
			if (b && c) {
				res += 2;
			}
			if (d && (!b&&!c)){
				res++;
			}
			if (b && !c){
				res++;
			}
			if (!b && c){
				res++;
			}
			return res;
		}
	}
}
/*
import "fmt"

	func maxNumberOfFamilies(n int, reservedSeats [][]int) int {

		a := make([][10]bool,n)
		res := 0
		for i := 0 ; i < len(reservedSeats) ; i++{
			x := reservedSeats[i][0]
			y := reservedSeats[i][1]
			a[x-1][y-1] = true
		}
		for i := 0 ;i < len(a) ; i++{
			res += count(&a[i])
		}
		return res
	}
	func count(a *[10]bool)int{
	res := 0
	temp1 := a[1] !=true && a[2]!=true && a[3]!=true && a[4]!=true
	temp3 := a[5] !=true && a[6]!=true && a[7]!=true && a[8]!=true
	temp2 := a[3] !=true && a[4]!=true && a[5]!=true && a[6]!=true
	if temp1 && temp3 {
		res += 2
	}
	if temp2 && (!temp1&&!temp3){
		res ++
	}
	if temp1 && !temp3{
		res ++
	}
	if !temp1 && temp3{
		res ++
	}

	return res

}

 */