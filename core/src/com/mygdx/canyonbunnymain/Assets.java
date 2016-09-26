package com.mygdx.canyonbunnymain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by imran on 9/25/2016.
 */


/**
 * There is quite a lot going on in the preceding code, so let's break it down. First, notice that this class is using a design pattern called singleton. Simply put, a singleton class ensures that only a single instance of it will exist; hence the name singleton. This makes sense here because there is no reason to have multiple instances that point to the same resources. A singleton is implemented by defining a private constructor that prevents other classes from instantiating it. The instance variable holds the actual instance of the class. It uses the public static final modifiers that allows read-only access and is the one and only way to access this class. The staticness of this class allows us to access it from virtually anywhere in the game code without having to pass over its reference to every method where we will use it.
 * A singleton can be implemented to do either lazy or eager initialization. Using lazy initialization means that the instance is created only when it is requested for the very first time. Any subsequent requests will always yield the exact same instance. In contrast, eager initialization means that the instance is directly created on startup.
 **/

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    public AssetBunny bunny;
    public AssetRock rock;
    public AssetGoldCoin goldCoin;
    public AssetFeather feather;
    public AssetLevelDecoration levelDecoration;


    // singleton: prevent instantiation from other classes
    private Assets() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this); //set asset manger error handler
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, Texture.class); //load texture atlas
        assetManager.finishLoading();//start loading assets and wait until finished
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, " asset: " + a);


        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        // enable texture filtering for pixel smoothing  for (Texture t : atlas.getTextures()) {       t.setFilter(TextureFilter.Linear, TextureFilter.Linear);  }

        // create game resource objects
        bunny = new AssetBunny(atlas);
        rock = new AssetRock(atlas);
        goldCoin = new AssetGoldCoin(atlas);
        feather = new AssetFeather(atlas);
        levelDecoration = new AssetLevelDecoration(atlas);

    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception) throwable);
    }

    //    @Override
//    public void error(String filename, Class type, Throwable throwable) {
//        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception) throwable);
//    }
    @Override
    public void dispose() {
        assetManager.dispose();
    }


    public class AssetBunny {
        public final TextureAtlas.AtlasRegion head;

        public AssetBunny(TextureAtlas atlas) {
            head = atlas.findRegion("bunny_head");
        }
    }

    public class AssetRock {
        public final TextureAtlas.AtlasRegion edge;
        public final TextureAtlas.AtlasRegion middle;

        public AssetRock(TextureAtlas atlas) {
            edge = atlas.findRegion("rock_edge");
            middle = atlas.findRegion("rock_middle");
        }
    }

    public class AssetGoldCoin {
        public final TextureAtlas.AtlasRegion goldCoin;

        public AssetGoldCoin(TextureAtlas atlas) {
            goldCoin = atlas.findRegion("item_gold_coin");
        }
    }

    public class AssetFeather {
        public final TextureAtlas.AtlasRegion feathr;

        public AssetFeather(TextureAtlas atlas) {
            feathr = atlas.findRegion("item_feather");
        }
    }

    public class AssetLevelDecoration {
        public final TextureAtlas.AtlasRegion cloud01;
        public final TextureAtlas.AtlasRegion cloud02;
        public final TextureAtlas.AtlasRegion cloud03;
        public final TextureAtlas.AtlasRegion mountainLeft;
        public final TextureAtlas.AtlasRegion mountainRight;
        public final TextureAtlas.AtlasRegion waterOverlay;

        public AssetLevelDecoration(TextureAtlas atlas) {
            cloud01 = atlas.findRegion("cloud01");
            cloud02 = atlas.findRegion("cloud02");
            cloud03 = atlas.findRegion("cloud03");
            mountainLeft = atlas.findRegion("mountain_left");
            mountainRight = atlas.findRegion("mountain_right");
            waterOverlay = atlas.findRegion("water_overlay");
        }
    }
}
