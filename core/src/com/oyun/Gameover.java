package com.oyun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Gameover implements Screen {
    public Gameover(){
    }
    private BitmapFont font;
    private Sound bitisSesi;

    SpriteBatch batch;
    Texture img;


    @Override
    public void show() {
        font =new BitmapFont(Gdx.files.internal("font.fnt"));
        batch = new SpriteBatch();
        img = new Texture("game_over_osmn.png");
        bitisSesi=Gdx.audio.newSound(Gdx.files.internal("balloonpop.mp3"));


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        font.setColor(1,1, 1, 1);
        font.getData().setScale(1f);
        batch.begin();


         Preferences preferences =Gdx.app.getPreferences("prefs");
         int scoree =preferences.getInteger("score",100);

        if(Gdx.input.isTouched()){
            Gdx.app.exit();
        }

        batch.draw(img, 50, 100);
        font.draw(batch,"Cikmak icin Ekrana Dokunun ",20,50);
        font.draw(batch, "Skorunuz :"+String.valueOf(scoree), Gdx.graphics.getWidth()/2-190, Gdx.graphics.getHeight()/2-130);
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
