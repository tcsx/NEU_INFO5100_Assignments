public class Extra {
    public boolean findPartition(int arr[]) {
        if (arr.length < 2) {
            return false;
        }
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        int[] sequence = new int[arr.length];
        return partition(sequence, 1, arr, sum);
    }

    private boolean partition(int[] sequence, int ptr, int[] arr, int sum) {
        if (ptr == sequence.length) {
            return evaluate(sequence, arr, sum);
        }
        sequence[0] = 1;
        for (int i = 0; i < 2; i++) { //to exhaust all ways of partition
            sequence[ptr] = i;
            if (partition(sequence, ptr + 1, arr, sum))
                return true;
        }
        return false;
    }

    private boolean evaluate(int[] sequence, int[] arr, int sum) {
        int partSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sequence[i] == 1) { //the corresponding values in arr[] are in the same subset
                partSum += arr[i];
            }
        }
        return (partSum == sum);
    }

    public static void main(String[] args) {
        Extra e = new Extra();
        int[] a1 = { 1, 2, 3, 4 };
        int[] a2 = { 1, 5, 11, 5 };
        int[] a3 = { 2, 6, 8, 10 };
        int[] a4 = { 2, 6, 8, 12 };
        int[] a5 = { 2, 6, 8, 12, 10 };
        int[] a6 = { 2, 6, 8, 12, 4 };
        System.out.println(e.findPartition(a1));
        System.out.println(e.findPartition(a2));
        System.out.println(e.findPartition(a3));
        System.out.println(e.findPartition(a4));
        System.out.println(e.findPartition(a5));
        System.out.println(e.findPartition(a6));
    }
}