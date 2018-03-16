package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Database.JuegoDataBase;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Tools.GameCamera;

import static com.mygdx.game.Tools.Constantes.ALTO_PANTALLA;
import static com.mygdx.game.Tools.Constantes.ANCHO_PANTALLA;

public class MainGame extends Game {



    public static boolean IS_MOBILE = false;
    public static SpriteBatch batch;
    public static GameCamera cam;
    public static Boolean musica;
    public static JuegoDataBase db;

    public MainGame(Boolean musica, JuegoDataBase juegoDataBaseAndroid) {

        db=juegoDataBaseAndroid;
        this.musica=musica;
    }




    @Override
    public void create() {
        batch = new SpriteBatch();
        cam = new GameCamera(ANCHO_PANTALLA, ALTO_PANTALLA);

        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS)
            IS_MOBILE = true;
        IS_MOBILE = true;



        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render() {

        batch.setProjectionMatrix(cam.combined());
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        cam.update(width, height);
        super.resize(width, height);
    }

}