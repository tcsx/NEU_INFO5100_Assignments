public class MaxValue extends Thread{
    private int[] arr;
    private int low, high,max;

    public MaxValue(int[] arr, int low, int high) {
        this.low = low;
        this.high = high;
        this.arr = arr;
        max = arr[low];
    }
    
    public void run(){
        for(int i = low + 1; i < high; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
    }
    
    
    public static int max(int[] arr) throws InterruptedException{
        int len = arr.length;
        MaxValue mv[] = new MaxValue[4];
        int maxVal = Integer.MIN_VALUE;
        for(int i = 0; i < 4; i++) {
            mv[i] = new MaxValue(arr, len * i / 4, len * (i + 1) / 4);
            mv[i].start();
        }
        for(int i = 0; i < 4; i++) {
            mv[i].join();
            if (mv[i].max > maxVal) {
                maxVal = mv[i].max;
            }
        }
        return maxVal;
    }
    
    
    public static void main(String[] args) throws InterruptedException{
        int [] arr = new int[20];
        System.out.println("The array is:");
        for(int i = 0; i < 20; i++) {
            arr[i] = (int)(Math.random() * 100);
            System.out.print(arr[i] + ",");
        }
        System.out.println();

        System.out.println("Max value is: " + max(arr));
    }
}