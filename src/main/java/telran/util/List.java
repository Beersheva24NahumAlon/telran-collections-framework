package telran.util;

public interface List<T> extends Collection<T> {
    void add(int index, T obj);

    T remove(int index);

    T get(int index);

    int indexOf(T pattern);

    int lastIndexOf(T pattern);

    @Override
    default boolean remove(T pattern) {
        boolean isFound = false;
        int index = indexOf(pattern);
        if (index >= 0) {
            isFound = true;
            remove(index);
        }
        return isFound;
    }

    @Override
    default boolean contains(T pattern) {
        return indexOf(pattern) >= 0;
    }

    default void checkBounds(int index, boolean inclusiveBounds) {
        int size = size();
        int limit = inclusiveBounds ? size : size - 1;
        if (index > limit || index < 0) {
            throw new IndexOutOfBoundsException(String.format("Index must be in range from 0 to %d", limit));
        }
    }
}