package com;

public interface Ship {
    int getDecksCount();

    void hit ();

    boolean isSunk();
    void setOnField(Field field, int deckNo);
    WarShip.Orientation getOrientation();
}
