package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.game.Constantes;
import com.mygdx.game.Database.JuegoDataBase;
import com.mygdx.game.Database.PuntuacionJuego;
import com.mygdx.game.Entidades.Huevo;
import com.mygdx.game.Entidades.Patata;
import com.mygdx.game.Entidades.Tortilla;
import com.mygdx.game.MainGame;
import com.mygdx.game.Screens.GameOverScreen;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.Input.Keys;
import static com.mygdx.game.Constantes.ALTOPATATA;
import static com.mygdx.game.Constantes.ALTO_PANTALLA;
import static com.mygdx.game.Constantes.NUM_RECORDS;
import static com.mygdx.game.Constantes.PATATASMAXIMAS;
import static com.mygdx.game.Constantes.PATATASMINIMAS;
import static com.mygdx.game.Constantes.PUNTUACION_JUEGO;
import static com.mygdx.game.Constantes.ROBOTALTO;
import static com.mygdx.game.Constantes.ROBOTANCHO;
import static com.mygdx.game.Constantes.ROBOTVELOCIDAD;
import static com.mygdx.game.Constantes.TIEMPO_DISPARO;
import static com.mygdx.game.Constantes.VELOCIDADPATADA;
import static com.mygdx.game.MainGame.cam;


/**
 * Created by CristinaViilas on 29/01/2018.
 */

public class GameScreen implements Screen {

    //CONSTANTES


   public static int  contadorPatatas;
     int contador;
    public Game game;
    protected float robotX, robotY;
    protected float disparoTime;
    protected float statetime;
    float patatatimer;
    ArrayList<Huevo> huevos;
    ArrayList<Patata> patatas;
    protected Tortilla tortilla;
    protected Texture ROBOT;
    private SpriteBatch spritebatch;
   static protected ArrayList<Tortilla>tortillas;
    Batch batch;
    protected Random random;
    public Texture background;
    public Music cocinerococinero;
    protected BitmapFont scoreFont;
    protected BitmapFont patatasFont;



    public GameScreen(Game game) {

        contador=0;


        contadorPatatas=3;
        Gdx.app.log("INICIALIZANDO","MAIN SCREEN");
        spritebatch = new SpriteBatch();
        com.mygdx.game.Tools.Constantes.PATATAS_OUT = 0;
        this.game = game;
        robotY = 0;
        robotX = 0;
        huevos = new ArrayList<Huevo>();
        patatas = new ArrayList<Patata>();
        tortillas=new ArrayList<Tortilla>();
        random = new Random();
        patatatimer = random.nextFloat() * (PATATASMAXIMAS - PATATASMINIMAS) + PATATASMINIMAS;
        disparoTime = 0;
        patatasFont= new BitmapFont(Gdx.files.internal("score.fnt"));
        scoreFont = new BitmapFont(Gdx.files.internal("score.fnt"));
        ROBOT = new Texture("disparo1.png");
        background = new Texture("kitchen.png");
        Gdx.app.log("musica activada?", MainGame.musica.toString());

        if(MainGame.musica){
            cocinerococinero = Gdx.audio.newMusic(Gdx.files.internal("cocinero.ogg"));
            cocinerococinero.play();
            cocinerococinero.setLooping(true);
        }
    }


    @Override
    public void show() {






    }

