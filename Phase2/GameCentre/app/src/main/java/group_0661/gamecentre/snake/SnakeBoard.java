package group_0661.gamecentre.snake;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Random;
import java.util.Stack;


public class SnakeBoard implements Serializable{

    public static int EMPTY = 0;
    public static int WALL = 1;
    public static int BODY = 2;
    public static int CHERRY = 3;


    private int[] head = new int[2];

    private ArrayDeque<int[]> body = new ArrayDeque<>();

    private int[] direction = new int[2];

    private int rows, cols;

    private Integer[][] tiles;

    private Random rand = new Random();

    private boolean finished = false;

    private int score = 0;

    // Undo stacks
    private Stack<Integer[][]> tileStack = new Stack<>();
    private Stack<int[]> headStack = new Stack<>();;
    private Stack<ArrayDeque<int[]>> bodyStack = new Stack<>();;
    private Stack<int[]> directionStack = new Stack<>();;
    private Stack<Integer> scoreStack = new Stack<>();

    SnakeBoard(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        tiles = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = EMPTY;
            }
        }

        for (int i = 0; i < rows; i++) {
            tiles[i][0] = WALL;
            tiles[i][cols-1] = WALL;
        }

        for (int i = 0; i < cols; i++) {
            tiles[0][i] = WALL;
            tiles[rows-1][i] = WALL;
        }

        // Initialize the starting snake
        int startLen = 4;

        for (int i = 1; i < startLen+1; i++) {
            tiles[1][i] = BODY;

            head[0] = 1;
            head[1] = i;

            body.addFirst(new int[] {head[0], head[1]});
        }

        // Initialize the cherry
        //resetCherry();
        tiles[1][cols-3] = CHERRY;

        // Start direction is to the right
        direction[0] = 0;
        direction[1] = 1;
    }

    public Integer[][] getTiles() {
        return tiles;
    }

    public boolean update() {
//        System.out.println("update");
//        for (int[] c : body) {
//            System.out.printf("%d, %d\n", c[0], c[1]);
//        }

        pushState();

        int[] new_head = new int[2];
        new_head[0] = head[0] + direction[0];
        new_head[1] = head[1] + direction[1];

        int targetTile = tiles[new_head[0]][new_head[1]];

        if (targetTile == WALL) {
            finished = true;
            return false;
        }

        head = new_head;
        tiles[head[0]][head[1]] = BODY;
        body.addFirst(new int[] {head[0], head[1]});

        if (targetTile == CHERRY) {
            resetCherry();
            score++;
        } else {
            int[] tail = body.removeLast();
            tiles[tail[0]][tail[1]] = 0;
        }

        return true;
    }

    private void resetCherry() {
        do {
            int i = rand.nextInt(rows);
            int j = rand.nextInt(cols);
            if (tiles[i][j] == 0) {
                tiles[i][j] = CHERRY;
            }
            return;
        } while (true);
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

    boolean isOver() {
        return finished;
    }

    int getScore() {
        return score;
    }

    public boolean undo() {
        if (finished) return false;

        int nUndo = 3;

        for (int i = 0; i < nUndo; i++) {
            if (tileStack.empty()) return true;

            tiles = tileStack.pop();
            head = headStack.pop();
            body = bodyStack.pop();
            direction = directionStack.pop();
            score = scoreStack.pop();
        }

        return true;
    }

    private void pushState() {
        Integer[][] saveTiles = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                saveTiles[i][j] = tiles[i][j];
            }
        }

        tileStack.push(saveTiles);

        headStack.push(head.clone());
        bodyStack.push(body.clone());
        directionStack.push(direction.clone());
        scoreStack.push(score);
    }
}
