package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public abstract class AbstractMapTest {
    Map<Integer, Integer> map;
    Integer[] keyArray = { 1, 3, -2, 6, 0, 4, 9, 5 };

    Integer func(Integer key) {
        return key * key;
    }

    void setUp() {
        Arrays.stream(keyArray).forEach(key -> map.put(key, func(key)));
    }

    abstract <T> void runTest(T[] expected, T[] actual);

    @Test
    void getTest() {
        assertEquals(4, map.get(-2));
        assertEquals(0, map.get(0));
        assertEquals(null, map.get(8));
    }

    @Test
    void containsKeyTest() {
        assertTrue(map.containsKey(6));
        assertFalse(map.containsKey(8));
    }

    @Test
    void containsValueTest() {
        assertTrue(map.containsValue(36));
        assertFalse(map.containsValue(64));
    }

    @Test
    void valuesTest() {
        Integer[] expected = { 1, 9, 4, 36, 0, 16, 81, 25 };
        Collection<Integer> collection = (Collection<Integer>) map.values();
        runTest(expected, collection.stream().sorted().toArray(Integer[]::new));
    }

    @Test
    void keySetTest() {
        Set<Integer> set = (Set<Integer>) map.keySet();
        runTest(keyArray, set.stream().toArray(Integer[]::new));
    }

    @Test
    void sizeTest() {
        assertEquals(8, map.size());
        map.put(2, 4);
        assertEquals(9, map.size());
    }

    @Test
    void getOrDefaultTest() {
        assertEquals(4, map.getOrDefault(-2, 0));
        assertEquals(0, map.getOrDefault(0, 0));
        assertEquals(0, map.getOrDefault(8, 0));
    }

    @Test
    void putIfAbsentTest() {
        assertEquals(null, map.putIfAbsent(2, 4));
        assertEquals(9, map.size());
        assertEquals(4, map.putIfAbsent(2, 5));
        assertEquals(9, map.size());
        assertFalse(map.containsValue(5));
    }

    @Test
    void putTest() {
        assertEquals(null, map.put(2, 4));
        assertEquals(9, map.size());
        assertEquals(4, map.put(2, 5));
        assertEquals(9, map.size());
        assertTrue(map.containsValue(5));
        assertEquals(5, map.get(2));
    }

    @Test
    void isEmptyTest() {
        assertFalse(map.isEmpty());
        map.entrySet().clear();
        assertTrue(map.isEmpty());
    }
}