package main.java;

import java.util.Arrays;
import java.util.Objects;


public class Field {

    public enum symbol {
        cross('☦'), circle('✡'), nothing('.');
        private char get;
        public char getSymbol() {
            return get;
        }
        symbol(char c) {
             get = c;
        }
    }

    private enum Line {
        leftRight, upDown, mainDiagonal, secondaryDiagonal
    }

    private int size;
    private symbol[][] field;

    Field(int size) { // конструктор для кастомного поля
        if (size < 3) throw new IllegalArgumentException("Wrong field size");
        this.size = size;
        field = new symbol[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = symbol.nothing;
            }
        }
    }

    public Field() { // конструктор по умолчанию (3 клетки)
        this(3);
    }

    private boolean inBounds(int vertical, int horizontal) {
        return vertical < size && vertical >= 0 && horizontal < size && horizontal >= 0;
    }

    private boolean set(int vertical, int horizontal, symbol element) { // метод добавления элемента
        if (inBounds(vertical, horizontal) && field[vertical][horizontal] == symbol.nothing) {
            field[vertical][horizontal] = element;
            return true;
        } else {
            return false;
        }
    }

    public symbol get(int vertical, int horizontal) { // получение символа в заданной клетке
        if (inBounds(vertical, horizontal)) return field[vertical][horizontal];
        else throw new IllegalArgumentException("Wrong Input");
    }

    public boolean setCross(int vertical, int horizontal) {
        return set(vertical, horizontal, symbol.cross);
    }

    public boolean setCircle(int vertical, int horizontal) {
        return set(vertical, horizontal, symbol.circle);
    }

    public boolean clear(int vertical, int horizontal) { // очистка указанной клетки
        if (inBounds(vertical, horizontal) && field[vertical][horizontal] != symbol.nothing) {
            field[vertical][horizontal] = symbol.nothing;
            return true;
        } else return false;
    }

    public void clear() { // очистка всего поля
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                clear(i, j);
            }
        }
    }

    private int checkDirection(Line line, int vertical, int horizontal, symbol el, boolean direction) {
        int currentY = vertical;
        int currentX = horizontal;
        int currentMax = 0;
        while (inBounds(currentY, currentX) && field[currentY][currentX] == el) {
            currentMax++;
            switch (line) {
                case leftRight:
                    if (direction) {
                        currentX++;
                    } else {
                        currentX--;
                    }
                    break;
                case upDown:
                    if (direction) {
                        currentY++;
                    } else {
                        currentY--;
                    }
                    break;
                case mainDiagonal:
                    if (direction) {
                        currentX++;
                        currentY++;
                    } else {
                        currentX--;
                        currentY--;
                    }
                    break;
                case secondaryDiagonal:
                    if (direction) {
                        currentX++;
                        currentY--;
                    } else {
                        currentX--;
                        currentY++;
                    }
                    break;
            }
        }
        return currentMax;
    }

    private int longestSequence(symbol el) {
        int max = 0;
        int currentMax;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (Line a : Line.values()) {
                    // вычитаю 1, т.к. в checkDirection считаю начальную клетку 2 раза
                    currentMax = checkDirection(a, i, j, el, true) - 1;
                    currentMax += checkDirection(a, i, j, el, false);
                    if (max < currentMax) max = currentMax;
                }
            }
        }
        return max;
    }

    public int longestCrossSequence() {
        return longestSequence(symbol.cross);
    }

    public int longestCircleSequence() {
        return longestSequence(symbol.circle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field1 = (Field) o;
        return size == field1.size &&
                Arrays.deepEquals(field, field1.field);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(field);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append(field[i][j]);
                result.append(" ");
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
