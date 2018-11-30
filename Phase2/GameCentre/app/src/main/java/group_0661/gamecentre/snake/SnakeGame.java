package group_0661.gamecentre.snake;

import java.io.Serializable;
import java.util.ArrayDeque;

import group_0661.gamecentre.gameSystem.Game;
import group_0661.gamecentre.gameSystem.IGame;
import android.util.Pair;

public class SnakeGame extends Game implements Serializable{

    private SnakeBoard board;


    public SnakeGame() {
        super(4, 0, false);

        board = new SnakeBoard(8, 8);
    }

    @Override
    public Integer[][] getBoard() {
        return board.getTiles();
    }

    public boolean update() {
        boolean success = board.update();
        return success;
    }

}
