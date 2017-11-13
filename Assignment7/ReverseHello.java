public class ReverseHello {
    public static void createThread(int i) throws InterruptedException {
        if (i == 51) {
            return;
        }
        Thread thread = new Thread() {
            public void run() {
                try {
                    createThread(i + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Hello from Thread " + i + "!");
            }
        };
  
        thread.start();
        thread.join();
    }

    public static void main(String[] args) {
        try {
            createThread(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}