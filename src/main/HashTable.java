package main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTable <K, V> implements Iterable<HashTable.Entry> {

    @Override
    public Iterator iterator() {
        return new HashTableIterator();
    }

    public class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private LinkedList<Entry<K,V>>[] arr;
    private final BigDecimal DEFAULT_LOAD_FACTOR = new BigDecimal(0.75);
    protected int capacity = 0;
    private int actualSize = 0;
    private BigDecimal loadFactor = new BigDecimal(0);

    public HashTable () {
        this(16);
    }

    public HashTable (int capacity) {
        this(null, capacity);
    }

    public HashTable (HashTable<K, V> src, int capacity) {
        int initSize = capacity;
        this.arr = new LinkedList[initSize];
        if (src != null) {
            for (Entry<K, V> entry : src) {
                put(entry.key, entry.value);
            }
        }
        this.capacity = initSize;
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null. Please, try again.");
        Entry <K, V> entry = new Entry<>(key, value);
        int index = getIndex(key.hashCode());
        LinkedList <Entry<K, V>> list = arr[index];
        if (list == null) {
            list = new LinkedList<>();
            arr[index] = list;
        }
        list.add(entry);
        actualSize++;
        loadFactor = countLoadFactor();
        if (loadFactor.compareTo(DEFAULT_LOAD_FACTOR) == 1) {
            rehash();
        }
    }

    public V get(K key) {
        if (key == null) return null;
        int index = getIndex(key.hashCode());
        LinkedList <Entry <K, V>> list = arr[index];
        V result = null;
        for (Entry<K, V> item : list) {
            if (item.key == key) {
                result = item.value;
                break;
            }
        }
        return result;
    }

    public int size() {
        return actualSize;
    }

    private int getIndex(int hash) {
        return (hash & 0x7FFFFFFF) % capacity;
    }

    private BigDecimal countLoadFactor() {
        return new BigDecimal((float) actualSize / capacity);
    }

    private void rehash() {
        LinkedList<Entry<K, V>>[] oldArr = new LinkedList[capacity];
        System.arraycopy(arr, 0, oldArr, 0, capacity);
        capacity *= 2;
        actualSize = 0;
        arr = new LinkedList[capacity];
        for (LinkedList<Entry<K, V>> list : oldArr) {
            if (list == null) continue;
            for (Entry<K, V> entry : list) {
                put(entry.key, entry.value);
            }
        }

    }

    public class HashTableIterator implements Iterator<Entry> {

        private final int currentSize;
        private int counter = 0;
        Entry<K, V>[] resultingArr;

        HashTableIterator() {
            currentSize = size();
            LinkedList<Entry<K, V>>[] table = arr;
            int counter = 0;
            resultingArr = new Entry[actualSize];
            for (LinkedList<Entry<K, V>> list : table) {
                if (list == null) continue;
                for (Entry<K, V> entry : list) {
                    resultingArr[counter] = entry;
                    counter++;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return counter < resultingArr.length;
        }

        @Override
        public Entry<K, V> next() {
            if (currentSize != size()) throw new ConcurrentModificationException();
            Entry<K, V> result = null;
            if (counter < resultingArr.length) {
                result = resultingArr[counter];
                counter++;
            } else {
                counter = 0;
            }
            return result;
        }
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "arr=" + Arrays.toString(arr) +
                ", DEFAULT_LOAD_FACTOR=" + DEFAULT_LOAD_FACTOR +
                ", capacity=" + capacity +
                ", actualSize=" + actualSize +
                ", loadFactor=" + loadFactor +
                '}';
    }
}
