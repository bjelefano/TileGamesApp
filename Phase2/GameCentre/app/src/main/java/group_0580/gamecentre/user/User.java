package group_0580.gamecentre.user;

import group_0580.gamecentre.game.Game;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * The user account.
 */
public class User implements Serializable {

    /**
     * The account username.
     */
    private String userName;

    /**
     * The user's saved game.
     */
    private Game savedGame;

    /**
     * The background path.
     */
    private String backgroundPath;

    /**
     * A new user with given username.
     *
     * @param userName given string username
     */
    public User(String userName){
        this.userName = userName;
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
     * Set saved game to given game.
     *
     * @param game given game
     */
    public void setSavedGame(Game game){
        this.savedGame = game;
    }

    /**
     * Set background path to given background path.
     *
     * @param path given background path
     */
    public void setBackgroundPath(String path){
        this.backgroundPath = path;
    }

    /**
     * Return background path.
     *
     * @return background path
     */
    public String getBackgroundPath(){
        return this.backgroundPath;
    }

    /**
     * Return saved game.
     *
     * @return saved game
     */
    public Game getSavedGame() {
        return savedGame;
    }
}
