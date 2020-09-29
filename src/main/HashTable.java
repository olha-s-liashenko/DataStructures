package main.HashTable;

import java.util.Hashtable;
import java.util.LinkedList;

public class HashTable <K, V> {

    private class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K,V>>[] arr;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected int size = 0;
    private int capacity = 0;
    private float loadFactor = 0;

    public HashTable () {
        int initSize = 16;
        this.arr = new LinkedList[initSize];
        this.size = initSize;
    }

    public void put(K key, V value) {
        Entry <K, V> entry = new Entry<>(key, value);
        int index = getIndex(key.hashCode());
        LinkedList <Entry<K, V>> list = arr[index];
        if (list == null) {
            list = new LinkedList<>();
            arr[index] = list;
        }
        list.add(entry);
        capacity++;
        loadFactor = countLoadFactor();
        if (loadFactor > DEFAULT_LOAD_FACTOR) {
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
        return capacity;
    }

    private int getIndex(int hash) {
        return hash % size;
    }

    private int countLoadFactor() {
        return size / capacity;
    }

    private void rehash() {
        LinkedList<Entry<K, V>>[] oldArr = new LinkedList[size];
        System.arraycopy(arr, 0, oldArr, 0, size);
        size *= 2;
        arr = new LinkedList[size];
        for (LinkedList<Entry<K, V>> list : oldArr) {
            for (Entry<K, V> entry : list) {
                put(entry.key, entry.value);
            }
        }

    }

}
