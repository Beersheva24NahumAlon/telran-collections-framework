package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class HashMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = java.util.Arrays.copyOf(expected, expected.length);
        java.util.Arrays.sort(expectedSorted);
        T[] actualSorted = java.util.Arrays.copyOf(expected, expected.length);
        java.util.Arrays.sort(actualSorted);
        assertArrayEquals(expectedSorted, actualSorted);
        assertEquals(expectedSorted.length, actualSorted.length);
    }

    @Override
    @BeforeEach
    void setUp() {
        map = new HashMap<>();
        super.setUp();
    }
}