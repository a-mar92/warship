package com;

import com.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {


    private final Board board;

    public BoardTest() {
        board = new Board();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldAddSubmarine() throws Exception {

        board.addShip(0, 0, new Submarine());
        assertEquals(1, board.getShipsCount());
    }


    @Test
    public void shouldAddSubmarineOnField() throws Exception {

        board.addShip(0, 0, new Submarine());
        Field field = board.getField(0, 0);
        assertEquals(State.SHIP, field.getState());
    }

    @Test
    public void shouldAddDestroyerOnField() throws Exception {

        board.addShip(0, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
        Field field = board.getField(1, 0);
        assertEquals(State.SHIP, field.getState());
    }

    @Test(expected = IllegalMoveException.class)
    public void shouldNotBeAbleToAddFiveSubmarine() throws Exception {
        board.addShip(-1, 0, new Submarine());
    }

    @Test(expected = IllegalMoveException.class)
    public void shouldFailToAddOutsideX() throws Exception {

        board.addShip(0, 0, new Submarine());
        board.addShip(3, 3, new Submarine());
        board.addShip(5, 5, new Submarine());
        board.addShip(7, 7, new Submarine());
        board.addShip(9, 9, new Submarine());

    }

    @Test(expected = IllegalMoveException.class)
    public void shouldFailToAddOutsideY() throws Exception {
        Board board = new Board();

        board.addShip(0, 11, new Submarine());

    }

    @Test(expected = IllegalMoveException.class)
    public void shouldNotBeAbleToAddTwoBattleships() throws Exception {
        board.addShip(0, 0, new BattleShip(WarShip.Orientation.HORIZONTAL));
        board.addShip(0, 0, new BattleShip(WarShip.Orientation.HORIZONTAL));

    }

    @Test(expected = IllegalMoveException.class)
    public void shouldNotBeAbleToGetOutside() throws Exception {

        board.addShip(9, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
    }

    @Test(expected = IllegalMoveException.class)
    public void shouldNotBeAbleToBeClose() throws Exception {

        board.addShip(0, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
        board.addShip(2, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
    }

    @Test
    public void shouldMarkMiss() throws Exception {
        board.shoot(0, 0);
        //assert
        assertEquals(State.MISS, board.getField(0, 0).getState());
    }

    @Test
    public void shouldMarkAsHit() throws Exception {
        //arrange
        board.addShip(0,0, new Destoyer(WarShip.Orientation.HORIZONTAL));
        //act
        board.shoot(0, 0);
        //assert
        assertEquals(State.HIT, board.getField(0,0).getState());
    }

    @Test
    public void shouldMarkAsSunk() throws Exception {
        board.addShip(0, 0, new Destoyer(WarShip.Orientation.HORIZONTAL));
        board.shoot(0, 0);
        //act
        board.shoot(1, 0);
        //assert
        assertEquals(State.SUNK, board.getField(0, 0).getState());
        assertEquals(State.SUNK, board.getField(1, 0).getState());

    }

    @Test
    public void souldDecreaseShipOnBoard()throws Exception {
        //arrange
        board.addShip(0,0, new Submarine());
        //act
        board.shoot(0,0);
        //assert
        assertEquals(0,board.getShipsCount());
    }

//assert
    @Test(expected = IllegalMoveException.class)
    public void shouldNotBeAbleToShootTwice() throws Exception {
        //arrange
        board.shoot(0, 0);
        //act
        board.shoot(0,0);
}

    @Test
    public void shouldHaveAllShipsGenerated() throws  Exception{
        //arrange
        //act
        board.fillBoard();
        //assert
        assertEquals(10, board.getShipsCount());
    }
}