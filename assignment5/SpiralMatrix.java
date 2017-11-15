import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        int m = matrix.length; //number of rows
        int n = matrix[1].length; //number of columns
        int ltr = 0; //row index of left top elements
        int ltc = 0; //column index of left top elements
        int rbr = m - 1; //row index of right bottom elements
        int rbc = n - 1; //column index of right bottom elements
        while (ltr <= rbr && ltc <= rbc) {
            if (ltr == rbr && ltc == rbc) {
                list.add(matrix[ltr][ltc]);
                break;
            }
            for (int i = ltc; i <= rbc - 1; i++) {
                list.add(matrix[ltr][i]);
            }
            for (int i = ltr; i <= rbr - 1; i++) {
                list.add(matrix[i][rbc]);
            }
            for (int i = rbc; i >= ltc + 1; i--) {
                list.add(matrix[rbr][i]);
            }
            for (int i = rbr; i >= ltr + 1; i--) {
                list.add(matrix[i][ltc]);
            }
            ltr++;
            ltc++;
            rbr--;
            rbc--;
        }
        return list;
    }

    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();
        System.out.println(
                sm.spiralOrder(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } }));
        System.out.println(sm.spiralOrder(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }));
        System.out.println(sm.spiralOrder(new int[][] { { 1, 2, 3 }, { 5, 6, 7 }, { 9, 10, 11 } }));
        System.out.println(sm.spiralOrder(new int[][] { { 1, 2, 3 }, { 6, 7, 8 }, { 10, 11, 12 }, { 14, 15, 16 } }));
    }
} 
