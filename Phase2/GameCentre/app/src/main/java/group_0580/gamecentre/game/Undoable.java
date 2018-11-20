package group_0580.gamecentre.gestures;

/**
 * Interface implemented for games that support calls.
 */
interface Undoable<T> {

    /**
     * Remove the first element from stack of previous states
     *
     * @return the stored game state
     */
    public T pop();

    /**
     * Add game state to the stack of previous states
     *
     * @param obj the game state to be added to the stack
     */
    public void push(T obj);
}
