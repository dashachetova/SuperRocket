package ru.omsk.sch120.sun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class UfoActor extends Actor {

    private Polygon hitbox;
    private TextureRegion textureRegion;
    private RocketActor turtle;

    public UfoActor(int x, int y, RocketActor turtle){
        textureRegion = new TextureRegion(new Texture("ufo.png"));
        setPosition(x,y);
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());
        setOrigin(getWidth()/2, getHeight()/2);
        this.turtle = turtle;

        float[] vertices = new float[]{
            0,getHeight()/2, // на четной позиции X, на нечетной - Y
            getWidth()/3, getHeight(),
            2 * getWidth() / 3, getHeight(),
            getWidth(),getHeight()/2,
            2 * getWidth() / 3, 0,
            getHeight() / 3, 0

        };
        hitbox = new Polygon(vertices);
        hitbox.setPosition(getX(), getY());
        hitbox.setOrigin(getWidth()/2, getHeight()/2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Vector2 directionVector = new Vector2(turtle.getX() - getX(), turtle.getY() - getY());
        float angle = directionVector.angleDeg();
        setRotation(angle);

        Vector2 velocityVector = new Vector2(3,0);
        velocityVector.setAngleDeg(angle);
        moveBy(velocityVector.x, velocityVector.y);


        hitbox.setPosition(getX(), getY());

        hitbox.setRotation(getRotation());
    }

    public Polygon getHitbox() {
        return hitbox;
    }


    public void reSpawn(){
        float nextX = MathUtils.random(0, MainGame.WORLD_WIDTH - getWidth());
        float nextY = MathUtils.random(0, MainGame.WORLD_HEIGHT - getHeight());
        setPosition(nextX, nextY);
        hitbox.setPosition(nextX,nextY);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(
                textureRegion,
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
