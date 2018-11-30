package group_0661.gamecentre.snake;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayDeque;

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
        if (SnakeBoard.notDead(head, tiles)) {

        }
        else {
            return false;
        }
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

    private static boolean notDead(Pair<Integer, Integer> head, Integer[][] tiles, int NUM_COLS, ) {
        if (head.first >= 0 && head.first < NUM_COLS && head.second >= 0 && head.second < NUM_COLS) {
            for (:
                 ) {

            }
        }
    }


}
