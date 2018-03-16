package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame;

import static com.mygdx.game.MainGame.cam;

/**
 * Created by CristinaVilas on 06/03/2018.
 */

public class GameOverScreen implements Screen {

    private Texture fondo;
    private SpriteBatch batch;
    private Game game;

    public GameOverScreen(Game game) {
        this.game = game;
        fondo = new Texture("gameover.png");
        batch = new SpriteBatch();


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(cam.combined());

        Gdx.gl.glClearColor(0.1f, 1f, 0.0f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(fondo, 400, 170);
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        fondo.dispose();
        batch.dispose();


    }
}
