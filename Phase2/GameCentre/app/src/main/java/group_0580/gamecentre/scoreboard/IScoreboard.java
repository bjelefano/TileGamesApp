package group_0580.gamecentre.scoreboard;

import java.util.List;
import group_0580.gamecentre.game.Game;


/**
 * Interface for a retrievable list of scores,
 * queryable by game type and user.
 */
public interface IScoreboard {

    /**
     * Returns the high scores among all players of a game type.
     * Note that it returns null if the game type has not been saved.
     *
     * @param gameType The game type identifier
     * @return A sorted list of ScoreFields corresponding to the query, or null if none exists
     */
    List<ScoreField> getGlobalHighScores(String gameType);

    /**
     * Returns the high scores of a game type for a single user.
     * Note that it returns null if the user has not played this game.
     *
     * @param userName The user
     * @param gameType The game type identifier
     * @return A sorted list of ScoreFields corresponding to the query, or null if none exists
     */
    List<ScoreField> getUserHighScores(String userName, String gameType);

    /**
     * Saves a completed game to the data.
     * This method should be called whenever a game is finished
     *
     * @param game The completed game
     * @param user The user name
     */
    void addGame(Game game, String user);
}
