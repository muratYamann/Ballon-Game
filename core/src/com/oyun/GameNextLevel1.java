package com.oyun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by murat on 16.05.2016.
 */


public class GameNextLevel1 implements Screen {

    public GameNextLevel1(){
    }

    private   int seconds ;
    float totalTime = 1 * 5;

    private BitmapFont font;
    private Sound bitisSesi;

    SpriteBatch batch;
    Texture img;


    @Override
    public void show() {
        font =new BitmapFont(Gdx.files.internal("font.fnt"));
        batch = new SpriteBatch();
        img = new Texture("next_page.png");
        bitisSesi=Gdx.audio.newSound(Gdx.files.internal("balloonpop.mp3"));


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        font.setColor(1,1, 1, 1);
        font.getData().setScale(1f);
        batch.begin();


        Preferences preferences =Gdx.app.getPreferences("prefsltwo");
        int scoree =preferences.getInteger("score2",100);

        int kirmiziSyc =preferences.getInteger("red2",1);
        int yesilSyc   =preferences.getInteger("green2",1);
        int sariSyc    =preferences.getInteger("yellow2",1);
        int siyahSyc   =preferences.getInteger("black2",1);



        float deltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()
        totalTime -= deltaTime; //if counting down
        seconds = ((int) totalTime) % 60;
        String time = String.valueOf(seconds);


        if (seconds==0){

            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameStart2());
        }
        batch.draw(img, 50, 100);


        font.draw(batch, "Red    :" + kirmiziSyc, Gdx.graphics.getWidth()/2-120, Gdx.graphics.getHeight()/2+270);
        font.draw(batch, "Green  :" + yesilSyc,   Gdx.graphics.getWidth()/2-120, Gdx.graphics.getHeight()/2+230);
        font.draw(batch, "Yellow :" + sariSyc,    Gdx.graphics.getWidth()/2-120, Gdx.graphics.getHeight()/2+190);
        font.draw(batch, "Black  :" + siyahSyc,   Gdx.graphics.getWidth()/2-120, Gdx.graphics.getHeight()/2+150);


        font.draw(batch, "Your Level2 Score :"+String.valueOf(scoree), Gdx.graphics.getWidth()/2-220, Gdx.graphics.getHeight()/2+40);

        font.draw(batch,"For Next Level Wait  "+ time+ "  Please ", Gdx.graphics.getWidth()/2-300, Gdx.graphics.getHeight()/2-120);
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

    }
}
