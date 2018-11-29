package group_0661.gamecentre.snake;

import group_0661.gamecentre.gameSystem.Game;
import group_0661.gamecentre.gameSystem.IGame;

public class SnakeGame implements IGame {

    private SnakeBoard board;

    public SnakeGame(int size) {
        this.board = new SnakeBoard(size);
    }

    @Override
    public String getGameTitle() {
        return "Snake";
    }

}
