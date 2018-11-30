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

        // Initialize the starting snake
        int startLen = 4;

        for (int i = 0; i < startLen; i++) {
            tiles[0][i] = 1;

            head[0] = 0;
            head[1] = i;

            body.addFirst(new int[] {head[0], head[1]});
        }

        // Initialize the cherry
        tiles[rows/2][cols/2] = 2;

        NUM_COLS = cols;

        // Start direction is to the right
        direction[0] = 0;
        direction[1] = 1;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public boolean update() {
        System.out.println("update");
        for (int[] c : body) {
            System.out.printf("%d, %d\n", c[0], c[1]);
        }

        int[] new_head = new int[2];
        new_head[0] = head[0] + direction[0];
        new_head[1] = head[1] + direction[1];
        if (SnakeBoard.isAlive(new_head, body, NUM_COLS)) {
            if (tiles[new_head[0]][new_head[1]] == 0) {
                body.addFirst(new_head);
                tiles[new_head[0]][new_head[1]] = 1;
                int[] tail = body.removeLast();
                tiles[tail[0]][tail[1]] = 0;

                head = new_head;
                return true;
            } else if (tiles[new_head[0]][new_head[1]] == 2) {
                body.addFirst(new_head);
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
