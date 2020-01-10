// 346
class MovingAverage 
{
    private Queue<Integer> q;
    private double sum = 0;
    private int size;

    public MovingAverage(int s) 
    {
        q = new LinkedList();
        size = s;
    }
    
    public double next(int val) 
    {
        if (q.size() == size)
        {
            sum = sum - q.poll();
        }
        
        q.offer(val);
        sum += val;
        return sum / q.size();
    }
}

