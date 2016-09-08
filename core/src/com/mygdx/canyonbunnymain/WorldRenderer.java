package com.mygdx.canyonbunnymain;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by imran on 9/7/2016.
 */
public class WorldRenderer implements Disposable {


    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;


    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        //WorldRenderer worldRenderer  = new WorldRenderer(worldController);
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
    }

    public void render() {
        renderTestObject();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_WIDTH / height) * width;
        camera.update();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void renderTestObject() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Sprite sprite : worldController.testSprites) {
            sprite.draw(batch);
        }
        batch.end();
    }
}
