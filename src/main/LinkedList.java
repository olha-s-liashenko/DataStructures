package main;

import java.util.NoSuchElementException;

public class LinkedList <T> {

    public class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
        public Node next() {
            return next;
        }

        public T getData() {
            return data;
        }

    }

    private Node head = null, tail = null;
    int size = 0;

    public void addNode(Object data) {
        Node temp = new Node((T)data);
        if (head == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = tail.next;
        }
        size++;
    }

    public void addNodeAtStart(T data) {
        Node temp = new Node(data);
        if (head == null) {
            head = tail = temp;
        } else {
            temp.next = head;
            head = temp;
        }

        size++;
    }

    public void addNodeAt(T data, int index) {
        Node trav = head;
        int count = 0;

        while(trav != null && ++count != index) {
            trav = trav.next;
        }

        Node temp = new Node(data);
        temp.next = trav.next;
        trav.next = temp;

        size++;
    }

    public T removeNode() {
        Node trav = head;

        while(trav.next != null && trav.next.next != null) {
            trav = trav.next;
        }
        T data = trav.next.data;
        trav.next = null;
        tail = trav;
        size--;
        return data;
    }

    public T removeNodeAtStart() {
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public T removeNodeAt(int index) {
        Node trav = head;
        int count = 0;

        while (trav != null && count++ != index) {
            trav = trav.next;
        }
        T data = trav.data;
        trav.data = trav.next.data;
        trav.next = trav.next.next;
        size--;
        return data;
    }

    public boolean contains(T data) {
        Node trav = head;
        while (trav != null) {
            if (trav.data.equals(data)) {
                return true;
            } else {
                trav = trav.next;
            }
        }
        return false;
    }

    public int indexOf(T data) {
        Node trav = head;
        int count = 0;

        while (trav != null) {
            if (trav.data.equals(data)) {
                return count;
            }
            trav = trav.next;
            count++;
        }
        return -1;
    }

    public String print() {
        Node trav = head;
        StringBuilder buffer = new StringBuilder();
        while(trav.next != null) {
            buffer.append(trav.data + " -> ");
            trav = trav.next;
        }
        buffer.append(trav.data);
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    public Node getHead() {
        Node head = this.head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head;
    }

    public T getElement(Object elem) {
        Node trav = this.head;
        while (!trav.equals(elem)) {
            trav = trav.next;
        }
        return (T) trav.data;
    }

    public int size() {
        return this.size;
    }
}
