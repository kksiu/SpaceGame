package io.kks.handlers;

import io.kks.states.State;

import java.util.Stack;

public class StateHandler {

    private Stack<State> stateStack;

    private static StateHandler singleton = new StateHandler();

    /**
     * Use singleton for this class
     * @return The instance of the State Handler
     */
    public static StateHandler getInstance() {
        return singleton;
    }

    /**
     * Constructor for state handler, initializes stack
     */
    private StateHandler() {
        stateStack = new Stack<State>();
    }

    //region Update and Render calls
    /**
     * Draw all states in stack
     */
    public void render() {
        for (int i = 0; i < stateStack.size(); i++) {
            stateStack.get(i).render();
        }
    }

    /**
     * Update all states in stack
     * @param dt Delta time
     */
    public void update(float dt) {
        for (int i = 0; i < stateStack.size(); i++) {
            stateStack.get(i).update(dt);
        }
    }
    //endregion

    //region Change the stack
    /**
     * Add a state to the top of the stack
     * @param state State to add
     */
    public void pushState(State state) {
        addState(state, stateStack.size());
    }

    /**
     * Add a state to a certain position in the stack
     * @param state State to add
     * @param index Position to add the state
     */
    public void addState(State state, int index) {
        if (index < 0 || index > stateStack.size()) {
            return;
        }

        state.onCreate();
        stateStack.add(index, state);
    }

    /**
     * Remove the state from the top of the stack
     * @return Removed state
     */
    public State popState() {
        return removeState(stateStack.size() - 1);
    }

    /**
     * Remove a state from a certain location in the stack
     * @param index Location to remove state
     * @return Removed state
     */
    public State removeState(int index) {
        if (stateStack.size() <= 1 || outOfBounds(index)) {
            return null;
        }

        State removeState = stateStack.remove(index);
        removeState.onDestroy();

        return removeState;
    }

    /**
     * Replace the top of the stack with another state
     * @param state State to replace the one that will be removed
     * @return Removed state
     */
    public State replaceTopState(State state) {
        return replaceState(state, stateStack.size() - 1);
    }

    /**
     * Replace a certain location of the stack with another state
     * @param state State to replace the one that will be removed
     * @param index Location of the state to be removed and replaced
     * @return Removed state
     */
    public State replaceState(State state, int index) {
        State removedState = removeState(index);
        addState(state, index);

        return removedState;
    }
    //endregion

    //region Changes functionality of states
    /**
     * Hide a certain state
     * @param index Location of state to hide
     */
    public void hideState(int index) {
        if (outOfBounds(index)) {
            return;
        }

        stateStack.get(index).setVisible(false);
    }

    /**
     * Show a certain state
     * @param index Location of state to show
     */
    public void showState(int index) {
        if(outOfBounds(index)) {
            return;
        }

        stateStack.get(index).setVisible(true);
    }

    /**
     * Pause a certain state
     * @param index Location of state to pause
     */
    public void pauseState(int index) {
        if(outOfBounds(index)) {
            return;
        }

        stateStack.get(index).pause();
    }

    /**
     * Resume a certain state
     * @param index Location of state to resume
     */
    public void resumeState(int index) {
        if(outOfBounds(index)) {
            return;
        }

        stateStack.get(index).resume();
    }
    //endregion

    //region Edit functionality of all states
    /**
     * Resize all states to fit new width and height
     * @param width Width of window
     * @param height Height of window
     */
    public void resize(int width, int height) {
        for(int i = 0; i < stackSize(); i++) {
            stateStack.get(i).resize(width, height);
        }
    }

    /**
     * Pause all states
     */
    public void pause() {
        for(int i = 0; i < stackSize(); i++) {
            pauseState(i);
        }
    }

    /**
     * Resume all states
     */
    public void resume() {
        for(int i = 0; i < stackSize(); i++) {
            resumeState(i);
        }
    }

    /**
     * Remove all and dispose states
     */
    public void dispose() {
        while(stackSize() > 0) {
            removeState(0);
        }
    }
    //endregion

    //region Helper methods
    /**
     * @return The size of the stack
     */
    public int stackSize() {
        return stateStack.size();
    }

    /**
     * Method determines if an index is out of bounds in regards to the stack
     * @param index Index to check
     * @return True if index is out of bounds
     */
    private boolean outOfBounds(int index) {
        if(index < 0 || index >= stateStack.size()) {
            return true;
        }

        return false;
    }
    //endregion
}