    @Override
    public void render(float delta) {


        spritebatch.setProjectionMatrix(cam.combined());

        // codigo disparo
        disparoTime += delta;
        if ((arriba() || abajo()) && disparoTime >= TIEMPO_DISPARO) {
            disparoTime = 0;
            huevos.add(new Huevo(robotY+90f,robotX + 220f));

        }


        // respawn patatas
        patatatimer -= delta;
        if (patatatimer <= 0) {
            patatatimer = random.nextFloat() * (PATATASMAXIMAS - PATATASMINIMAS) + PATATASMINIMAS;
            patatas.add(new Patata(random.nextInt(ALTO_PANTALLA - ALTOPATATA)));
        }
        //Update patatas
        ArrayList<Patata> patatasfuera = new ArrayList<Patata>();
        for (Patata patata : patatas) {
            patata.update(delta);
            if (patata.eliminar){
                patatasfuera.add(patata);

            }
            if(patata.getX()<robotX)
            {
                System.out.print("PATATA MENOS");
            }



        }
        //Update Tortilla

        ArrayList<Tortilla> eliminarTortilla = new ArrayList<Tortilla>();





        //Update huevos
        ArrayList<Huevo> disparosEliminar = new ArrayList<Huevo>();
        for (Huevo huevo : huevos) {
            huevo.update(delta);
            if (huevo.remove)
                disparosEliminar.add(huevo);

        }

        //  movimiento del robot
        if (arriba()) {// ARRIBA
            robotY += ROBOTVELOCIDAD * Gdx.graphics.getDeltaTime();

            if (robotY > Constantes.ALTO_PANTALLA)
                robotY = Constantes.ALTO_PANTALLA - ROBOT.getHeight();
        }
        if (abajo()) {//ABAJO
            robotY -= ROBOTVELOCIDAD * Gdx.graphics.getDeltaTime();

            if (robotY < -400)
                robotY = 0;
        }
        //COLISIONES
        for (Huevo huevo : huevos) {
            for (Patata patata : patatas) {
                if (huevo.getColision().chocadoCon(patata.getColision())){


                    disparosEliminar.add(huevo);
                    patatasfuera.add(patata);
                    tortilla= new Tortilla(patata);
                    tortilla.activa=true;


                    tortillas.add(tortilla);
                    contador+=1;
                    PUNTUACION_JUEGO+=1;
                    MainGame.db.saveCurrentGame(PUNTUACION_JUEGO);

                    //Me borra las tortillas cuando son dos dejando la tercera recien creada

                    if(contador==2) {
                        for (int i=0;i<tortillas.size();i++) {

                            tortillas.get(i).update(delta);
                            tortillas.remove(tortillas.get(i));

                        }
                        contador=0;

                    }







                }
            }
        }

        huevos.removeAll(disparosEliminar);
        patatas.removeAll(patatasfuera);
        tortillas.removeAll(eliminarTortilla);


        statetime += delta;
        // aumentamos dificultad segun pase el tiempo
        dificultad();

        Gdx.gl.glClearColor(0.1f, 0.4f , 0.6f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MainGame.batch.begin();
        MainGame.batch.draw(background, 0, 0);



        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Score:" +PUNTUACION_JUEGO);
        GlyphLayout patataLayout =new GlyphLayout(patatasFont,"Vidas"+ contadorPatatas);
        scoreFont.draw(MainGame.batch, scoreLayout, 900, 740);
        patatasFont.draw(MainGame.batch,patataLayout,80,740);


            for (Huevo huevo : huevos) {
                huevo.render(MainGame.batch);
            }
        for(Tortilla tortilla:tortillas)
        {
            tortilla.render(MainGame.batch);
        }

        for (Patata patata : patatas) {
            patata.render(MainGame.batch);
        }


        if (com.mygdx.game.Tools.Constantes.PATATAS_OUT >= 3) {
            this.dispose();
            MainGame.db.endCurrentGame(PUNTUACION_JUEGO);
            PUNTUACION_JUEGO=0;
            NUM_RECORDS+=1;
            this.game.setScreen(new GameOverScreen(this.game));
        }
        MainGame.batch.draw(ROBOT, robotX, robotY, ROBOTANCHO, ROBOTALTO);
        MainGame.batch.end();


    }



    public void dificultad() {
        PATATASMINIMAS -= 0.000001f;
        VELOCIDADPATADA += 0.5f;
        TIEMPO_DISPARO -= 0.000001f;
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

        ROBOT.dispose();


    }

    private boolean arriba() {
        return Gdx.input.isKeyPressed(Keys.UP) || (Gdx.input.isTouched() && MainGame.cam.getInputInGameWorld().y < ALTO_PANTALLA / 2);
    }

    private boolean abajo() {
        return Gdx.input.isKeyPressed(Keys.DOWN) || (Gdx.input.isTouched() && MainGame.cam.getInputInGameWorld().y >= ALTO_PANTALLA / 2);
    }


}
