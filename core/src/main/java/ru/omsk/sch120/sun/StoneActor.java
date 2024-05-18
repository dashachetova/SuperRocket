package ru.omsk.sch120.sun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StoneActor extends Actor {

    private Texture texture;
    private Polygon hitbox;

    public StoneActor(Texture tex, int x, int y){
        this.texture = tex;

        setPosition(x, y);
        float[] vertices = {
            0,0,
            0, texture.getHeight(),
            texture.getWidth(), texture.getHeight(),
            texture.getWidth(), 0
        };
        hitbox = new Polygon(vertices);
        hitbox.setPosition(x,y);
    }

    public Polygon getHitbox() {
        return hitbox;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY());
    }
}
