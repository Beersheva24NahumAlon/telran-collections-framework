package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int size;

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean add(T obj) {
        if (size == array.length) {
            reallocate();
        }
        array[size++] = obj;
        return true;
    }

    private void reallocate() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new CollectionIterator();
    }

    private class CollectionIterator implements Iterator<T> {
        int curIndex = 0;
        boolean flagNext = false;

        @Override
        public boolean hasNext() {
            flagNext = true;
            return curIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[curIndex++];
        }

        @Override
        public void remove() {
            if (!flagNext) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(--curIndex);
            flagNext = false;
        }
    }

    @Override
    public void add(int index, T obj) {
        checkBounds(index, true);
        if (size == array.length) {
            reallocate();
        }
        int i = size;
        size++;
        while (i >= index) {
            array[i + 1] = array[i];
            i--;
        }
        array[index] = obj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkBounds(index, false);
        int i = index;
        size--;
        while (i < size) {
            array[i] = array[i + 1];
            i++;
        }
        array[size] = null;
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkBounds(index, false);
        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        int i = 0;
        while (i < size && !Objects.equals(array[i], pattern)) {
            i++;
        }
        return i != size ? i : -1;
    }

    @Override
    public int lastIndexOf(T pattern) {
        int i = size - 1;
        while (i > -1 && !Objects.equals(array[i], pattern)) {
            i--;
        }
        return i;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeIf(Predicate<T> predicate) {
        int placeToMove = 0;
        for (int i = 0; i < size; i++) {
            if (!predicate.test((T) array[i])) {
                array[placeToMove++] = array[i];
            }
        }
        Arrays.fill(array, placeToMove, size, null);
        boolean res = size > placeToMove;
        size = placeToMove;
        return res;    
    } 
}
