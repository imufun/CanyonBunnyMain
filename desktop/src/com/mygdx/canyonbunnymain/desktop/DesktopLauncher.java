package com.mygdx.canyonbunnymain.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.mygdx.canyonbunnymain.CanyonBunnyMain;

public class DesktopLauncher {

    private static boolean rebuilAtls = true;
    private static boolean drawDebugOutLin = true;

    public static void main(String[] arg) {
        if (rebuilAtls) {
            TexturePacker.Settings settings = new TexturePacker.Settings();
            //The maxWidth and maxHeight variables of the Settings instance define the maximum dimensions (in pixels) for the texture atlas.
            // Always make sure that a single subimage does not exceed the maximum size of the atlas either in the width or height or both dimensions.
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.duplicatePadding = false;
            settings.debug = drawDebugOutLin;
            //We use the drawDebugOutline variable to set the value to debug. The static variables rebuildAtlas and drawDebugOutline are
            // there just for our convenience to make these two behavior controls stand out a bit more because we usually change these variables every now and then while debugging our game
            TexturePacker.process(settings, "images", "../android/assets/images", "canyonbunny.pack");

        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "CanyonBunny";
        config.width = 800;
        config.height = 480;
        new LwjglApplication(new CanyonBunnyMain(), config);


    }
}
