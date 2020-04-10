package util.utility;

import java.io.PrintWriter;
import java.util.PriorityQueue;

public class Out
{
	public static void print(PrintWriter out, Object o)
	{
		out.println(o);
	}

	public static void printAr(PrintWriter out, int[] a)
	{
		for (int value : a)
		{
			out.print(value);
			out.print(" ");
		}
		out.println();
	}

	public static <E> void printPriorityQueue(PriorityQueue<E> pq) {
		PriorityQueue<E> pq2 = new PriorityQueue<>();
		System.out.println();
		while (!pq.isEmpty()) {
			E x = pq.poll();
			pq2.add(x);
			System.out.print(x.toString() + " ");
		}
		pq.addAll(pq2);
		System.out.println();
	}
}
