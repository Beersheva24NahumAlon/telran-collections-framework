package telran.util;

import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends AbstractMapTest{

    @Override
    <T> void runTest(T[] expected) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runTest'");
    }

    @Override
    @BeforeEach
    void setUp() {
        map = new TreeMap<>();
        super.setUp();
    }

}
