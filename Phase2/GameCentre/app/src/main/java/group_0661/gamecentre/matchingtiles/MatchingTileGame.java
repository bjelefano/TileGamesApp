package group_0661.gamecentre.matchingtiles;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.Serializable;
import android.widget.Toast;

import group_0661.gamecentre.gameSystem.Game;

/**
 * The class for matching tile game.
 */
public class MatchingTileGame extends Game implements Serializable {

    private MatchingTileBoard board;

    private int length;
    /**
     * A new matchingtiles with given dimensions and undo limit.
     *
     * @param dimensions size of the board
     */
    public MatchingTileGame(int dimensions) {
        super(dimensions);
        this.board = new MatchingTileBoard(dimensions);
        length = board.getState().length;
    }

    /**
     * Touch position on board to move
     *
     * @param position the position on the board being tapped
     * @return true if tapping is valid and recorded
     */
    public boolean touchMove(int position) {
        int size = board.getSize();
        if (position < length * (length -1)) {
            board.makeMove(position / (length-1), position % (length-1));
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Return number of moves made.
     *
     * @return number of moves made
     */
    public int getMoves() { return board.getMoves_made(); }

    /**
     * Return current state of tiles in board.
     *
     * @return current state of tiles in board
     */
    public Integer[][] getBoard() {return board.getState();}

    /**
     * Return dimensions of the board.
     *
     * @return dimensions of the board
     */
    public String getType() {
        if (getBoard().length == 4) {
            return "Easy";
        } else if (getBoard().length == 5) {
            return "Casual";
        } else {
            return "Hard";
        }
    }

    public String getGameTitle() { return "Matching Tiles"; }
}
