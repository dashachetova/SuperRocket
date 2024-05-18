package ru.omsk.sch120.sun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    private Game game;
    private Stage stage;
    private Viewport viewport;

    private int score=0;

    //для отладки хитбокса
    private ShapeRenderer shapeRenderer; //null
    //временно
    private RocketActor turtleActor;
    private AstronautActor patrik;
    private UfoActor ufoActor;

    public FirstScreen (Game game){
        this.game=game;
    }

    @Override
    public void show() {
        viewport = new FitViewport(MainGame.WORLD_WIDTH, MainGame.WORLD_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor((stage));


        Backgroundactor backgroundActor = new Backgroundactor();
        stage.addActor(backgroundActor);


        TextureRegion [] astronautsArray = {
            new TextureRegion(new Texture("astronaut.png")),
            new TextureRegion(new Texture("astronaut2.png")),
            new TextureRegion(new Texture("astronaut3.png")),
            new TextureRegion(new Texture("astronaut4.png"))
        };

        patrik = new AstronautActor(astronautsArray,500, 500);
        stage.addActor(patrik);


        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = new TextureRegionDrawable(new Texture("joystick-background.png"));
        touchpadStyle.knob = new TextureRegionDrawable(new Texture("joystick-knob.png"));
        Touchpad touchpad = new Touchpad(0, touchpadStyle);
        touchpad.setPosition(MainGame.WORLD_WIDTH - touchpad.getWidth(), 0);




        int nextX = MathUtils.random(250, 325);
        int nextY = MathUtils.random(125, 620);

        Texture stoneTexture = new Texture("rock.png");
        StoneActor stone1Actor = new StoneActor(stoneTexture, nextX, nextY);
        stage.addActor(stone1Actor);

        int nextX2 = MathUtils.random(425, 853);
        int nextY2 = MathUtils.random(0, 620);
        StoneActor stone2Actor = new StoneActor(stoneTexture, nextX2, nextY2);
        stage.addActor(stone2Actor);

        int nextX3 = MathUtils.random(853, 1180);
        int nextY3 = MathUtils.random(0, 620);
        StoneActor stone3Actor = new StoneActor(stoneTexture, nextX3, nextY3);
        stage.addActor(stone3Actor);




        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("lobster2.fnt"));
        Label scores = new Label ("Score: ggh" + score, labelStyle);
        scores.setPosition(10, 680);
        stage.addActor(scores);

        turtleActor = new RocketActor(touchpad, scores);
        stage.addActor(turtleActor);


        ufoActor = new UfoActor(1000,360, turtleActor);
        stage.addActor(ufoActor);

        shapeRenderer = new ShapeRenderer();


        stage.addActor(touchpad);





        Texture upTexture = new Texture("knopka3.png");
        Texture downTexture = new Texture("knopka4.png");

        FileHandle fontFile = Gdx.files.internal("lobster2.fnt");
        BitmapFont bitmapFont = new BitmapFont(fontFile);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(upTexture);
        textButtonStyle.down = new TextureRegionDrawable(downTexture);
        textButtonStyle.font = bitmapFont;
        TextButton exitbutton = new TextButton("Menu", textButtonStyle);
        exitbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
        exitbutton.setPosition(1150, 670);
        stage.addActor(exitbutton);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act();
        stage.draw();

        //shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //shapeRenderer.setColor(0, 1, 0, 1);
        //shapeRenderer.polygon(turtleActor.getHitbox().getTransformedVertices());
        //shapeRenderer.polygon(patrik.getHitbox().getTransformedVertices());
        //shapeRenderer.polygon(stoneActor.getHitbox().getTransformedVertices());
        //shapeRenderer.polygon(ufoActor.getHitbox().getTransformedVertices());
        //shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
