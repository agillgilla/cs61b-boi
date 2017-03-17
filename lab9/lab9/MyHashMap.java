package lab9;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Collections;

/**
 * Created by Arjun on 3/17/2017.
 */
public class MyHashMap<K, V> implements Map61B {
    private ArrayList<Entry<K, V>> bins;
    private HashSet<K> keySet;
    private HashSet<Entry<K, V>> entrySet;
    private double loadFactor;
    private int size;

    public MyHashMap() {
        this(127, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, .75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.keySet = new HashSet<K>();
        this.entrySet = new HashSet<Entry<K, V>>();
        if (initialSize < 1 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        bins = new ArrayList<Entry<K, V>>(initialSize);
        bins.addAll(Collections.nCopies(initialSize, null));
        this.size = 0;
        this.loadFactor = loadFactor;

    }

    private static class Entry<K, V> implements MapEntry<K, V> {
        K key; V value;
        Entry<K, V> next;
        Entry(K key, V value, Entry<K, V> next) {
            this.key = key; this.value = value; this.next = next;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public V setValue(V x) {
            V old = value; value = x; return old;
        }
        public int hashCode() {
            return getValue().hashCode();
        }
        public boolean equals() {
            return true;
        }
    }

    private void putAll(MyHashMap<K, V> m) {
        for (Iterator i = m.entrySet().iterator(); i.hasNext();) {
            Entry<K, V> e = (Entry<K, V>) i.next();
            put(e.getKey(), e.getValue());
        }
    }

    private Set<Entry<K, V>> entrySet() {
        return this.entrySet;
    }

    private int hash(Object key) {
        return (key == null) ? 0
                : (0x7fffffff & key.hashCode()) % bins.size();
    }

    private void grow() {
        MyHashMap<K, V> newMap = new MyHashMap((bins.size() * 2), loadFactor);
        newMap.putAll(this);
        copyFrom(newMap);
    }

    private void copyFrom(MyHashMap<K, V> S) {
        size = S.size;
        bins = S.bins;
        loadFactor = S.loadFactor;
    }

    private Entry<K, V> find(Object key, Entry<K, V> bin) {
        for (Entry<K, V> e = bin; e != null; e = e.next) {
            if (key == null && e.key == null || key.equals(e.key)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.keySet = new HashSet<K>();
        bins = new ArrayList<Entry<K, V>>(127);
    }

    @Override
    public boolean containsKey(Object key) {
        return this.keySet.contains(key);
    }

    @Override
    public V get(Object key) {
        Entry e = find(key, bins.get(hash(key)));
        return (e == null) ? null : (V) e.value;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(Object key, Object value) {
        this.keySet.add((K) key);
        int h = hash(key);
        Entry<K, V> e = find(key, bins.get(h));
        if (e == null) {
            bins.set(h, new Entry<K, V>((K) key, (V) value, bins.get(h)));
            this.entrySet.add(new Entry<K, V>((K) key, (V) value, bins.get(h)));
            size += 1;
            if (size > bins.size() * loadFactor) {
                grow();
            }
            return;
        } else {
            e.setValue((V) value);
        }
    }


    @Override
    public Set<K> keySet() {
        return this.keySet;
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("'remove' operation not supported!");
    }

    @Override
    public V remove(Object key, Object value) {
        throw new UnsupportedOperationException("'remove' operation not supported!");
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
