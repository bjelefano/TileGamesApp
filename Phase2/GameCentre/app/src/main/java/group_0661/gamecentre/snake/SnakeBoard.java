package group_0661.gamecentre.snake;

import android.util.Pair;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;


public class SnakeBoard implements Serializable{

    private int[] head = new int[2];

    private ArrayDeque<int[]> body = new ArrayDeque<>();

    private int[] direction = new int[2];

    private int NUM_COLS;

    private Integer[][] tiles;

    private Random rand = new Random();

    private boolean finished = false;

    private int score = 0;

    SnakeBoard(int cols, int rows) {
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
        tiles[2][0] = 2;
        int[] piece1 = {0,0};
        body.addLast(piece1);
        int[] piece2 = {0,1};
        body.addLast(piece2);
        int[] piece3 = {0,2};
        body.addLast(piece3);
        int[] piece4 = {1,2};
        body.addLast(piece4);

        NUM_COLS = cols;

        head[0] = 0;
        head[1] = 0;

        direction[0] = 1;
        direction[1] = 0;
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
                int[] tail = body.removeLast();
                tiles[tail[0]][tail[1]] = 0;

                head = new_head;
                return true;
            } else if (tiles[new_head[0]][new_head[1]] == 2) {
                body.addFirst(head);
                tiles[new_head[0]][new_head[1]] = 1;
                head = new_head;
                score += 1;
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
        finished = true;
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

    boolean isOver() {
        return finished;
    }

    int getScore() {
        return score;
    }
}
