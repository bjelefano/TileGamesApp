package group_0661.gamecentre.gestures;

import group_0661.gamecentre.gameSystem.Game;
import group_0661.gamecentre.matchingtiles.MatchingTileGame;
import group_0661.gamecentre.snake.SnakeGame;

import android.content.Context;
import android.widget.Toast;
import android.util.Log;

/**
 * Controls the logic after a movement is made
 */
public class MovementController {

    /**
     * The current game
     */
    private Game game = null;

    /**
     * Setter for this game
     *
     * @param game the game
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
        if (game instanceof MatchingTileGame & position < game.getBoard().length * (game.getBoard().length+1)) {
            Log.d("touched", new Integer(position).toString());
//            Toast.makeText(context, "MatchingTileTouch",Toast.LENGTH_SHORT ).show();
            game.touchMove(position);
        } else if (game instanceof Game & position < game.getBoard().length * game.getBoard().length) {
            Log.d("touched", new Integer(position).toString());
//            Toast.makeText(context, "SlidingTileTouch",Toast.LENGTH_SHORT ).show();
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

    public void processSwipe(Context context, String direction) {
        if (game instanceof SnakeGame) {

            if (direction.equals("left")) {
                ((SnakeGame) game).makeMove("left");
            }
            if (direction.equals("right")) {
                ((SnakeGame) game).makeMove("left");
            }
            if (direction.equals("top")) {
                ((SnakeGame) game).makeMove("left");
            }
            if (direction.equals("bottom")) {
                ((SnakeGame) game).makeMove("left");
            }

        }
    }

}