package group_0661.gamecentre.snake;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Arrays;

public class SnakeBoard implements Serializable {
    private Integer[][] tiles;

    public SnakeBoard(int cols, int rows) {
        tiles = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = 0;
            }
        }
        tiles[0][0] = 1;
        tiles[0][1] = 1;
        tiles[0][2] = 1;
        tiles[1][2] = 1;
        tiles[1][0] = 2;
    }

    public Integer[][] getTiles() {
        return tiles;
    }
}
/*
public class SnakeBoard implements Serializable{

    private int[] head;

    ArrayDeque<int[]> body;

    private int[] direction = new int[2];

    private int NUM_COLS;

    private Integer[][] tiles;

    public SnakeBoard(int cols, int rows) {
        tiles = new Integer[rows][cols];
        NUM_COLS = cols;
        direction[0] = 0;
        direction[1] = -1;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public boolean update() {
        int[] new_head = new int[2];
        new_head[0] = head[0] + direction[0];
        new_head[1] = head[1] + direction[1];
        if (SnakeBoard.isAlive(new_head, NUM_COLS, body)) {
            if (tiles[new_head[0]][new_head[1]] == 0) {
                body.addFirst(head);
                tiles[new_head[0]][new_head[1]] = 1;

            }
            else if (1 == 1) {

            }
        }
        return false;
    }

    public boolean makeMove(int[] move) {
        if (direction == move) {
            return false;
        }
        else {
            direction = move;
            return true;
        }
    }

    private static boolean isAlive(int[] head,
                                   int NUM_COLS, ArrayDeque<int[]> body) {
        if (!(head[0] >= 0 && head[0] < NUM_COLS && head[1] >= 0 && head[1] < NUM_COLS))
        {
            return false;
        }
        for (int[] pieces: body) {
            if (Arrays.equals(pieces, head)) {
                return false;
                }
        }
        return true;
    }


}
*/