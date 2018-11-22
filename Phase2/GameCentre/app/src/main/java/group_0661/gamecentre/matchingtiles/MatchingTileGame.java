package group_0661.gamecentre.matchingtiles;

import java.io.Serializable;
import java.util.Observable;

import group_0661.gamecentre.gestures.Undo;
import group_0661.gamecentre.gameSystem.Game;

/**
 * The class for matching tile game.
 */
public class MatchingTileGame extends Game implements Serializable {

    /**
     * A new slidingtiles with given dimensions and undo limit.
     *
     * @param dimensions size of the board
     */
    public MatchingTileGame(int dimensions) {
        super(dimensions);
    }
}
