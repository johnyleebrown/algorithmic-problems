package sort._ds;

import util.utils.Gen;

import java.util.Arrays;

import static util.utils.Gen.genIntArray;

/**
 * Arrays.sort uses quick sort if the array contains elements of primitive type such as long or int. Quick sort has on
 * average a runtime of O(nlogn), but the worst-case runtime is O(n2) for arrays that are almost sorted.
 *
 * So 2 solutions here to boost performance: randomize input or use wrapper class as Arrays.sort an array with objects
 * uses merge sort to sort an array of objects.
 *
 * ======
 *
 * Results of testing: Long array is sorted faster than Integer. "(long) x" conversion is faster than "new Long(x)".
 */
public class Sorts
{
	public static void qSort(int n)
	{
		int[] a = genIntArray(n, n, false);
		final long startTime = System.currentTimeMillis();
		Arrays.sort(a);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time for int[] is: " + (endTime - startTime));
	}

	public static <T> void mergeSort(int n, Class<T> cls)
	{
		T[] a = Gen.genArray(n, n);
		final long startTime = System.currentTimeMillis();
		Arrays.sort(a);
		final long endTime = System.currentTimeMillis();
		System.out.println(String.format("Total execution time for %s[] is: ", cls.toString()) + (endTime - startTime));
	}

	public static void main(String[] args)
	{
//		qSort(200_000);
//		Sort.<Integer>mergeSort(200_000, Integer.class);
//		Sort.<Long>mergeSort(200_000, Long.class);
	}
}
