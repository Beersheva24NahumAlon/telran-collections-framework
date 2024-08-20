package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = { 3, -10, 20, 1, 10, 8, 100, 17 };

    void setUp() {
        Arrays.stream(array).forEach(collection::add);
    }

    @Test
    void addTest() {
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        assertEquals(array.length + 2, collection.size());
    }

    @Test
    void sizeTest() {
        assertEquals(array.length, collection.size());
    }

    @Test
    void removeTest() {
        assertFalse(collection.remove(4));
        assertTrue(collection.remove(3));
        assertTrue(collection.remove(17));
        assertEquals(array.length - 2, collection.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
    }

    @Test
    void containsTest() {
        assertTrue(collection.contains(-10));
        assertFalse(collection.contains(-11));
    }
}
