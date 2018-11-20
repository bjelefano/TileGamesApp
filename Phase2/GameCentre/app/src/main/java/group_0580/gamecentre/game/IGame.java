package group_0580.gamecentre.game;

/**
 * The interface for any game

 */
public interface IGame {

    /**
     * Get the score of the game
     *
     * @return the recorded score
     */
    int getScore();

    /**
     * Get the board of the game
     *
     * @return the board
     */
    Board getBoard();

    /**
     * Get the type of game
     *
     * @return type of the game
     */
    String getType();
}
