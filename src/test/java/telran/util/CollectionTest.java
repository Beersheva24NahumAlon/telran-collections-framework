package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    private static final int N_ELEMENTS = 1000000;
    protected Collection<Integer> collection;
    Random random = new Random();
    Integer[] array = { 3, -10, 20, 1, 10, 8, 100, 17 };

    void setUp() {
        Arrays.stream(array).forEach(collection::add);
    }

    @Test
    void removeIfTest() {
        Integer[] expected = { 3, 1, 17 };
        collection.removeIf(n -> n % 2 == 0);
        assertArrayEquals(expected, collection.stream().toArray());
    }

    @Test
    void clearTest() {
        collection.clear();
        assertTrue(collection.isEmpty());
    }

    @Test
    void addTest() {
        Integer[] expected = { 3, -10, 20, 1, 10, 8, 100, 17, 200, 17 };
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        assertArrayEquals(expected, collection.stream().toArray());
    }

    @Test
    void sizeTest() {
        assertEquals(array.length, collection.size());
    }

    @Test
    void removeTest() {
        Integer[] expected = { -10, 20, 1, 10, 8, 100 };
        assertFalse(collection.remove(4));
        assertTrue(collection.remove(3));
        assertTrue(collection.remove(17));
        assertArrayEquals(expected, collection.stream().toArray());
    }

    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
        Arrays.stream(array).forEach(n -> collection.remove(n));
        assertTrue(collection.isEmpty());
    }

    @Test
    void containsTest() {
        assertTrue(collection.contains(-10));
        assertFalse(collection.contains(-11));
    }

    @Test
    void iteratorTest() {
        Iterator<Integer> iterator = collection.iterator();
        Integer[] actual = new Integer[array.length];
        int i = 0;
        while (iterator.hasNext()) {
            actual[i++] = iterator.next();
        }
        assertArrayEquals(array, actual);
        assertThrowsExactly(NoSuchElementException.class, iterator::next );
    }

    @Test
    void removeInIteratorTest() {
        Iterator<Integer> it = collection.iterator();
        assertThrowsExactly(IllegalStateException.class, () -> it.remove());
        Integer n = it.next();
        it.remove();
        it.next();
        it.next();
        it.remove();
        assertFalse(collection.contains(n));
        assertThrowsExactly(IllegalStateException.class, () -> it.remove());
    }

    @Test 
    void performanceTest() {
        collection.clear();
        IntStream.range(0, N_ELEMENTS).forEach(i -> collection.add(random.nextInt()));
        collection.clear();

    }
}
