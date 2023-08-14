package lru_and_lfu;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class LRUWithExpires {
    // 之所以使用LinkedHashMap而不是LinkedList，是为了在O(1)的时间内找到对应的key
    // LinkedList中的contains方法是O(n)的
    private static Map<Object, Object> cache;
    private static PriorityQueue<Node> queue;
    private ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);;
    private int capacity;

    class Node<K, V> implements Comparable<Node>{
        K key;
        V val;
        long expires;

        public Node(K key, V val, long time) {
            this.key = key;
            this.val = val;
            this.expires = time;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.expires - o.expires);
        }
    }

    class ExpireThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Node node = queue.peek();
                if (node == null || node.expires < System.currentTimeMillis()) {
                    return;
                }
                // 进行删除
                queue.poll();
                cache.remove(node.key);
            }
        }
    }

    /**
     * 以正整数capacity作为容量初始化 LRU 缓存
     *
     * @param capacity
     */
    public LRUWithExpires(int capacity) {
        cache = Collections.synchronizedMap(new LinkedHashMap<>());
        this.capacity = capacity;
        threadPool.scheduleWithFixedDelay(new ExpireThread(), 0, 3, TimeUnit.SECONDS);
        queue = new PriorityQueue<>();
    }

    /**
     * 如果关键字key存在于缓存中，则返回关键字的值，否则返回-1
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        return makeKeyFirst(key);
    }

    private Object makeKeyFirst(String key) {
        // 缓存中删除这个key，在队尾添加上这个key
        Object val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
        return val;
    }

    /**
     * 如果关键字key已经存在，则变更其数据值value；
     * 如果不存在，则向缓存中插入该组key-value。
     * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     *
     * @param key
     * @param value
     */
    public void put(String key, Node value) {
        if (cache.containsKey(key)) {
            makeKeyFirst(key);
        } else {
            if (cache.size() == capacity) {
                // 删除头部元素
                Object firstKey = cache.keySet().iterator().next();
                cache.remove(firstKey);
            }
        }
        cache.put(key, value);
        queue.add(new Node(key, value, System.currentTimeMillis()));
    }
}
