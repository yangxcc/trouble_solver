/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-16 13:54:08
 * @LastEditTime: 2023-03-02 21:46:49
 */
package lru_and_lfu;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * LFU也是一种缓存淘汰策略，全称是Least Frequently Used，最近最少使用的
 */
public class LFUCache {
    private int capacity;
    private int minFreq;
    private HashMap<Integer, Integer> kv; // key-value表
    private HashMap<Integer, Integer> kf; // key-frequent表
    // 当frequent相同时，删除的是最久没有被访问到的
    private HashMap<Integer, LinkedHashSet<Integer>> fl; // frequent-list表

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        kv = new HashMap<>();
        kf = new HashMap<>();
        fl = new HashMap<>();
    }

    public int get(int key) {
        if (!kv.containsKey(key)) {
            return -1;
        }
        int freq = kf.get(key);
        // 访问频率+1
        kf.put(key, freq + 1);

        increaseFreq(key, freq);

        return kv.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (kv.containsKey(key)) {
            // 更新kv表
            kv.put(key, value);

            int freq = kf.get(key);
            kf.put(key, freq + 1);

            increaseFreq(key, freq);
        } else {
            // 没有这个key

            if (kv.size() >= capacity) {
                // 删除最小频率上的最久未访问的元素
                removeEle();
            }

            // 放到kv表中，更新minFreq
            kv.put(key, value);
            minFreq = 1;

            // 放到kf表中
            kf.put(key, minFreq);

            // 放到kl表中
            fl.putIfAbsent(minFreq, new LinkedHashSet<>());
            fl.get(minFreq).add(key);
        }
    }

    // 更新kl表
    private void increaseFreq(int key, int freq) {
        // 在fl表中把此前频率中的key删掉，在+1频率中再加上
        fl.get(freq).remove(key);
        if (fl.get(freq).isEmpty()) {
            fl.remove(freq);
            if (minFreq == freq) {
                minFreq++;
            }
        }

        fl.putIfAbsent(freq + 1, new LinkedHashSet<>());
        fl.get(freq + 1).add(key);
    }

    private void removeEle() {
        // 通过minFreq找到其对应的列表中的第一个key
        int firstKey = fl.get(minFreq).iterator().next();
        fl.get(minFreq).remove(firstKey);
        if (fl.get(minFreq).isEmpty()) {
            fl.remove(minFreq);
            minFreq++;
        }

        // kv表中删除
        kv.remove(firstKey);

        // kf表中删除
        kf.remove(firstKey);
    }
}
