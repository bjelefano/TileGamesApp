package group_0580.gamecentre.gestures;

/*
Adapted from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/GestureDetectGridView.java

This extension of GridView contains built in logic for handling swipes between buttons
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;
import android.util.Log;

import group_0580.gamecentre.game.Game;

/**
 * Initialize gesture detection
 */
public class GestureDetectGridView extends GridView {

    /**
     * The minimum swiping distance
     */
    public static final int SWIPE_MIN_DISTANCE = 100;

    /**
     * The maximum distance off swiping path
     */
    public static final int SWIPE_MAX_OFF_PATH = 100;

    /**
     * Maximum velocity for a swipe
     */
    public static final int SWIPE_THRESHOLD_VELOCITY = 100;

    /**
     * Tool for detecting gesture
     */
    private GestureDetector gDetector;

    /**
     * Tool for controlling a movement
     */
    private MovementController mController;

    /**
     * Check if there is a fling
     */
    private boolean mFlingConfirmed = false;

    /**
     * X coordinate of a touch on screen
     */
    private float mTouchX;

    /**
     * Y coordinate of a touch on screen
     */
    private float mTouchY;

    /**
     * The game being monitored
     */
    private Game game;

    /**
     * Initialize gesture detector within context
     *
     * @param context the context of the game
     */
    public GestureDetectGridView(Context context) {
        super(context);
        init(context);
    }

    /**
     * Initialize gesture detector with context and attributes
     *
     * @param context the context of the game
     * @param attrs the attribute set of the game
     */
    public GestureDetectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Initialize the gesture detector with context, attributes, and style attribute
     *
     * @param context the context of game
     * @param attrs the attribute set of the game
     * @param defStyleAttr style attribute of the game
     */
    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) // API 21
    public GestureDetectGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**
     * Initialize the movement control logic
     *
     * @param context the context of the game
     */
    private void init(final Context context) {
        mController = new MovementController();
        gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectGridView.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));
                mController.processTapMovement(context, position, true);
                return true;
            }
        });
    }

    /**
     * Detects the movement made
     *
     * @param ev the motion recorded
     * @return true if movement logged
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        gDetector.onTouchEvent(ev);

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mFlingConfirmed = false;
        } else if (action == MotionEvent.ACTION_DOWN) {
            mTouchX = ev.getX();
            mTouchY = ev.getY();
        } else {

            if (mFlingConfirmed) {
                return true;
            }

            float dX = (Math.abs(ev.getX() - mTouchX));
            float dY = (Math.abs(ev.getY() - mTouchY));
            if ((dX > SWIPE_MIN_DISTANCE) || (dY > SWIPE_MIN_DISTANCE)) {
                mFlingConfirmed = true;
                return true;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * Process what happens when screen touched
     *
     * @param ev the motion recorder
     * @return true if movement logged
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gDetector.onTouchEvent(ev);
    }

    /**
     * Set the current game being monitored
     *
     * @param game the game being played
     */
    public void setGame(Game game) {
        this.game = game;
        mController.setGame(game);
    }

    /**
     * Process undo when detected
     *
     * @param context the context of the game
     */
    public void undo(Context context) {
        mController.processUndo(context);
    }

}