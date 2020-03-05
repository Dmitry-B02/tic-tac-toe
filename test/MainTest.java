import org.junit.jupiter.api.Test;
import main.java.Field;

import static org.junit.jupiter.api.Assertions.*;

class test { // тесты для крестиков эквивалентны тестам для ноликов, поэтому вторые опустил
    Field testField = new Field();
    @Test
    void setSymbol() {
        assertTrue(testField.setCross(1, 1));
        assertFalse(testField.setCross(1, 1));
        assertFalse(testField.setCross(0, 3));
        assertFalse(testField.setCross(-1, -1));
        assertFalse(testField.setCross('a', 'a'));
    }
    @Test
    void clearSymbol() {
        testField.setCross(1, 1);
        assertTrue(testField.clear(1, 1));
        assertFalse(testField.clear(1, 1));
        assertFalse(testField.clear(3, 3));
    }
    @Test
    void sequence() {
        testField.clear();
        testField.setCross(0, 0);
        testField.setCross(0, 1);
        testField.setCross(0, 2);
        System.out.println(testField.toString());
        testField.longestCrossSequence();
        assertEquals(3, testField.longestCrossSequence());
        testField.clear();
        testField.setCross(0, 0);
        testField.setCross(1, 1);
        testField.setCross(2, 2);
        assertEquals(3, testField.longestCrossSequence());
        testField.clear();
        testField.setCross(0, 2);
        testField.setCross(1, 1);
        testField.setCross(2, 0);
        assertEquals(3, testField.longestCrossSequence());
        testField.clear();
        testField.setCross(0, 0);
        testField.setCross(1, 0);
        testField.setCross(2, 0);
        assertEquals(3, testField.longestCrossSequence());
    }
}