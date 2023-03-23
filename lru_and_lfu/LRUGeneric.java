package lru_and_lfu;

import java.util.LinkedHashMap;

/**
 * leetcode上 LRU的实现中，缓存中的key和value都是int类型的数据，这里改成泛型
 */
public class LRUGeneric {
    int capacity;
    LinkedHashMap<Key<?>, Val<?>> cache;

    public LRUGeneric(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public Val<?> get(Key<?> key) {
        if (!cache.containsKey(key)) {
            return new Val<>(-1);
        }

        makeKeyFirst(key);

        return cache.get(key);
    }

    private void makeKeyFirst(Key<?> key) {
        Val<?> val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

    public void put(Key<?> k, Val<?> v) {
        if (cache.containsKey(k)) {
            makeKeyFirst(k);
        } else {
            if (cache.size() == capacity) {
                // 删除第一个key
                Key<?> firstKey = cache.keySet().iterator().next();
                cache.remove(firstKey);
            }
        }
        cache.put(k, v);
    }
}

class Key<K> {
    private K key;

    public Key(K k) {
        this.key = k;
    }

    public K get() {
        return this.key;
    }

    public void set(K k) {
        this.key = k;
    }
}

class Val<V> {
    private V val;

    public Val(V v) {
        this.val = v;
    }

    public V get() {
        return this.val;
    }

    public void set(V v) {
        this.val = v;
    }
}