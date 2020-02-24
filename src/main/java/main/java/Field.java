package main.java;

import java.util.Arrays;
import java.util.Objects;

class Field {
    private int size;
    char[][] field;

    Field() { // конструктор по умолчанию (3 клетки)
        this.size = 3;
        field = new char[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = ' ';
            }
        }
    }

    Field(int size) { // конструктор для кастомного поля
        if (size < 3) throw new IllegalArgumentException("Wrong field size");
        this.size = size;
        field = new char[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = ' ';
            }
        }
    }

    boolean inBounds(int vertical, int horizontal) {
        return vertical < size && vertical >= 0 && horizontal < size && horizontal >= 0;
    }

    private void add(int vertical, int horizontal, char element) { // метод добавления элемента
        if (inBounds(vertical, horizontal) && field[vertical][horizontal] == ' ')
            field[vertical][horizontal] = element;
        else throw new IllegalArgumentException("Wrong input");
    }

    void addCross(int vertical, int horizontal) {
        add(vertical, horizontal, '☦');
    }

    void addCircle(int vertical, int horizontal) {
        add(vertical, horizontal, '✡');
    }

    void clear(int vertical, int horizontal) {
        if (inBounds(vertical, horizontal)) {
            if (field[vertical][horizontal] == ' ') {
                throw new IllegalArgumentException("The cell is already empty");
            }
            field[vertical][horizontal] = ' ';
        } else throw new IllegalArgumentException("Wrong input");
    }

    private int find(char element) {
        int max = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                // проверка влево-вправо
                int maxH = 0;
                if (field[y][x] == element) {
                    int right = x + 1;
                    int left = x - 1;
                    while (inBounds(y, right) && field[y][right] == element) { // проверка вправо
                        maxH++;
                        right++;
                    }
                    while (inBounds(y, left) && field[y][left] == element) { // проверка влево
                        maxH++;
                        left--;
                    }
                    maxH++;
                    if (maxH > max) max = maxH;

                    // проверка вверх-вниз
                    int maxV = 0;
                    int up = y - 1;
                    int down = y + 1;
                    while (inBounds(up, x) && field[up][x] == element) { // проверка вверх
                        maxV++;
                        up--;
                    }
                    while (inBounds(down, x) && field[down][x] == element) { // проверка ввниз
                        maxV++;
                        down++;
                    }
                    maxV++;
                    if (maxV > max) max = maxV;

                    int maxD1 = 0;
                    int maxD2 = 0;

                    // проверка по 1 диагонали
                    int upD = y - 1;
                    int rightD = x + 1;
                    int downD = y + 1;
                    int leftD = x - 1;
                    while (inBounds(upD, rightD) && field[upD][rightD] == element) { // проверка вправо-вверх
                        maxD1++;
                        upD--;
                        rightD++;
                    }
                    while (inBounds(downD, leftD) && field[downD][leftD] == element) { // проверка влево-вниз
                        maxD1++;
                        downD++;
                        leftD--;
                    }
                    maxD1++;
                    if (maxD1 > max) max = maxD1;

                    // проверка по 2 диагонали
                    upD = y - 1;
                    rightD = x + 1;
                    downD = y + 1;
                    leftD = x - 1;
                    while (inBounds(upD, leftD) && field[upD][leftD] == element) { // проверка влево-вверх
                        maxD2++;
                        upD--;
                        leftD--;
                    }
                    while (inBounds(downD, rightD) && field[downD][rightD] == element) { // проверка вправо-вниз
                        maxD2++;
                        downD++;
                        rightD++;
                    }
                    maxD2++;
                    if (maxD2 > max) max = maxD2;
                }
            }
        }
        return max;
    }

    int longestCrossSequence() {
        return find('☦');
    }

    int longestCircleSequence() {
        return find('✡');
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(field);
        return result;
    }

    // TODO: override equals & toString
}
