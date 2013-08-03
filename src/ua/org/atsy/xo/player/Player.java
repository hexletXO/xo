/*
Copyright (c) 2013 Evgeniy Dolgikh

See the file LICENSE for copying permission.
*/
package ua.org.atsy.xo.player;

public class Player {
    private String name;
    private char symbol;
    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
