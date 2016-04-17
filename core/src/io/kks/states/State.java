package io.kks.states;

public abstract class State {

    // True if state is paused (don't update)
    private boolean paused;

    // True if one can see the state
    private boolean visible;

    /**
     * Constructor makes the state default to visible and not paused.
     */
    public State() {
        visible = true;
        paused = false;
    }

    //region Getters and Setters
    /**
     * Pause the state so it won't update or unpause the state
     * @param paused True if paused
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * Get the paused value for this state
     * @return True if paused
     */
    public boolean getPaused() {
        return paused;
    }

    /**
     * Hide or show the state
     * @param visible True is visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Get if state is visible
     * @return True if visible
     */
    public boolean getVisible() {
        return visible;
    }
    //endregion

    //region Methods to be called by the State Handler
    /**
     * Pauses the state
     */
    public void pause() {
        if(getPaused()) {
            return;
        }

        setPaused(true);
        onPaused();
    }

    /**
     * Unpauses the state
     */
    public void resume() {
        if(!getPaused()) {
            return;
        }

        setPaused(false);
        onResume();
    }

    /**
     * Update the state
     * @param dt Delta time
     */
    public void update(float dt) {
        if(!getPaused()) {
            onUpdate(dt);
        }
    }

    /**
     * Draw the state
     */
    public void render() {
        if(getVisible()) {
            onRender();
        }
    }
    //endregion

    //region Methods implemented by every state
    /**
     * Call when state is added to stack in StateHandler
     */
    public abstract void onCreate();

    /**
     * Call to update state
     * @param dt Delta time
     */
    public abstract void onUpdate(float dt);

    /**
     * Call to draw state
     */
    public abstract void onRender();

    /**
     * Call when pausing state
     */
    public abstract void onPaused();

    /**
     * Call when State is resuming from paused
     */
    public abstract void onResume();

    /**
     * Resize state to match appropriate height and width of new window
     * @param width Width of window
     * @param height Height of window
     */
    public abstract void resize(int width, int height);

    /**
     * Call when state is being removed from stack in StateHandler
     */
    public abstract void onDestroy();
    //endregion
}
