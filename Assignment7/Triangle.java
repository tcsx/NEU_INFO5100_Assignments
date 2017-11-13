
public class Triangle {
    public void printPascalTriangle(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        for (int i = 0; i < n; i++) {
            arr[i] = 1;
            for (int j = i - 1; j > 0; j--) {
                arr[j] = arr[j] + arr[j - 1];
            }
            for(int j = 0; j <= i; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Triangle t = new Triangle();
        t.printPascalTriangle(1);
        t.printPascalTriangle(5);
        t.printPascalTriangle(10);
    }
}