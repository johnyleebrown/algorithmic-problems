package string.other;

/**
 * 657
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves,
 * judge if this robot makes a circle, which means it moves back to the original place.
 * The move sequence is represented by a string. And each move is represent by a character.
 * The valid robot moves are R (Right), L (Left), U (Up) and D (down).
 * The output should be true or false representing whether the robot makes a circle.
 */
public class JudgeRouteCircle {

    public boolean judgeCircle(String moves) {
        char[] count = new char[256];
        for (char ch : moves.toCharArray()) count[ch]++;
        return count['U'] == count['D'] && count['R'] == count['L'];
    }

    public boolean judgeCircle2(String moves) {
        int a = 0;
        int b = 0;
        for (char c : moves.toCharArray()){
            if (c == 'U') a++;
            else if (c == 'D') a--;
            else if (c == 'L') b--;
            else if (c == 'R') b++;
            else return false;
        }
        return a == 0 && b == 0;
    }
}
