package Medium;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 855
 *
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 */
class ExamRoom {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public int left;
    private boolean[] seats;
    private HashMap<Integer, Integer> map = new HashMap<>();
    private PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
        if (a[0] != b[0]) {
            return b[0] - a[0];
        } else {
            return a[1] - b[1];
        }
    });

    public ExamRoom(int N) {
        seats = new boolean[N];
    }

    public int seat() {
        if (left == 0) {
            seats[0] = true;
            left++;
            return 0;
        } else if (left == 1) {
            seats[seats.length - 1] = true;
            pq.add(new int[]{seats.length - 1, 0, 0, seats.length - 1});
            map.put(seats.length - 1, 1);
            left++;
            return seats.length - 1;
        } else {
            int[] match = pq.poll();
            int pos = (match[3] - match[2]) / 2;

            if (match[3] - pos == pos - match[2]) {
                int count = map.getOrDefault(pos - match[2],1);
                pq.add(new int[]{pos - match[2], count, match[2], pos});
                pq.add(new int[]{match[3] - pos, count + 1, pos, match[3]});
                map.put(pos - match[2], count + 2);
            } else {
                int count = map.getOrDefault(pos - match[2], 1);
                pq.add(new int[]{pos - match[2], count, match[2], pos});
                map.put(pos - match[2], count + 1);
                count = map.getOrDefault(match[3] - pos, 1);
                pq.add(new int[]{match[3] - pos, count, pos, match[3]});
                map.put(match[3] - pos, count + 1);
            }

            seats[pos] = true;
            left++;
            return pos;
        }
    }

    public void leave(int p) {
        if (left > 0 && p >= 0 && p < seats.length) {
            seats[p] = false;
            left--;
        }
    }

    public static void main(String[] args) {
//        ExamRoom examRoom = new ExamRoom(10);
//        examRoom.seat();
//        System.out.println(examRoom.left);
//        examRoom.seat();
//        System.out.println(examRoom.left);
//        examRoom.seat();
//        System.out.println(examRoom.left);
//        examRoom.seat();
//        System.out.println(examRoom.left);
//
//        examRoom.leave(4);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            } else {
                return b[1] - a[1];
            }
        });


    }

}