package ru.omsk.sch120.sun;

import com.badlogic.gdx.Game;

public class GameStarter extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
