import java.io.*;

/**
 * Contains solutions to question 1&2.
 */
public class Question1And2 {
    /**
     * 1.Create your own MyIndexOutOfBoundException Class. 
     */
    public static class MyIndexOutOfBoundException extends Exception {
        private int lowerBound;
        private int upperBound;
        private int index;

        public MyIndexOutOfBoundException(int lowerBound, int upperBound, int index) {
            super(String.format("Error Message: Index: %d, but Lower bound: %d, Upper bound: %d", index, lowerBound,
                    upperBound));
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.index = index;
        }

    }

    /**
     * Modify the following parse() method so that it will compile: (Score 1)
     */
    public static void parse(File file) {
        RandomAccessFile input = null;
        String line = null;

        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MyIndexOutOfBoundException(2, 4, 5).getMessage());
    }
}