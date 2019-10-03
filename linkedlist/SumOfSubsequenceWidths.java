// 891
public class SumOfSubsequenceWidths 
{
    public int sumSubseqWidths(int[] A) 
	{
        LinkedList<Integer> ll = new LinkedList<>();
        LinkedList<Integer> llsmall = new LinkedList<>();
        int k = A.length - 1;
        for (int i = 0; i < k; i++) {
            while (!ll.isEmpty() && A[ll.getFirst()] < A[i])
                ll.removeFirst();
            while (!llsmall.isEmpty() && A[llsmall.getFirst()] > A[i])
                llsmall.removeFirst();
            llsmall.addFirst(i);
            ll.addFirst(i);
        }
        for (int i = k; i < A.length; i++) {
            if (ll.getLast() < i - k + 1)
                ll.removeLast();
            if (llsmall.getLast() < i - k + 1)
                llsmall.removeLast();
            while (!ll.isEmpty() && A[ll.getFirst()] < A[i])
                ll.removeFirst();
            while (!llsmall.isEmpty() && A[llsmall.getLast()] > A[i])
                llsmall.removeFirst();
            llsmall.addFirst(i);
            ll.addFirst(i);
        }
        return ll.getLast() + llsmall.getFirst();
    }
}

