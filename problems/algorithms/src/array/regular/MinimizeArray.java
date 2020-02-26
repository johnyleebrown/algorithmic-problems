package array.regular;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//import static test.Out.sout;

public class MinimizeArray
{
	public static void main(String[] args)
	{
		int[] arrayOfIntegers = new int[]{100, 5, 70, 2};
		minimize(arrayOfIntegers);
		System.out.println(Arrays.toString(arrayOfIntegers));
	}

	private static void minimize(int[] nums)
	{
		Map<Integer, Integer> set = new HashMap<>();
		int[] temp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) temp[i] = nums[i];
		Arrays.sort(temp);
		for (int i = 0; i < nums.length; i++) set.put(temp[i], i + 1);
		for (int i = 0; i < nums.length; i++) nums[i] = set.get(temp[i]);
	}

}
		
