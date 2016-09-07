package com.mygdx.canyonbunnymain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CanyonBunnyMain extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    private OrthographicCamera camera;
    private Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");


        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(1, h / w);
        img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(img, 0, 0, 512, 275);
        sprite = new Sprite(region);
        sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());//Sets the size of the sprite when drawn, before scaling and rotation are applied.
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);//Sets the origin in relation to the sprite's position for scaling and rotation.
        sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);//Sets the position where the sprite will be drawn.


    }

    @Override
    public void dispose() {
        // dispose()----> Releases all resources of this object.
        batch.dispose();
        img.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }


    private float rot;

    @Override
    public void render() {
        // Gdx.gl.glClearColor(1, 0, 0, 1);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        final float degreesPerSecond = 100.0f;

        rot = (rot + Gdx.graphics.getDeltaTime() * degreesPerSecond) % 90;

        sprite.setRotation(rot);
        sprite.draw(batch);
        batch.draw(img, 0, 0);
        batch.end();
    }


}
