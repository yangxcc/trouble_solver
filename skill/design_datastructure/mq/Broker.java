package skill.design_datastructure.mq;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    private final static int MAX_SIZE = 3;
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    public static void process(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.println("======成功向队列中插入一条消息========");
        } else {
            System.out.println("消息处理中心内暂存的消息达到最大负荷，不能继续放入消息！");
        }
    }

    public static String consumer() {
        String msg = messageQueue.poll();
        if (msg == null) {
            System.out.println("当前队列中没有消息可供消费");
        } else {
            System.out.println("成功取出消息");
        }
        return msg;
    }
}
