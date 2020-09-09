package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Array <T> implements Iterable<T> {
    private T[] array;
    private int length = 0;
    private int capacity = 0;

    public Array() {
        this(16);
    }

    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        this.capacity = capacity;
        this.length = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return  length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= this.length) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        return array[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= this.length) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < this.capacity; i++) {
            array[i] = null;
        }
        this.length = 0;
    }

    public void add(T element) {
        ArrayList l;
        if (this.length + 1 > this.capacity) {
            if (this.capacity == 0) {
                this.capacity = 1;
            } else {
                this.capacity *= 2;
            }
            T[] newArray = (T[]) new Object[this.capacity];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        this.array[this.length++] = element;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds. Index: " + index + ". Length: " + this.length);
        }
        T element = this.array[index];
        for (int i = index; i < this.array.length - 1; i++) {
            this.array[i] = this.array[i+1];
        }
        this.array[this.array.length - 1] = null;
        this.capacity = --this.length;
        return element;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < this.length; i++) {
            if (contains(o) && this.array[i].equals(o)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < this.length; i++) {
            if (this.array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        //TODO: implement
    }

    @Override
    public Spliterator<T> spliterator() {
        //TODO: implement
        return null;
    }

    @Override
    public String toString() {
        if (this.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(this.length);
        sb.append("[");
        for (int i = 0; i < this.length-1; i++) {
            sb.append(this.array[i] + ", ");
        }
        sb.append(this.array[this.length-1]);
        sb.append("]");
        return sb.toString();
//        return Arrays.toString(array);
    }
}
