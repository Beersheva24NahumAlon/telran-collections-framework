package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = java.util.Arrays.copyOf(expected, expected.length);
        java.util.Arrays.sort(expectedSorted);
        assertArrayEquals(expectedSorted, actual);
        assertEquals(expectedSorted.length, actual.length);
    }

    @Override
    @BeforeEach
    void setUp() {
        map = new TreeMap<>();
        super.setUp();
    }
}