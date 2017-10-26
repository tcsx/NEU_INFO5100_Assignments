import java.util.ArrayList;
import java.util.Arrays;

public class MidTerm {
    /**
     * Question 1 of 5.
     */
    public int[] reverseEvenIndices(int[] nums) {
        if (nums == null) {
            return null;
        }
        int len = nums.length;
        int[] reversed = new int[len];
        for (int i = 1; i < len; i += 2) {
            reversed[i] = nums[i];
        }
        int left = 0;
        int right = ((len - 1) % 2 == 0) ? len - 1 : len - 2;
        while (right >= left) {
            reversed[left] = nums[right];
            reversed[right] = nums[left];
            left += 2;
            right -= 2;
        }
        return reversed;
    }

    /**
     * Question 2 of 5.
     */
    public int arrangeCoins(int n) {
        int sum = 0;
        int i = 1;
        while (sum <= n) {
            sum += i;
            i++;
        }
        return (i - 2);
    }

    /**
     * Question 3 of 5.
     */
    public int minMoves(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        int move = 0;
        while (!allEqual(arr)) {
            int maxId = maxIndex(arr);
            for (int i = 0; i < arr.length; i++) {
                if (i != maxId)
                    arr[i]++;
            }
            move++;
        }
        return move;
    }

    /**
     * Help method of question 3.
     */
    public boolean allEqual(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[0])
                return false;
        }
        return true;
    }

    /**
     * Find the index of max value in an array. Help method of question 3.
     */
    public int maxIndex(int[] arr) {
        int max = arr[0];
        int maxId = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxId = i;
            }
        }
        return maxId;
    }

    /**
     * Question 4 of 5.
     * @param m number of faces
     * @param n number of dice
     * @param x sum
     * @return number of possible ways
     */
    public int countNumberOfPossibleWays(int m, int n, int x) {
        int ways = 0;
        if (n == 1) {
            if (x >= 1 && x <= m) {
                return 1;
            } else {
                return 0;
            }
        }
        for (int i = 1; i <= m; i++) {
            ways += countNumberOfPossibleWays(m, n - 1, x - i);
        }
        return ways;
    }

    public static void main(String[] args) {
        MidTerm m = new MidTerm();
        System.out.println("------------------q3------------------");
        System.out.println(m.minMoves(new int[] { 1, 2, 3 }));
        System.out.println(m.minMoves(new int[] { 1, 3, 3 }));
        System.out.println(m.minMoves(new int[] { 1, 2, 3, 4 }));
        System.out.println(m.minMoves(new int[] { 1 }));
        System.out.println(m.minMoves(new int[] { 1, 1 }));
        System.out.println("------------------q4------------------");
        System.out.println(m.countNumberOfPossibleWays(1, 2, 2));
        System.out.println(m.countNumberOfPossibleWays(1, 2, 1));
        System.out.println(m.countNumberOfPossibleWays(1, 2, 3));
        System.out.println(m.countNumberOfPossibleWays(4, 3, 6));
        System.out.println("------------------q5------------------");
        Solution s = new Solution();
        int[][] maze = new int[][] { { 1, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1 } };
        int[][] maze2 = new int[][] { { 1, 1, 1, 1 }, { 1, 0, 1, 1 }, { 1, 0, 0, 0 }, { 1, 1, 1, 1 } };
        System.out.println(s.findPath(maze));
        System.out.println(s.findPath(maze2));
        System.out.println(s.findPath(new int[][]{{1}}));
        System.out.println(s.findPath(new int[][]{{0}}));
        System.out.println(s.findPath(new int[][]{{0,1},{1,1}}));
        System.out.println(s.findPath(new int[][]{{1,1},{1,1}}));
    }
}

class Cell {
    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}

/**
* Question 5 of 5.
*/
class Solution {

    public ArrayList<Cell> findPath(int[][] maze) {
        return findPath(maze, new Cell(0, 0));
    }

    public ArrayList<Cell> findPath(int[][] maze, Cell c) {
        ArrayList<Cell> path = new ArrayList<Cell>();
        int n = maze.length;
        int x = c.x;
        int y = c.y;
        if(maze[x][y] == 0){
            return path;
        }
        if (x == n - 1 && y == n - 1) {
            if(maze[x][y] == 1)
                path.add(c);
            return path;
        }
        maze[x][y] = 0;
        if (y < n - 1 && maze[x][y + 1] != 0) {
            ArrayList<Cell> nextPath = findPath(maze, new Cell(x, y + 1));
            if (!nextPath.isEmpty()) {
                path.add(c);
                path.addAll(nextPath);
            }
        }
        if (path.isEmpty() && x < n - 1 && maze[x + 1][y] != 0) {
            ArrayList<Cell> nextPath = findPath(maze, new Cell(x + 1, y));
            if (!nextPath.isEmpty()) {
                path.add(c);
                path.addAll(nextPath);
            }
        }
        if (path.isEmpty() && y > 0 && maze[x][y - 1] != 0) {
            ArrayList<Cell> nextPath = findPath(maze, new Cell(x, y - 1));
            if (!nextPath.isEmpty()) {
                path.add(c);
                path.addAll(nextPath);
            }
        }
        if (path.isEmpty() && x > 0 && maze[x - 1][y] != 0) {
            ArrayList<Cell> nextPath = findPath(maze, new Cell(x - 1, y));
            if (!nextPath.isEmpty()) {
                path.add(c);
                path.addAll(nextPath);
            }
        }
        return path;
    }
}
