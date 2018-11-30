package group_0661.gamecentre.snake;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayDeque;

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

    private Pair<Integer, Integer> head;

    ArrayDeque<Pair<Integer, Integer>> body;

    private Pair<Integer, Integer> direction = new Pair<Integer, Integer>(0, -1);

    private int NUM_COLS;

    private Integer[][] tiles;

    public SnakeBoard(int cols, int rows) {
        tiles = new Integer[rows][cols];
        NUM_COLS = cols;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public boolean update() {
        head = new Pair<>(head.first + direction.first, head.second + direction.second);
        if (SnakeBoard.isAlive(head, NUM_COLS, body)) {
            if (tiles[head.first][head.second] == 0) {
                tiles[head.first][head.second] = 1;

            }
            else if (1 == 1) {

            }
        }
        return false;
    }

    public boolean makeMove(Pair<Integer, Integer> move) {
        if (direction == move) {
            return false;
        }
        else {
            direction = move;
            return true;
        }
    }

    private static boolean isAlive(Pair<Integer, Integer> head,
                                   int NUM_COLS, ArrayDeque<Pair<Integer, Integer>> body) {
        if (!(head.first >= 0 && head.first < NUM_COLS && head.second >= 0 && head.second < NUM_COLS))
        {
            return false;
        }
        for (Pair<Integer, Integer> pieces: body) {
            if (head.equals(pieces)) {
                return false;
                }
        }
        return true;
    }


}
*/