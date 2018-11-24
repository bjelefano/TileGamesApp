package group_0661.gamecentre.matchingtiles;

//import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The sliding tiles board.
 */
public class MatchingTileBoard extends Observable implements Serializable{

    /**
     * The number of columns.
     */
    private int columns;

    private int awaiting_tile = 0;
    /**
     * The tile being flipped;
     */
    private int flipped;
    /**
     * The number of rows.
     */
    private int rows;

    private Timer timer;
    /**
     * The tiles on the board in row-major order.
     */
    private Integer[][] tiles;

    /**
     * The matching pair of tiles.
     */
//    private Pair<Integer,Integer>[] pairs;
    /**
     * The number of moves made.
     */
    private int moves_made = 0;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == rows * columns
     *
     * @param column the number of rows of the board
     */
    MatchingTileBoard(int column) {
        rows = column+1;
        columns = column;
        int counter = 0;
        tiles = new Integer[rows][columns];
        ArrayList<Integer> tileslist = new ArrayList<>();
        for (int i = 0; i < rows * columns; i++) {
            tileslist.add(new Integer(i + 1));
        }
//        Collections.shuffle(tileslist);
        for (int i = 0; i < rows; i++) {
            for (int d = 0; d < columns; d++) {
                tiles[i][d] = tileslist.get(counter);
                counter++;
            }
//            tiles[i / rows][i % columns] = tileslist.get(i);
        }
//        generatePairs();
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return (rows * columns);
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    int getTile(int row, int col) {
        return tiles[row][col];
    }

//    void generatePairs(){
//        pairs = new Pair[numTiles()/2];
//        for (int i = 1; i <= numTiles()/2; i++) {
//            pairs[i-1] = new Pair<Integer, Integer>(i*2-1,i*2);
//        }
//    }

    /**
     * Check if valid move then swap the tiles. If not an undo call, add row and col of
     * the last location of the blank tile to previousMoves.
     *
     * @param row row being tapped
     * @param column column being tapped
     * @return true if tiles are successfully swapped
     */
    public boolean makeMove(final int row, final int column) {
        if (tiles[row][column] > 0 ) {
            flipped = tiles[row][column];
            MatchingTileBoard.this.tiles[row][column] = -flipped;
            if (awaiting_tile != flipped) {
                checkFlip(flipped);
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        MatchingTileBoard.this.tiles[row][column] = flipped;
                        awaiting_tile = 0;
                        setChanged();
                        notifyObservers();
                    }
                };
                timer = new Timer("Timer");
                timer.schedule(task, 2*1000);
                moves_made += 1;
            }
        }
        return true;
    }

    public boolean checkFlip(Integer flipped) {
        if (flipped % 2 == 1) {
            awaiting_tile = flipped + 1;
        } else {
            awaiting_tile = flipped - 1;
        }
        return true;
    }
//
//    public int timeMove(int row, int column,Integer tile) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                tiles[row][column] = - flipped;
//            }
//        });
//    }


    /**
     * Return tiles on board in row-major order.
     *
     * @return tiles on board in row-major order
     */
    Integer[][] getState() {return tiles;}

    /**
     * Return number of columns in the board.
     *
     * @return number of columns in the board
     */
    public int getSize() {return columns;}

    /**
     * Return number of moves made.
     *
     * @return number of moves made
     */
    public int getMoves_made() {return moves_made;}

    /**
     * Check if the tiles slidingtiles is solved.
     *
     * @return true if the slidingtiles is solved, false if the slidingtiles is not solved.
     */
    boolean puzzleSolved() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (getTile(i, j) != (i * rows) + j + 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return the slidingtiles state.
     *
     * @return the board with the tiles it contains
     */
    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

}
