package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        T obj;
        Node<T> next;
        Node<T> prev;

        Node(T obj) {
            this.obj = obj;
        }
    }

    Node<T> head;
    Node<T> tail;
    int size = 0;

    private Node<T> getNode(int index) {
        return index > size / 2 ? getNodeFromTail(index) : getNodeFromHead(index);
    }

    private Node<T> getNodeFromHead(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private Node<T> getNodeFromTail(int index) {
        Node<T> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        return current;
    }

    private void addNode(Node<T> node, int index) {
        if (index == 0) {
            addHead(node);
        } else if (index == size) {
            addTail(node);
        } else {
            addMiddle(node, index);
        }
        size++;
    }

    private void addMiddle(Node<T> node, int index) {
        Node<T> nodeAfter = getNode(index);
        Node<T> nodeBefore = nodeAfter.prev;
        node.prev = nodeBefore;
        nodeBefore.next = node;
        node.next = nodeAfter;
        nodeAfter.prev = node;
    }

    private void addTail(Node<T> node) {
        tail.next = node;
        node.prev = tail;
        node.next = null;
        tail = node;
    }

    private void addHead(Node<T> node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
        }
    }

    private void removeNode(Node<T> node) {
        if (node == head) {
            removeHead();
        } else if (node == tail) {
            removeTail();
        } else {
            removeMiddle(node);
        }
        size--;
        clearReferences(node);
    }

    private void clearReferences(Node<T> node) {
        node.next = null;
        node.obj = null;
        node.prev = null;
    }

    private void removeMiddle(Node<T> nodeToRemove) {
        Node<T> nodeBefore = nodeToRemove.prev;
        Node<T> nodeAfter = nodeToRemove.next;
        nodeBefore.next = nodeAfter;
        nodeAfter.prev = nodeBefore;
    }

    private void removeTail() {
        tail = tail.prev;
        tail.next = null;
    }

    private void removeHead() {
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    @Override
    public boolean add(T obj) {
        Node<T> node = new Node<T>(obj);
        addNode(node, size);
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        Node<T> current = head;
        Node<T> previous = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T res = current.obj;
            previous = current;
            current = current.next;
            return res;
        }

        @Override
        public void remove() {
            if (previous == null) {
                throw new IllegalStateException();
            }
            removeNode(previous);
            previous = null;
        }
    }

    @Override
    public void add(int index, T obj) {
        checkBounds(index, true);
        Node<T> node = new Node<T>(obj);
        addNode(node, index);
    }

    @Override
    public T remove(int index) {
        checkBounds(index, false);
        Node<T> node = getNode(index);
        T res = node.obj;
        removeNode(node);
        return res;
    }

    @Override
    public T get(int index) {
        checkBounds(index, false);
        Node<T> node = getNode(index);
        return node.obj;
    }

    @Override
    public int indexOf(T pattern) {
        int index = 0;
        Node<T> current = head;
        while (current != null && !Objects.equals(current.obj, pattern)) {
            current = current.next;
            index++;
        }
        return current == null ? -1 : index;
    }

    @Override
    public int lastIndexOf(T pattern) {
        int index = size - 1;
        Node<T> current = tail;
        while (current != null && !Objects.equals(current.obj, pattern)) {
            current = current.prev;
            index--;
        }
        return current == null ? -1 : index;
    }
}