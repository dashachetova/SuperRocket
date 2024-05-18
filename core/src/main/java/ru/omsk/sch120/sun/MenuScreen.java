package ru.omsk.sch120.sun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen extends ScreenAdapter {
    private Game game;
    public MenuScreen (Game game){
        this.game=game;
    }

    private Stage stage;
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 1, 0, 0);
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Backgroundactor backgroundActor = new Backgroundactor();
        stage.addActor(backgroundActor);

        Texture upTexture = new Texture("knopka2.png");
        Texture downTexture = new Texture("knopka1.png");

        FileHandle fontFile = Gdx.files.internal("lobster2.fnt");
        BitmapFont bitmapFont = new BitmapFont(fontFile);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(upTexture);
        textButtonStyle.down = new TextureRegionDrawable(downTexture);
        textButtonStyle.font = bitmapFont;
        TextButton playbutton = new TextButton("Play", textButtonStyle);
        playbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new FirstScreen(game));
            }
        });

        TextButton exitbutton = new TextButton("Exit", textButtonStyle);

        exitbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Table table = new Table();
        table.add(playbutton).pad(15);
        table.row();
        table.add(exitbutton);
        table.setFillParent(true);


        stage.addActor(table);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
