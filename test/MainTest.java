import org.junit.jupiter.api.Test;
import main.java.Field;

import static org.junit.jupiter.api.Assertions.*;

class test { // тесты для крестиков эквивалентны тестам для ноликов, поэтому вторые опустил
    private Field testField = new Field();

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
        assertFalse(testField.clear(-1, -1));
        assertFalse(testField.clear('a', 'a'));
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

    @Test
    void get() {
        testField.clear();
        testField.setCross(1, 1);
        assertEquals(Field.symbol.cross, testField.get(1, 1));
        assertNotEquals(Field.symbol.cross, testField.get(1, 0));
    }

    @Test
    void equals() {
        testField.clear();
        Field testField2 = new Field();
        testField.setCross(1, 1);
        testField.setCircle(0, 2);
        testField2.setCross(1, 1);
        testField2.setCircle(0, 2);
        assertEquals(testField, testField2);
        testField2.setCircle(2,2);
        assertNotEquals(testField, testField2);
    }
}