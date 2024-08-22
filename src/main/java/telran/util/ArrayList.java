package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

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
    public boolean remove(T pattern) {
        boolean isFound = false;
        int index = indexOf(pattern);
        if (index != -1) {
            isFound = true;
            remove(index);
        }
        return isFound;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CollectionIterator();
    }

    private class CollectionIterator implements Iterator<T> {
        int curIndex = 0;

        @Override
        public boolean hasNext() {
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
    }

    private void checkBounds(int index, boolean inclusiveBounds) {
        int limit = inclusiveBounds ? size : size - 1;
        if (index > limit || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index must be in range from 0 to %d", limit));
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
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkBounds(index, true);
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
}
