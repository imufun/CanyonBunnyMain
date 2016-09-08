package com.mygdx.canyonbunnymain;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by imran on 9/7/2016.
 */
public class WorldController {

    public Sprite[] testSprites;
    public int selectedSprited;

    private static final String TAG = WorldController.class.getName();

    public WorldController() {
        init();
    }

    private void init() {
        initTestObjects();

    }


    public void initTestObjects() {
        // Create new array for 5 sprites
        testSprites = new Sprite[50];
        // Create empty POT-sized Pixmap with 8 bit RGBA pixel data
        int width = 32;
        int height = 32;
        Pixmap pixmap = createProceduralPixmap(width, height);
        Texture texture = new Texture(pixmap);

        for (int i = 0; i < testSprites.length; i++) {
            Sprite spr = new Sprite(texture);
            spr.setSize(1, 1);
            spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);  // Calculate random position for sprite
            float randomX = MathUtils.random(-2.0f, 2.0f);
            float randomY = MathUtils.random(-2.0f, 2.0f);
            spr.setPosition(randomX, randomY);
            testSprites[i] = spr;
        }// Set first sprite as selected one
        selectedSprited = 0;
    }

    private Pixmap createProceduralPixmap(int width, int height) {

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB888);
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();  // Draw a yellow-colored X shape on square
        pixmap.setColor(1, 1, 0, 1);
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height); // Draw a cyan-colored border around square
        pixmap.setColor(0, 1, 1, 1);
        pixmap.drawRectangle(0, 0, width, height);
        return pixmap;

    }

    public void update(float deltaTime) {
        updateTestObjects(deltaTime);
    }


    public void updateTestObjects(float deltaTime) {
        // Get current rotation from selected sprite
        float rotation = testSprites[selectedSprited].getRotation();
        // Rotate sprite by 90 degrees per second
        rotation += 90 * deltaTime;
        // Wrap around at 360 degrees
        rotation %= 360;
        // Set new rotation value to selected sprite
        testSprites[selectedSprited].setRotation(rotation);
    }


}
