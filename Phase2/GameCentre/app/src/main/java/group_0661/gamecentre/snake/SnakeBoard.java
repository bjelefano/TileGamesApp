package group_0661.gamecentre.snake;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;

public class SnakeBoard implements Serializable{

    private int[] head;

    private ArrayDeque<int[]> body;

    private int[] direction = new int[2];

    private int NUM_COLS;

    private Integer[][] tiles;

    private Random rand = new Random();

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
        if (SnakeBoard.isAlive(new_head, body, NUM_COLS)) {
            if (tiles[new_head[0]][new_head[1]] == 0) {
                body.addFirst(head);
                tiles[new_head[0]][new_head[1]] = 1;
                body.removeFirst();
                head = new_head;
                return true;
            } else if (tiles[new_head[0]][new_head[1]] == 2) {
                body.addFirst(head);
                tiles[new_head[0]][new_head[1]] = 1;
                head = new_head;
                do {
                    int x_coord = rand.nextInt(NUM_COLS);
                    int y_coord = rand.nextInt(NUM_COLS);
                    if (tiles[x_coord][y_coord] == 0) {
                        tiles[x_coord][y_coord] = 2;
                        return true;
                    }
                } while (true);
            }
        }
        return false;
    }

    public boolean makeMove(int[] move) {
        if (direction[0] == move[0] && direction[1] == move[1]) {
            return false;
        }
        else {
            direction = move;
            return true;
        }
    }

    private static boolean isAlive(int[] head, ArrayDeque<int[]> body, int NUM_COLS) {
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
