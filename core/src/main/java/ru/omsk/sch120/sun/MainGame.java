package ru.omsk.sch120.sun;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    public static final int WORLD_WIDTH= 1280;
    public static final int WORLD_HEIGHT= 720;

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
