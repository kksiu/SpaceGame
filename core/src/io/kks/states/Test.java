package io.kks.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Test extends State {
    private World world;
    private Box2DDebugRenderer renderer;
    private Camera camera;

    // box2d
    private BodyDef bodyDef;
    private Body body;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    // Test some box2d stuff
    @Override
    public void onCreate() {
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        world = new World(new Vector2(0, -10), true);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(150, 150);
        body = world.createBody(bodyDef);

        // make a circle
        CircleShape circle = new CircleShape();
        circle.setRadius(7f);

        //create fixture
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1.f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.8f;

        fixture = body.createFixture(fixtureDef);

        circle.dispose();
    }

    @Override
    public void onUpdate(float dt) {
        renderer.render(world, camera.combined);
        world.step(dt, 6, 2);
    }

    @Override
    public void onRender() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void onDestroy() {

    }
}
