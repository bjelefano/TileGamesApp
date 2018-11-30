package group_0661.gamecentre.snake;

import java.io.Serializable;
import java.util.ArrayDeque;

import group_0661.gamecentre.gameSystem.Game;
import group_0661.gamecentre.gameSystem.IGame;
import android.util.Pair;

public class SnakeGame extends Game implements Serializable{

    private SnakeBoard board;


    public SnakeGame(int size) {
        super(0, 0, false);

        board = new SnakeBoard(size, size);
    }

    @Override
    public Integer[][] getBoard() {
        return board.getTiles();
    }

    public boolean update() {
        boolean success = board.update();
        return success;
    }

    public boolean isWon() {
        return board.isOver();
    }

    public boolean undo() {
        if (board.isOver()) {
            return false;
        }
        else {
            return true;
        }

    }
    public int getScore() {
        return board.getScore();
    }
}
