package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Tools.Colision;
import com.mygdx.game.Tools.Constantes;

import static com.mygdx.game.Tools.Constantes.ALTOPATATA;
import static com.mygdx.game.Tools.Constantes.ANCHO_PANTALLA;
import static com.mygdx.game.Tools.Constantes.ANCHURAPATATA;
import static com.mygdx.game.Tools.Constantes.VELOCIDADPATADA;


/**
 * Created by CristinaVilas on 29/01/2018.
 */

//p

public class Patata {


    private static Texture texture;

    float x, y;
    Colision colision;
    public boolean eliminar = false;

    public Patata(float y) {
        this.y = y;
        this.x = ANCHO_PANTALLA;
        this.colision = new Colision(y, x, ALTOPATATA, ANCHURAPATATA);
         texture = new Texture("potato.png");
    }

    public void update(float deltaTime) {
        x -= VELOCIDADPATADA * deltaTime;
        if (x < -ANCHURAPATATA) {
            Constantes.PATATAS_OUT++;
            eliminar = true;
            GameScreen.contadorPatatas--;

        }
        colision.mover(x, y);

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHURAPATATA, ALTOPATATA);

    }

    public Colision getColision() {
        return colision;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}