package io.kks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.kks.handlers.StateHandler;

public class MainGame extends ApplicationAdapter {

	@Override
	public void create () {
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update states and draw
		float dt = Gdx.graphics.getDeltaTime();
		StateHandler.getInstance().update(dt);
		StateHandler.getInstance().render();
	}

	@Override
	public void resize (int width, int height) {
		StateHandler.getInstance().resize(width, height);
	}

	@Override
	public void pause () {
		StateHandler.getInstance().pause();
	}

	@Override
	public void resume () {
		StateHandler.getInstance().resume();
	}

	@Override
	public void dispose () {
		StateHandler.getInstance().dispose();
	}
}