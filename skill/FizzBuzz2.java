package skill;

/**
 * 使用多线程，顺序打印1-100
 */
public class FizzBuzz2 {
    private static final int NUM_THREADS = 5;
    private static volatile int currentNum = 1;
    private static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        for (int i = 1; i <= NUM_THREADS; i++) {
            new Thread(new Printer(i)).start();
        }
    }

    private static class Printer implements Runnable {
        private int threadId;

        public Printer(int _threadId) {
            this.threadId = _threadId;
        }

        @Override
        public void run() {
            while (true) {
                synchronized(this) {
                    if (currentNum > MAX_VALUE) {
                        return;
                    }

                    if (currentNum % NUM_THREADS == threadId - 1) {
                        System.out.println(currentNum);
                        currentNum++;
                    }
                }
            }
        }

    }
}
