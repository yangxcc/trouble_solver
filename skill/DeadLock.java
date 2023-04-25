/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-04-04 11:33:28
 * @LastEditTime: 2023-04-04 11:38:32
 */
package skill;

public class DeadLock {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Object.class) {
                    System.out.println("获取到了锁A");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    synchronized (DeadLock.class) {
                        System.out.println("获取到了锁B");
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (DeadLock.class) {
                System.out.println("获取到了锁B");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                synchronized (Object.class) {
                    System.out.println("获取到了锁A");
                }
            }
        }).start();
    }
}