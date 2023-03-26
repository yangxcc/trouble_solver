/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-26 19:15:05
 * @LastEditTime: 2023-03-26 19:46:32
 */
package skill;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * leetcode 1195 middle 交替打印字符串
 * 
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13,
 * 14, fizzbuzz。
 * 
 * 请你实现一个有四个线程的多线程版 FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 */

// 通过加锁来实现同步
public class FizzBuzz {
    private int n;
    private Lock lock = new ReentrantLock();
    private volatile int index; // 通过volatile保证对多线程的可见性

    private Condition condition = lock.newCondition(); // 等待队列
    // private Condition buzz = lock.newCondition();
    // private Condition fizzAndBuzz = lock.newCondition();
    // private Condition num = lock.newCondition();

    public FizzBuzz(int n) {
        this.n = n;
        this.index = 1;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        while (index <= n) {
            if (index % 3 == 0 && index % 5 != 0) {
                printFizz.run();
                index++;
                // 唤醒其余的线程
                condition.signalAll();
            } else {
                // 不符合条件就加入等待队列中
                condition.await();
            }
        }

        lock.unlock();
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        while (index <= n) {
            if (index % 5 == 0 && index % 3 != 0) {
                printBuzz.run();
                index++;
                condition.signalAll();
            } else {
                condition.await();
            }
        }

        lock.unlock();
    }

    public void fizzbuzz(Runnable printfizzbuzz) throws InterruptedException {
        lock.lock();

        while (index <= n) {
            if (index % 5 == 0 && index % 3 == 0) {
                printfizzbuzz.run();
                index++;
                condition.signalAll();
            } else {
                condition.await();
            }
        }

        lock.unlock();
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();

        while (index <= n) {
            if (index % 5 != 0 && index % 3 != 0) {
                printNumber.accept(index);
                index++;
                condition.signalAll();
            } else {
                condition.await();
            }
        }

        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        CountDownLatch countDownLatch = new CountDownLatch(4);

        Runnable fizz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizz");
                        }
                    });
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        };

        Runnable buzz = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("buzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        };

        Runnable fizzbuzzRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("fizzbuzz");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        };

        Runnable number = new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.number(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        };

        new Thread(fizz).start();
        new Thread(buzz).start();
        new Thread(fizzbuzzRunnable).start();
        new Thread(number).start();

        countDownLatch.await();
        System.out.println("down...");
    }

}
