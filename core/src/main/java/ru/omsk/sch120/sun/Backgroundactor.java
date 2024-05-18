package ru.omsk.sch120.sun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Backgroundactor extends Actor {

    private Texture texture;

    public Backgroundactor(){
        texture = new Texture("space.jpg" );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, 0, 0, MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT);
    }
}
