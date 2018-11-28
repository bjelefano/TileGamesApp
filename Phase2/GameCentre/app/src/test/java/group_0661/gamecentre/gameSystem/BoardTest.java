package group_0661.gamecentre.gameSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    private Board testBoard;
    private Integer[][] initialState;

    @Before
    public void setUp() {
        initCompletedBoard();
    }

    private void initCompletedBoard() {
        testBoard = new Board(5);
        for (int i = 0; i < 25; i++) {
            initialState[i / 5][i % 5] = i;
        }
        testBoard.setBoard(initialState);
    }

    @Test
    public void getTileTrue() {
        assertEquals(25,testBoard.getTile(4,4));
        assertNotEquals(25, testBoard.getTile(0,0));
    }

    @Test
    public void makeMove() {
        assertTrue(testBoard.makeMove(4,3,false));
        assertTrue(testBoard.makeMove(3,3,false));
        assertTrue(testBoard.makeMove(3,4,false));
        assertTrue(testBoard.makeMove(4,4,false));
        assertFalse(testBoard.makeMove(4,4,false));
        assertFalse(testBoard.makeMove(0,0,false));
    }

    @Test
    public void getPreviousMoves() {
        int[] prevMove = {4, 3};
        int[] noPrevMove = {-1, -1};
        testBoard.makeMove(4,3,false);
        assertEquals(prevMove, testBoard.getPreviousMoves());
        testBoard.makeMove(4,4,false);
        assertNotEquals(prevMove, testBoard.getPreviousMoves());
        assertEquals(noPrevMove, testBoard.getPreviousMoves());
    }

    @Test
    public void getState() {
        Integer[][] nonEqualState = initialState;
        assertArrayEquals(initialState, testBoard.getState());
        nonEqualState[0][0] = 4;
        nonEqualState[0][3] = 1;
        assertNotEquals(initialState, testBoard.getState());
    }

    @Test
    public void getSize() {
        assertEquals(5, testBoard.getSize());
        assertNotEquals(2, testBoard.getSize());
    }

    @Test
    public void getMoves_made() {
        assertNotEquals(4, testBoard.getMoves_made());
        testBoard.makeMove(4,3,false);
        testBoard.makeMove(3,3,false);
        testBoard.makeMove(3,4,false);
        testBoard.makeMove(4,4,false);
        assertEquals(4, testBoard.getMoves_made());
    }

    @Test
    public void puzzleSolvedFalse() {
        assertTrue(testBoard.puzzleSolved());
        testBoard.makeMove(4,3,false);
        assertFalse(testBoard.puzzleSolved());
    }
}