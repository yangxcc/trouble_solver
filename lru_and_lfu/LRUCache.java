/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-16 13:54:02
 * @LastEditTime: 2023-01-17 10:19:55
 */
package lru_and_lfu;

import java.util.LinkedHashMap;

/**
 * leetcode 146 middle LRU缓存
 * 
 * LRU是一种缓存淘汰策略，全称是Least Recently Used，最近最少使用
 */
public class LRUCache {
    // 之所以使用LinkedHashMap而不是LinkedList，是为了在O(1)的时间内找到对应的key
    // LinkedList中的contains方法是O(n)的
    private LinkedHashMap<Integer, Integer> cache;
    private int capacity;
    
    /**
     * 以正整数capacity作为容量初始化 LRU 缓存
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<>();
        this.capacity = capacity;
    }
    
    /**
     * 如果关键字key存在于缓存中，则返回关键字的值，否则返回-1 
     * @param key
     * @return
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        return makeKeyFirst(key);
    }
    
    private int makeKeyFirst(int key) {
        // 缓存中删除这个key，在队尾添加上这个key
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
        return val;
    }

    /**
     * 如果关键字key已经存在，则变更其数据值value；
     * 如果不存在，则向缓存中插入该组key-value。
     * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            makeKeyFirst(key);
        } else {
            if (cache.size() == capacity) {
                // 删除头部元素
                Integer firstKey = cache.keySet().iterator().next();
                cache.remove(firstKey);
            }
        }
        cache.put(key, value);
    }
}
