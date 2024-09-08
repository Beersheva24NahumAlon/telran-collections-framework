package telran.util;

import java.util.Iterator;
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
        Iterator<T> it = list.iterator();
        T iteratedObj;

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public T next() {
            iteratedObj = it.next();
            return iteratedObj;
        }

        @Override
        public void remove() {
            it.remove();
            map.remove(iteratedObj);
        }     
    } 

    @Override
    public T get(Object pattern) {
        Node<T> node = map.get(pattern);
        return node != null ? node.obj : null;
    }
}