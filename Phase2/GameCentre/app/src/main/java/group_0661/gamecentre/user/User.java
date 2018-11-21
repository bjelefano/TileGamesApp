package group_0661.gamecentre.user;

import group_0661.gamecentre.slidingtiles.Game;

import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

/**
 * The user account.
 */
public class User implements Serializable {

    /**
     * The account username.
     */
    private String userName;

    /**
     * The user's saved slidingtiles.
     */
    private Map<String, Game> savedGames;

    /**
     * The background path.
     */
    private Map<String, String> backgroundPath;

    /**
     * A new user with given username.
     *
     * @param userName given string username
     */
    public User(String userName){
        this.userName = userName;
        this.savedGames = new HashMap<>();
        this.backgroundPath = new HashMap<>();
    }

    /**
     * Return account username.
     *
     * @return account username
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Set saved slidingtiles to given slidingtiles.
     *
     * @param game given slidingtiles
     */
    public void setSavedGame(Game game){
        this.savedGames.put(game.getGameTitle(), game);
    }

    /**
     * Set background path to given background path.
     *
     * @param path given background path
     */
    public void setBackgroundPath(Game game, String path){
        this.backgroundPath.put(game.getGameTitle(), path);
    }

    /**
     * Return background path.
     *
     * @return background path
     */
    public String getBackgroundPath(String game){
        return this.backgroundPath.get(game);
    }

    /**
     * Return saved slidingtiles.
     *
     * @return saved slidingtiles
     */
    public Game getSavedGame(String game) {
        return this.savedGames.get(game);
    }
}
