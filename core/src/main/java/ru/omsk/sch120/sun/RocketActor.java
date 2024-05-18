package ru.omsk.sch120.sun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Array;

public class RocketActor extends Actor {
    private Touchpad touchpad;

    private Label scores;

    int score=-1;

    private Texture texture;
    private TextureRegion textureRegion;
    public static final int SPEED = 10;
    //контейнер, в котором лежат кадры
    private Animation<TextureRegion> animations; //null

    private boolean isMoving = true;
    private float gameTime = 0;

    private Polygon hitbox;

    public RocketActor(Touchpad touchpad, Label scores) {
        texture = new Texture("rocket.png");
        textureRegion = new TextureRegion(texture);
        setWidth(250);
        setHeight(125);
        this.touchpad = touchpad;
        this.scores = scores;

        setOriginX(getWidth() / 2);
        setOriginY(getHeight() / 2);

        //animations = getAnimations();
        float[] vertices = new float[]{
            0, getHeight() / 2, // на четной позиции X, на нечетной - Y
            getWidth() / 3, getHeight(),
            2 * getWidth() / 3, getHeight(),
            getWidth(), getHeight() / 2,
            2 * getWidth() / 3, 0,
            getHeight() / 3, 0
        };
        hitbox = new Polygon(vertices);
        hitbox.setPosition(getX(), getY());
        hitbox.setOrigin(getWidth() / 2, getHeight() / 2);


    }

    public Polygon getHitbox() {
        return hitbox;
    }


    @Override
    public void act(float delta) { //1/60
        gameTime += delta; //0,013
        super.act(delta);
        float newX = touchpad.getKnobPercentX() * SPEED;

        float newY = touchpad.getKnobPercentY() * SPEED;
        moveBy(newX, newY);

        if (getX() < 0) {
            setX(0);
        }
        if (getY() < 0) {
            setY(0);
        }
        if (getY() > MainGame.WORLD_HEIGHT - getHeight()) {
            setY(MainGame.WORLD_HEIGHT - getHeight());
        }
        if (getX() > MainGame.WORLD_WIDTH - getWidth()) {
            setX(MainGame.WORLD_WIDTH - getWidth());
        }
        if (newX != 0F || newY != 0F) {
            Vector2 vector2 = new Vector2(newX, newY);
            float angle = vector2.angleDeg();
            setRotation(angle);
            isMoving = true;
        } else {
            isMoving = false;
        }

        hitbox.setPosition(getX(), getY());

        hitbox.setRotation(getRotation());
        checkOverlap();
    }

    private void checkOverlap() {
        Stage stage = getStage();
        Array<Actor> actors = stage.getActors();
        for (int i = 0; i < actors.size; i++) {
            Actor actor = actors.get(i);
            if (actor instanceof AstronautActor) {
                AstronautActor atronautActor = (AstronautActor) actor;
                boolean isOverlap = Intersector.overlapConvexPolygons(hitbox, atronautActor.getHitbox());
                if (isOverlap) {
                    atronautActor.reSpawn();
                    score = score+1;
                    scores.setText("Score: " + score);
                }
            }
            if (actor instanceof UfoActor) {
                UfoActor ufoActor = (UfoActor) actor;
                boolean isOverlap = Intersector.overlapConvexPolygons(hitbox, ufoActor.getHitbox());
                if (isOverlap) {
                    ufoActor.reSpawn();
                    score = score-1;
                    scores.setText("Score: " + score);
                }

            }
            if (actor instanceof StoneActor){
                StoneActor stoneActor = (StoneActor) actor; // привели к типу стоунэктор чтобы работать дальше с переменной
                Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector(); //Переменна в которую сохранется минимальный сдвиг дл разделени двух полигонов
                boolean isOverlap = Intersector.overlapConvexPolygons(hitbox, stoneActor.getHitbox(),mtv);
                if (isOverlap){
                    float nextX = mtv.normal.x * mtv.depth
                        ;
                    float nextY = mtv.normal.y * mtv.depth;
                    moveBy(nextX, nextY);
                }
            }
        }
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
