package group_0580.gamecentre;

import group_0580.gamecentre.scoreboard.ScoreField;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ToggleButton;
import android.util.Log;

import group_0580.gamecentre.scoreboard.Scoreboard;
import group_0580.gamecentre.game.Game;

public class LeaderBoardActivity extends ActionBarActivity {

    /**
     * An object containing all previous scores held by users
     */
    private Scoreboard scoreboard;
    /**
     * The username of the user who most recently completed a game (if logged in; equal to "" if that
     * isn't the case
     */
    private String user;
    /**
     * True iff the toggleButton is set to global. False otherwise
     */
    private boolean global;

    /**
     * Initializes the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        // Adds title to action bar
        configureActionBar("Leaderboards");

        // Initializes Scoreboard/Leaderboards
        this.scoreboard = new Scoreboard(LeaderBoardActivity.this);
        if (getIntent().getStringExtra("user") != null) {
            user = getIntent().getStringExtra("user");
            this.scoreboard.addGame((Game) getIntent().getSerializableExtra("game"), user);
        }

        // Initializes UI elements
        addToggleButtonListener();
        initGameTypes();
        populateScroll();
    }
    /**
     * Initializes the toggleButton that switches between global and local leaderboards.
     */
    private void addToggleButtonListener() {
        ToggleButton tButton = findViewById(R.id.localGlobal);
        tButton.setText("Local");
        tButton.setTextOff("Local");
        tButton.setTextOn("Global");

        tButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    global = true;
                    populateScroll();
                }
                else {
                    global = false;
                    populateScroll();
                }
            }
        });
    }
    /**
     * Initializes the spinner for choosing gametypes (for leaderboard access).
     */
    private void initGameTypes() {
        Spinner gameTypes = findViewById(R.id.game_types);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.game_types,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameTypes.setAdapter(adapter);

        gameTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { populateScroll(); }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populateScroll();
            }
        });

    }

    /**
     * Updates the ScrollView depending on the setting of the spinner and the toggle button. Displays
     * the content within the scoreboard instance.
     */
    private void populateScroll() {
        TextView ranks = findViewById(R.id.ranks);
        Spinner gameType = findViewById(R.id.game_types);
        String values = "";
        List<ScoreField> scores;
        int rank = 1;

        if (global) { scores = this.scoreboard.getGlobalHighScores(gameType.getSelectedItem().toString()); }
        else { scores = this.scoreboard.getUserHighScores(this.user, gameType.getSelectedItem().toString()); }

        if (scores != null) {
            for (ScoreField i : scores) {
                values += String.format("   %d. Username:   ", rank) + i.getUserName() + "   Score:   " + Integer.toString(i.getScore()) + "\n" + "\n";
                rank += 1;
            }
        }
        else {
            values = "   No Scores found.";
        }

        ranks.setText(values);
    }

}
