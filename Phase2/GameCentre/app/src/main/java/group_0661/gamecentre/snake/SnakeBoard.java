package group_0661.gamecentre.snake;

import java.io.Serializable;

public class SnakeBoard implements Serializable{

    private Integer[][] tiles;

    public SnakeBoard(int cols, int rows) {
        tiles = new Integer[rows][cols];
    }

    public Integer[][] getTiles() {
        return tiles;
    }

}
