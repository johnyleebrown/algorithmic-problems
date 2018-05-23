package Medium.Graph;

import java.util.List;

/**
 * 841
 *
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
 * Initially, all the rooms start locked (except for room 0).
 * You can walk back and forth between rooms freely.
 * Return true if and only if you can enter every room.
 */
public class KeysAndRooms {
    /**
     * Connected Components
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] marked = new boolean[rooms.size()];
        return helper(1, rooms.get(0), rooms, 0, marked) == rooms.size();
    }

    int helper(int count, List<Integer> room, List<List<Integer>> rooms, int roomN, boolean[] marked) {
        marked[roomN] = true;

        for (int key : room)
            if (!marked[key]) count = helper(++count, rooms.get(key), rooms, key, marked);

        return count;
    }
}
