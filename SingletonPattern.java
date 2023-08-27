/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-07 22:08:43
 * @LastEditTime: 2023-03-07 22:26:52
 */
public class SingletonPattern {
}

class LazySingleInstance {
    /**
     * 实现懒汉式单例模式，用到时才加载
     * 线程不安全的
     */
    private static LazySingleInstance singleInstance;

    public static LazySingleInstance getInstance() {
        /**
         * 仅就两个线程而言，如果线程A在判断当前对象为null之后，new实例化之前时间片耗尽，切换了进程B，
         * 进程B完整的执行了整个流程，实例化出了一个对象，当线程A切换回来之后，又会创建一个新的对象
         * 所以，这种方式是线程不安全的
         */
        if (singleInstance == null) {
            singleInstance = new LazySingleInstance();
        }

        return singleInstance;
    }


    /**
     * 使用双重检验+volatile关键字 改进懒汉式写法，使其变成线程安全的
     * 首先解释为什么使用双重检验，当第一次判断当前实例为空时，加锁，只有一个线程能够进入创建一个实例
     * 当该线程释放锁之后，另一个线程会再次进行判断，这时候判断出实例不为null
     * 
     * 为什么使用volatile呢，这个关键字能够禁止JVM对指令进行重排，new关键字主要进行三部分的工作，
     *  1. 分配内存
     *  2. 初始化对象
     *  3. 设置instance指向内存空间
     * 如果不使用这个关键字，在执行过程中，JVM可能会将步骤2，3进行调换，当3执行之后，锁就会释放掉，这是另一个线程就会抢占所，然后又创建出一个新的对象
     */
    private static volatile LazySingleInstance singleInstance2;

    // 需要加上私有的构造函数，这样才能够保证本类之外的其他类不能通过new的方式来实例化对象
    private LazySingleInstance(){}
    public static LazySingleInstance getInstanceSafely() {
        if (singleInstance2 == null) {
            synchronized(LazySingleInstance.class) {
                if (singleInstance2 == null) {
                    singleInstance2 = new LazySingleInstance();
                }
            }
        }

        return singleInstance;
    }
}

class HungrySingleInstance {
    /**
     * 实现饿汉式单例模式，类加载的时候就实例化出来,线程安全的
     */
    private static HungrySingleInstance hungrySingleInstance = new HungrySingleInstance();

    public static HungrySingleInstance getInstance() {
        return hungrySingleInstance;
    }
}

/**
 * 使用静态内部类实现
 * 这种方式跟饿汉式方式采用的机制类似，但又有不同。
 * 两者都是采用了类装载的机制来保证初始化实例时只有一个线程。
 * 不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，
 * 而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。
 *
 * 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
 * 优点：避免了线程不安全，延迟加载，效率高。
 */
class SingleInstanceWithInnerClass {
    public SingleInstanceWithInnerClass() {}

    private static class innerSingleInstance {
        private static final SingleInstanceWithInnerClass INSTANCE = new SingleInstanceWithInnerClass();
    }

    public static SingleInstanceWithInnerClass getInstance() {
        return innerSingleInstance.INSTANCE;
    }
}
