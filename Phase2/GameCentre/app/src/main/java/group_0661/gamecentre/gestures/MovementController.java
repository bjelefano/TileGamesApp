package group_0661.gamecentre.gestures;

import group_0661.gamecentre.slidingtiles.Game;
import android.content.Context;
import android.widget.Toast;
import android.util.Log;

/**
 * Controls the logic after a movement is made
 */
public class MovementController {

    /**
     * The current slidingtiles
     */
    private Game game = null;

    /**
     * Setter for this slidingtiles
     *
     * @param game the slidingtiles
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Process what happens after a single tap on screen
     *
     * @param context the context of the movement
     * @param position the location of tap
     * @param display true if movement recorded
     */
    public void processTapMovement(Context context, int position, boolean display) {
        if (position < game.getBoard().length * game.getBoard().length) {
            Log.d("touched", new Integer(position).toString());
            game.touchMove(position);
        }
        if (game.isWon()) {
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Process what happens when tapping on undo button
     *
     * @param context the context of the movement
     */
    public void processUndo(Context context) {
        if (game.getUndo().getSize() == 0) {
            Toast.makeText(context, "Undo Limit Reached", Toast.LENGTH_SHORT).show();
        } else {
            game.undo();
            Toast.makeText(context ,"Undo", Toast.LENGTH_SHORT).show();
        }
    }

}