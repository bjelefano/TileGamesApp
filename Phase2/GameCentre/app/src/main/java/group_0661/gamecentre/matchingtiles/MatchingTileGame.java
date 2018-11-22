package group_0661.gamecentre.matchingtiles;

import java.io.Serializable;

import group_0661.gamecentre.gameSystem.Game;

/**
 * The class for matching tile game.
 */
public class MatchingTileGame extends Game implements Serializable {

    private MatchingTileBoard board;
    /**
     * A new matchingtiles with given dimensions and undo limit.
     *
     * @param dimensions size of the board
     */
    public MatchingTileGame(int dimensions) {
        super(dimensions);
        this.board = new MatchingTileBoard(dimensions);
    }

    /**
     * Return current state of tiles in board.
     *
     * @return current state of tiles in board
     */
    public Integer[][] getBoard() {return board.getState();}
}
