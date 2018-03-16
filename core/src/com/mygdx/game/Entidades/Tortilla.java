package com.mygdx.game.Entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.mygdx.game.Tools.Constantes.ALTOPATATA;
import static com.mygdx.game.Tools.Constantes.ANCHURAPATATA;


/**
 * Createdd by admincenec on 31/01/2018.
 */

public class Tortilla {

    private Texture texture;
    float x, y;
    public boolean activa = false;


    //Creamos la tortilla en la poscion de la patata
    public Tortilla(Patata c) {
        this.y = c.getY();
        this.x = c.getX();


        if (texture == null)
            texture = new Texture("tortilla.jpg");
    }

    public void update(float deltaTime) {


    }

    public void dispose() {

        texture.dispose();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, ANCHURAPATATA, ALTOPATATA);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
