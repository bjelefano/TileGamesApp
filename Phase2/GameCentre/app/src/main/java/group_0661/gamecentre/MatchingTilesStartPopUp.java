package group_0661.gamecentre;

import group_0661.gamecentre.matchingtiles.MatchingTileGame;
import group_0661.gamecentre.user.UserManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import group_0661.gamecentre.user.UserManager;

/**
 * Manager for the matching tiles option selection layout.
 */
public class MatchingTilesStartPopUp extends PopUpActivity implements ServiceConnection {

    /**
     * The instantiation of the UserManager service
     */
    private UserManager userManager;

    /**
     * Board size
     */
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchingtiles_pop_up);

        configurePopUp(0.85, 0.85);

    }

    /**
     * Binds UserManager service to MatchingTilesStartPopUp when said activity starts
     */
    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MatchingTilesStartPopUp.this, UserManager.class), this, Context.BIND_AUTO_CREATE);
    }

    /**
     * Unbinds UserManager service to MatchingTilesStartPopUp when said activity stops
     */
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this);
    }

    /**
     * Creates an instantiation of UserManager service when UserManager establishes a connection with
     * this activity
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        UserManager.UserBinder binder = (UserManager.UserBinder) service;
        userManager = binder.getService();
    }

    /**
     * Initialize a listener for the radio buttons regarding board difficulty.
     */
    private boolean radioGroupListener() {
        RadioGroup boardSelect = findViewById(R.id.mboard_select);
        if (boardSelect.getCheckedRadioButtonId() == R.id.match_easy) {
            size = 3;
            Toast.makeText(MatchingTilesStartPopUp.this, "Game Start: Easy", Toast.LENGTH_SHORT).show();
            return true;
        } else if (boardSelect.getCheckedRadioButtonId() == R.id.match_casual) {
            size = 4;
            Toast.makeText(MatchingTilesStartPopUp.this, "Game Start: Normal", Toast.LENGTH_SHORT).show();
            return true;
        } else if (boardSelect.getCheckedRadioButtonId() == R.id.match_hard) {
            size = 5;
            Toast.makeText(MatchingTilesStartPopUp.this, "Game Start: Hard", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(MatchingTilesStartPopUp.this, "Please Select a Board Size", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * Nullifies UserManager once the UserManager service is disconnected
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        userManager = null;
    }

    public void addStartButtonListener(View view) {
        Button startButton = (Button) findViewById(R.id.start_mgame);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent newgame = initNewGame();
//                startActivity(newgame);
            }
        });
    }
}

//    /**
//     * Initialises the new matchingtiles
//     *
//     * @return an intent with new matchingtiles data
//     */
//    private Intent initNewGame() {
////        Intent startGame = new Intent(MatchingTilesStartPopUp.this, MatchingTilesActivity.class);
////        MatchingTileGame game = new MatchingTileGame(size, Integer.valueOf(((
////                EditText)findViewById(R.id.UndoInput)).getText().toString()), unlimited);
////        startGame.putExtra("Sliding Tiles", game);
////        startGame.putExtra("background_path", backgroundPath);
////
////        return startGame;
//    }
//}


