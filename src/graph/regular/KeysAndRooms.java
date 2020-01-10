package Medium.Graph;

import java.util.List;

// 841
public class KeysAndRooms 
{
    public boolean canVisitAllRooms(List<List<Integer>> rooms) 
	{
        boolean[] marked = new boolean[rooms.size()];
        return helper(1, rooms.get(0), rooms, 0, marked) == rooms.size();
    }

    int helper(int count, List<Integer> room, List<List<Integer>> rooms, 
			int roomN, boolean[] marked) 
	{
        marked[roomN] = true;

        for (int key : room)
            if (!marked[key]) 
				count = helper(++count, rooms.get(key), rooms, key, marked);

        return count;
    }
}
