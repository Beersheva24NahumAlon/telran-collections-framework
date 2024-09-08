package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T> {
    private LinkedList<T> list = new LinkedList<>();
    HashMap<T, Node<T>> map = new HashMap<>();

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);
        }        
        return res;        
    }

    @Override
    public boolean remove(T pattern) {
        Node<T> node = map.get(pattern);
        boolean res = false;
        if (node != null) {
            res = true;
            map.remove(pattern);
            list.removeNode(node);
        }
        return res;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean contains(T pattern) {
        return map.containsKey(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedHashSetIterator();
    }

    class LinkedHashSetIterator implements Iterator<T> {
        Node<T> current = list.head;
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
            T pattern = previous.obj;
            list.removeNode(previous);
            map.remove(pattern);
            previous = null;
        } 
        
    } 

    @Override
    public T get(Object pattern) {
        Node<T> node = map.get(pattern);
        return node != null ? node.obj : null;
    }

}