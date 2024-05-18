package ru.omsk.sch120.sun;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AstronautActor extends Actor {

    private TextureRegion[] textureArray;
    private TextureRegion texture;
    private Polygon hitbox;

    public AstronautActor(TextureRegion[] textureArray, float startX, float startY){
        this.textureArray = textureArray;
        int index = MathUtils.random(0, 3);
        texture = textureArray[index];
        setWidth(80);
        setHeight(80);
        setX(startX);
        setY(startY);

        float[] vertices ={
            0,0,
            0,getHeight(),
            getWidth(), getHeight(),
            getWidth(),0
        };
        hitbox = new Polygon(vertices);

    }

    public Polygon getHitbox() {
        return hitbox;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void reSpawn(){
        float nextX = MathUtils.random(0, MainGame.WORLD_WIDTH - getWidth());
        float nextY = MathUtils.random(0, MainGame.WORLD_HEIGHT - getHeight());
        setPosition(nextX, nextY);
        hitbox.setPosition(nextX,nextY);
        int index = MathUtils.random(0, 3);
        texture = textureArray[index];
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
            texture,
            getX(),
            getY(),
            getOriginX(), //центр,относительно которого будет вращение
            getOriginY(),
            getWidth(),
            getHeight(),
            getScaleX(),
            getScaleY(),
            getRotation()
        );
    }
}
