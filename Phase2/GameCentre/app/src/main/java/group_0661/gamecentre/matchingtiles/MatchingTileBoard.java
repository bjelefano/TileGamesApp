package group_0661.gamecentre.matchingtiles;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * The sliding tiles board.
 */
public class MatchingTileBoard extends Observable implements Serializable{

    /**
     * The number of columns.
     */
    private int columns;

    /**
     * The number of rows.
     */
    private int rows;


    /**
     * The tiles on the board in row-major order.
     */
    private Integer[][] tiles;

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

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    private void swapTiles(int row1, int col1, int row2, int col2) {
        int temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        setChanged();
        notifyObservers();
    }

    /**
     * Check if valid move then swap the tiles. If not an undo call, add row and col of
     * the last location of the blank tile to previousMoves.
     *
     * @param row the tile row
     * @param column the tile column
     * @return true if tiles are successfully swapped
     */
    public boolean makeMove(int row, int column) {
        List<Pair<Integer, Integer>> toCheck = getTilesToCheck(row, column);
        for (Pair<Integer, Integer> indices: toCheck) {
            if (tiles[indices.first][indices.second] == columns * rows) {
                swapTiles(row, column, indices.first, indices.second);
                moves_made += 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Return list of (row, col) of tiles that are candidates for swapping.
     *
     * @param row the current tile row
     * @param column the current tile column
     * @return list of (row, col) of tiles that are candidates for swapping
     */
    private List<Pair<Integer, Integer>> getTilesToCheck(int row, int column) {
        List<Pair<Integer, Integer>> indices = new ArrayList<>();
        if (column != 0) {
            Pair<Integer, Integer> toAdd = new Pair<>(row, column - 1);
            indices.add(toAdd);
        }
        if (column != rows - 1) {
            Pair<Integer, Integer> toAdd = new Pair<>(row, column + 1);
            indices.add(toAdd);
        }
        if (row != 0) {
            Pair<Integer, Integer> toAdd = new Pair<>(row - 1, column);
            indices.add(toAdd);
        }
        if (row != rows - 1) {
            Pair<Integer, Integer> toAdd = new Pair<>(row + 1, column);
            indices.add(toAdd);
        }

        return indices;
    }

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
