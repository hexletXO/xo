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
    private char winner = DEFAULT_FIELD_VALUE;
    private int emptyCells = 0;
    public Field(int size) {
        gameField = new char[size][size];
        fieldSize = size;
        eraseField();
    }
    public Field() {
        this(DEFAULT_FIELD_SIZE);
    }
    public void eraseField() {
        winner = DEFAULT_FIELD_VALUE;
        for(int i = 0; i<fieldSize; i++) {
            for(int j = 0; j<fieldSize; j++)
                eraseCell(i,j);
        }
    }
    private void eraseCell(int row, int column) {
        setCellValue(row, column, DEFAULT_FIELD_VALUE);
    }
    public void setCellValue(int row, int column, char value) {
        if(value == DEFAULT_FIELD_VALUE)
            emptyCells++;
        else
            emptyCells--;
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

    public int getFieldSize() {
        return fieldSize;
    }

    private boolean checWinRow(int rowNum) {
        if(gameField[rowNum][0] == DEFAULT_FIELD_VALUE)
            return false;
        char tmp = gameField[rowNum][0];
        for(int i = 1; i < fieldSize; i++) {
            if(gameField[rowNum][i] != tmp || gameField[rowNum][i] == DEFAULT_FIELD_VALUE)
                return false;
        }
        return true;
    }
    private boolean checkWinColumn(int colNum) {
        if(gameField[0][colNum] == DEFAULT_FIELD_VALUE)
            return false;
        char tmp = gameField[0][colNum];
        for(int i = 1; i < fieldSize; i++) {
            if(gameField[i][colNum] != tmp || gameField[i][colNum] == DEFAULT_FIELD_VALUE)
                return false;
        }
        return true;
    }
    private boolean checkWinDiagonal(int startFrom) {
        if(gameField[startFrom][0] == DEFAULT_FIELD_VALUE)
            return false;
        char tmp = gameField[startFrom][0];
        int j = startFrom;
        for(int i = 0; i < fieldSize-1; i++) {
            if(startFrom == 0) {
                if(gameField[i+1][j+1] != tmp || gameField[i+1][j+1] == DEFAULT_FIELD_VALUE)
                    return false;
                j++;
            }
            else {
                if(gameField[j-1][i+1] != tmp || gameField[j-1][i+1] == DEFAULT_FIELD_VALUE)
                    return false;
                j--;
            }
        }
        return true;
    }
    public boolean isDraw(boolean skipWinCheck) {
        if(skipWinCheck) {
            if(emptyCells == 0)
                return true;
        } else {
            if(emptyCells == 0 && !haveWinner())
                return true;
        }
        return false;
    }
    public boolean haveWinner() {
        //is enough turns for win combination?
        if(emptyCells > fieldSize*fieldSize-fieldSize+fieldSize/2)
            return false;
        if(checkWinDiagonal(0)) {
            winner = gameField[0][0];
            return true;
        }
        if(checkWinDiagonal(fieldSize-1)) {
            winner = gameField[fieldSize-1][0];
            return true;
        }
        for(int i = 0; i < fieldSize; i++) {
            if(checWinRow(i)) {
                winner = gameField[i][0];
                return true;
            }
            if(checkWinColumn(i)) {
                winner = gameField[0][i];
                return true;
            }
        }
        return false;
    }
    public char getWinner() {
        return winner;
    }
    public void printField() {
        String tmp = "";
        for(int i = fieldSize-1; i >= 0; i--) {
            tmp += "[" + (i+1) + "]";
            for(int j = 0; j < fieldSize; j++) {
                tmp += "[" + gameField[i][j] + "]";
            }
            tmp += "\n";
        }
        System.out.print(tmp + "   ");
        for(int k = 1; k <= fieldSize; k++)
            System.out.print("["+k+"]");
        System.out.println();
    }
}
