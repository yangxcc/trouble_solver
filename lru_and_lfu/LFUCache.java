/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-16 13:54:08
 * @LastEditTime: 2023-04-01 17:19:27
 */
package lru_and_lfu;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

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


// 使用LinkedList，时间肯定是没LinkedHashSet快的，因为双向链表中的删除需要索引，而LinkedHashSet中可以直接按照key删除
class lfu2 {
    private int capacity; // 最大容量
    private int minFreq;  // 最小频率
    private HashMap<Integer, Integer> kv; // kv表
    private HashMap<Integer, Integer> kf; // kf表
    private HashMap<Integer, LinkedList<Integer>> flist;


    public lfu2(int _capacity) {
        this.capacity = _capacity;
        this.minFreq = 0;
        this.kv = new HashMap<>();
        this.kf = new HashMap<>();
        this.flist = new HashMap<>();
    }

    public int get(int key) {
        if (!kv.containsKey(key)) {
            return -1;
        }

        int freq = kf.get(key);
        kf.put(key, freq + 1);

        // 更新flist表
        updateFList(key, freq);

        return kv.get(key);
    }

    private void updateFList(int key, int freq) {
        LinkedList<Integer> list = flist.get(freq);
        // 从list中删除key
        int idx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == key) {
                idx = i;
                break;
            }
        }
        int removeKey = list.remove(idx);
        if (list.isEmpty()) {
            flist.remove(freq);
            // 这里有这个条件判断，别写错了！！！最开始没加上条件判断
            if (freq == minFreq) {
                this.minFreq++;
            }
        }

        flist.putIfAbsent(freq + 1, new LinkedList<>());
        flist.get(freq + 1).addLast(removeKey);
    }

    public void put(int key, int value) {
        if (kv.containsKey(key)) {
            kv.put(key, value);

            int freq = kf.get(key);
            kf.put(key, freq + 1);

            updateFList(key, freq);
        } else {
            // 不包含这个key
            if (kv.size() == capacity) {
                LinkedList<Integer> list = flist.get(minFreq);
                int removeKey = list.removeFirst();
                if (list.isEmpty()) {
                    flist.remove(minFreq);
                    minFreq++;
                }
                kv.remove(removeKey);
                kf.remove(removeKey);
            }

            minFreq = 1;
            kv.put(key, value);
            kf.put(key, minFreq);
            flist.putIfAbsent(minFreq, new LinkedList<>());
            flist.get(minFreq).addLast(key);
        }
    }
}