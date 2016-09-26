package com.mygdx.canyonbunnymain;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class CanyonBunnyMain extends ApplicationAdapter {

    private Assets assets;

    private static final String TAG = CanyonBunnyMain.class.getName();
    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private boolean paused;


    @Override
    public void create() {
        // assets = new Assets();
        // Set Libgdx log level to DEBUG
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // load assets
        assets.instance.init(new AssetManager());
        // Initialize controller and rendere

        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);
        paused = false;

    }


    @Override
    public void render() {
        // Do not update game world when paused.

        if (!paused) {
            // Update game world by the time that has passed
            // since last rendered frame.
            worldController.update(Gdx.graphics.getDeltaTime());

        }
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);

        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();

    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }


    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        Assets.instance.init(new AssetManager());
        paused = false;
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }


}
