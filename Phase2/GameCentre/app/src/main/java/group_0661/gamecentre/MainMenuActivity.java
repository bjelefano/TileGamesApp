package group_0661.gamecentre;


import group_0661.gamecentre.user.UserManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The initial activity for the sliding puzzle tile slidingtiles.
 */
public class MainMenuActivity extends AppCompatActivity implements ServiceConnection {
    /**
     * Request code for account registry/login.
     */
    private final int ACCOUNT_REQUEST = 1;
    /**
     * Instance of userManager service.
     */
    private UserManager userManager;

    /**
     * Initializes the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Adds button listeners
        addStartButtonListener();
        addLoadButtonListener();
        addLoginButtonListener();

        // Starts running service in the background to be accessed by other activities
        startService(new Intent(MainMenuActivity.this, UserManager.class));
        }

    /**
     * Binds UserManager service to MainMenuActivity when said activity starts
     */
    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(MainMenuActivity.this, UserManager.class), this, Context.BIND_AUTO_CREATE);
    }

    /**
     * Unbinds UserManager service to MainMenuActivity when said activity stops
     */
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this);
    }

    /**
     * Creates an instantiation of UserManager service when UserManager establishes a connection with
     * MainMenuActivity. Updates login UI if a user is logged in
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        UserManager.UserBinder binder = (UserManager.UserBinder) service;
        userManager = binder.getService();
        if (userManager.getStatus()) {
            updateLoginUI();
        }
    }

    /**
     * Resets UserManager upon disconnect with service UserManager
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        userManager = null;
    }

    /**
     * Returns the result of AccountPopUp (Login/User Details).
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnData) {
        super.onActivityResult(requestCode,resultCode,returnData);
        if (requestCode == ACCOUNT_REQUEST) {
            if (resultCode == RESULT_OK) {
                try {
                    updateLoginUI();
                } catch(NullPointerException e) {
                    Log.e("Login Attempt", "Null Serializable");
                }
            }
        }
    }

    /**
     * Changes the Login button to a logout button when the user logs in. Creates a new listener for
     * the aforementioned button that logs out the user when pressed. Also adds a welcome at the top right
     * depending on what user is logged in.
     */
    private void updateLoginUI() {
        Button logoutButton = findViewById(R.id.account);
        logoutButton.setText(R.string.logout);
        ((TextView) findViewById(R.id.welcome)).setText("Welcome " + userManager.getName());
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logs out user, resets UI to its initial state
                userManager.signOut();
                ((TextView) findViewById(R.id.welcome)).setText("");
                addLoginButtonListener();
            }
        });
    }

    /**
     * Activates the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.new_game);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popUp = new Intent(MainMenuActivity.this, StartGamePopUp.class);
                startActivity(popUp);
            }
        });
    }
    /**
     * Activates the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.load_game);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userManager != null & userManager.getStatus()) {
                    if (userManager.getSavedGame() != null) {
                        startActivity(initLoadGame());
                    }
                    else {
                        Toast.makeText(MainMenuActivity.this, "No saved slidingtiles found", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainMenuActivity.this, "You must login to load a previous save", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /**
     * Activates the Login Button.
     */
    private void addLoginButtonListener() {
        Button loginButton = findViewById(R.id.account);
        loginButton.setText(R.string.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popUp = new Intent(MainMenuActivity.this, AccountPopUp.class);
                startActivityForResult(popUp, ACCOUNT_REQUEST);
            }
        });
    }

    /**
     * Initialises the loaded slidingtiles
     *
     * @return an intent with loaded slidingtiles data
     */
    private Intent initLoadGame() {
        Intent load = new Intent(MainMenuActivity.this, SlidingTilesActivity.class);
        // Cuts up saved image to recreate the saved board
        ImageToTiles initBoard = new ImageToTiles(userManager.loadUserImage(false), userManager.getSavedGame().getBoard().length);
        initBoard.saveTiles(MainMenuActivity.this);

        // Adding extra required data to be passed to SlidingTilesActivity
        load.putExtra("background_path", userManager.getBackgroundPath());
        load.putExtra("slidingtiles", userManager.getSavedGame());

        return load;
    }

}
