package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Tools.Colision;

import static com.mygdx.game.Tools.Constantes.ALTO_DISPARO;
import static com.mygdx.game.Tools.Constantes.ANCHO_DISPARO;
import static com.mygdx.game.Tools.Constantes.ANCHO_PANTALLA;
import static com.mygdx.game.Tools.Constantes.VELOCIDAD_DISPARO;

/**
 * Created by CristinaVilas on 29/01/2018.
 */

public class Huevo {


    private static Texture texture;

    float x, y;
    Colision colision;
    public boolean remove = false;

    public Huevo(float y, float x) {
        this.y = y;
        this.x = x;
        this.colision = new Colision(x, y, ANCHO_DISPARO, ALTO_DISPARO);
        texture = new Texture("egg copy3.png");
    }

    public void update(float deltaTime) {
        x += VELOCIDAD_DISPARO * deltaTime;
        if (x > ANCHO_PANTALLA) {
            remove = true;
        }
        colision.mover(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);

    }

    public Colision getColision() {
        return colision;
    }

}