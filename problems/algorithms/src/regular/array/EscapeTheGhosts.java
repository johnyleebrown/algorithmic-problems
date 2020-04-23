package regular.array;


/**
 * 789
 */
public class EscapeTheGhosts {
    public static boolean solution(int[][] ghosts, int[] target) {
        int c = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts)
            if (Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]) <= c)
                return false;
        return true;
    }
}
