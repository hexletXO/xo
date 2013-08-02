/*
Copyright (c) 2013 Evgeniy Dolgikh

See the file LICENSE for copying permission.
*/
package ua.org.atsy.xo;

public class Field {
    private static final int DEFAULT_FIELD_SIZE = 3;
    private static final char DEFAULT_FIELD_VALUE = ' ';
    private int fieldSize;
    private char gameField[][];
    public Field(int size) {
        gameField = new char[size][size];
        fieldSize = size;
        eraseField();
    }
    public Field() {
        this(DEFAULT_FIELD_SIZE);
    }
    public void eraseField() {
        for(int i = 0; i<fieldSize; i++) {
            for(int j = 0; j<fieldSize; j++)
                eraseCell(i,j);
        }
    }
    public void eraseCell(int row, int column) {
        setCellValue(row, column, DEFAULT_FIELD_VALUE);
    }
    public void setCellValue(int row, int column, char value) {
        gameField[row][column] = value;
    }
    public void setFieldSize(int size) {
        gameField = new char[size][size];
        fieldSize = size;
        eraseField();
    }
    public char getCellValue(int row, int column) {
        return gameField[row][column];
    }
}
