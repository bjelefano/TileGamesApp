package group_0661.gamecentre.snake;

import java.io.Serializable;

import group_0661.gamecentre.gameSystem.Game;
import group_0661.gamecentre.gameSystem.IGame;

public class SnakeGame extends Game implements Serializable{

    SnakeBoard board;

    public SnakeGame() {
        super(4, 0, false);

        board = new SnakeBoard(4, 4);
    }

    @Override
    public Integer[][] getBoard() {
        return board.getTiles();
    }
}
