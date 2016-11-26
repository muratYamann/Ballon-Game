package com.oyun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;


public class GameStart implements Screen {

    public GameStart(){
    }
    Gameover gameover;


    private SpriteBatch batch;
    private Texture background1;
    private Texture background2;
    private Texture background3;


    private Preferences prefs2;
    private Preferences prefScore;
    public int highscore;


    private String score ;
    private String kirmiziSyc;
    private String sariSyc ;
    private String yesilSyc ;
    private String siyahSyc ;

    Game game;

    private BitmapFont font;
    private  int level=0;

    private SpriteBatch sayfa;
    private OrthographicCamera kamera;
    private Texture kovaTexture;
    private Rectangle recKova;
    private Sound damlamaSesi;

    private   int seconds ;
    float atotalTime = 1 * 10;
    float totalTime = 1 * 30;

    private Texture damlaTexture1;
    private Texture kirmiziBalon1;
    private Texture yesilBalon1;
    private Texture sariBalon1;

    private Texture damlaTexture2;
    private Texture kirmiziBalon2;
    private Texture yesilBalon2;
    private Texture sariBalon2;

    private Music music;
    private ArrayList<Rectangle> kirmiziBalonlar;
    private ArrayList<Rectangle> yesilBalonlar;
    private ArrayList<Rectangle> sariBalonlar;
    private ArrayList<Rectangle> damlalar;

    private long sonDamlama;
    private long kirmiziBalonSonDamlama;


    private int siyahSayac =0;
    private int kirmiziSayac =0;
    private int sariSayac =0;
    private int yesilSayac=0;

    @Override
    public void show() {
        gameover = new Gameover();
        batch = new SpriteBatch();
        background1 = new Texture("bg_osmn.png");
        background2 = new Texture("s.jpg");
        background3 = new Texture("bg_osmn_gece.jpg");

        kamera = new OrthographicCamera();
        kamera.setToOrtho(false,800,480);
        sayfa = new SpriteBatch();


        kirmiziBalon2 =new Texture("red_y.png");
        damlaTexture2 = new Texture("black_y.png");
        yesilBalon2=new Texture("gren_y.png");
        sariBalon2 =new Texture("yellow_y.png");


        kirmiziBalon1 =new Texture("red_yk.png");
        damlaTexture1 = new Texture("black_yk.png");
        yesilBalon1=new Texture("green_yk.png");
        sariBalon1 =new Texture("yellow_yk.png");

        kovaTexture = new Texture("box_iyea.png");


        damlamaSesi=Gdx.audio.newSound(Gdx.files.internal("balloonpop.mp3"));


        prefs2 =Gdx.app.getPreferences("prefslone");
        prefScore=Gdx.app.getPreferences("prefs");


    //    int gelenPuan = prefs2.getInteger("score");
    //    level+=gelenPuan;


        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        if(prefs2.getBoolean("ses",true)){
            music.play();
        }

        //yagmurSesi.play();

        kirmiziBalonlar=new ArrayList<Rectangle> ();
        yesilBalonlar=new ArrayList<Rectangle> ();
        sariBalonlar=new ArrayList<Rectangle>();
        damlalar = new ArrayList<Rectangle>();

        font =new BitmapFont(Gdx.files.internal("font.fnt"));

        damlaUret();
        kirmiziBalonUren();
        yesilBalonUret();
        sariBalonUret();

        recKova = new Rectangle();
        recKova.width = 64;
        recKova.height = 64;
        recKova.setX(Gdx.graphics.getWidth());
        recKova.setY(20);
    }


    @Override
    public void render(float delta) {


            Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            if (TimeUtils.nanoTime() - sonDamlama > 1000000000) {

                damlaUret();
                kirmiziBalonUren();
                yesilBalonUret();
                sariBalonUret();
            }



            Iterator<Rectangle> iter = damlalar.iterator();
            while (iter.hasNext()) {
                Rectangle damla = iter.next();
                damla.y += 100 * Gdx.graphics.getDeltaTime();
                if (damla.y +64>550)
                    iter.remove();
                if (damla.overlaps(recKova)) {
                    if(prefs2.getBoolean("ses",true)){
                        damlamaSesi.play();
                    }
                    iter.remove();
                    level -= 10;
                    siyahSayac++;
                }
            }

        int sayac =0;
        Iterator<Rectangle> iter_kirmizi = kirmiziBalonlar.iterator();
        while (iter_kirmizi.hasNext()) {
            Rectangle K_balon = iter_kirmizi.next();
            //K_balon.y += 200 * Gdx.graphics.getDeltaTime();
            K_balon.y +=   200 * Gdx.graphics.getDeltaTime();
            K_balon.x += rastGeleHareket(sayac);
            sayac++;
          //  K_balon.x +=  MathUtils.random(-300, 300) * Gdx.graphics.getDeltaTime();
            if (K_balon.y +64>550)
                iter_kirmizi.remove();
            if (K_balon.overlaps(recKova)) {
                if(prefs2.getBoolean("ses",true)){
                    damlamaSesi.play();
                }    iter_kirmizi.remove();
                level += 10;
                kirmiziSayac++;
            }
        }



        Iterator<Rectangle> iter_yesil = yesilBalonlar.iterator();
            while (iter_yesil.hasNext()) {
                Rectangle Y_balon = iter_yesil.next();
                Y_balon.y += 200 * Gdx.graphics.getDeltaTime();

                if (Y_balon.y +64>550)
                    iter_yesil.remove();

                if (Y_balon.overlaps(recKova)) {
                    if(prefs2.getBoolean("ses",true)){
                        damlamaSesi.play();
                    }
                    iter_yesil.remove();
                    level += 5;
                    yesilSayac++;
                }
            }

            Iterator<Rectangle> iter_sari = sariBalonlar.iterator();
            while (iter_sari.hasNext()) {
                Rectangle S_balon = iter_sari.next();
                S_balon.y += 200 * Gdx.graphics.getDeltaTime();

                if (S_balon.y +64>550)
                    iter_sari.remove();

                if (S_balon.overlaps(recKova)) {
                    if(prefs2.getBoolean("ses",true)){
                        damlamaSesi.play();
                    }
                    iter_sari.remove();
                    level += 20;
                    sariSayac++;
                }
            }


            //dokunduguun yeri algılama
            if (Gdx.input.isTouched()) {
                Vector3 dokunmaPozisyonu = new Vector3();
                dokunmaPozisyonu.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                kamera.unproject(dokunmaPozisyonu);
                recKova.x = dokunmaPozisyonu.x - 64 / 2;
                recKova.y = dokunmaPozisyonu.y - 64 / 2;
            }


            Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            kamera.update();
            sayfa.setProjectionMatrix(kamera.combined);

            score = String.valueOf(level);
            kirmiziSyc = String.valueOf(kirmiziSayac);
            sariSyc = String.valueOf(sariSayac);
            yesilSyc = String.valueOf(yesilSayac);
            siyahSyc = String.valueOf(siyahSayac);

            float deltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()
            totalTime -= deltaTime; //if counting down
             seconds = ((int) totalTime) % 60;
            String time = String.valueOf(seconds);

            /*for score*/
            prefScore.putInteger("score", level);
            prefScore.flush();


            prefs2.putInteger("score1", level);
            prefs2.putInteger("red1",kirmiziSayac);
            prefs2.putInteger("green1",yesilSayac);
            prefs2.putInteger("yellow1",sariSayac);
            prefs2.putInteger("black1",siyahSayac);

            prefs2.flush();



        if (time.equals("0")){

            music.stop();
            ((Game)Gdx.app.getApplicationListener()).setScreen(new Gameover());
        }

        if (level>95){
            music.stop();
            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameNextLevel());
        }


        sayfa.begin();

            sayfa.draw(background1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            font.draw(sayfa, "SCORE:" + score, 10, 450);
          //   font.draw(sayfa, "(+10)Red :" + kirmiziSyc, 0, 420);
          //  font.draw(sayfa, "(+5)Green :" + yesilSyc, 0, 390);
          //  font.draw(sayfa, "(+20)Yellow:" + sariSyc, 0, 360);
          //   font.draw(sayfa, "(+10)Black  :" + siyahSyc, 0, 330);
            font.draw(sayfa, "time :" + time, Gdx.graphics.getWidth()/2+40, 450);


            sayfa.draw(kovaTexture, recKova.x, recKova.y, recKova.width, recKova.height);

            for (Rectangle damla : damlalar)
                sayfa.draw(damlaTexture1, damla.x, damla.y);

            for (Rectangle K_balon : kirmiziBalonlar)
                sayfa.draw(kirmiziBalon1, K_balon.x, K_balon.y);


            for (Rectangle Y_balon : yesilBalonlar)
                sayfa.draw(yesilBalon1, Y_balon.x, Y_balon.y);

            for (Rectangle S_balon : sariBalonlar)
                sayfa.draw(sariBalon1, S_balon.x, S_balon.y);


            sayfa.end();
        }




    ///genel
    @Override
    public void dispose() {
        background1.dispose();
        batch.dispose();
        sayfa.dispose();
        kovaTexture.dispose();
        damlaTexture2.dispose();
        kirmiziBalon2.dispose();
        sariBalon2.dispose();
        yesilBalon2.dispose();

    }
    private void damlaUret() {
        Rectangle damla = new Rectangle();
        damla.x = MathUtils.random(0, 800 - 64);
        damla.y = -200;
        damla.width = 64;
        damla.height = 64;
        damlalar.add(damla);
        sonDamlama = TimeUtils.nanoTime();
    }



    private void kirmiziBalonUren () {
        Rectangle K_balon = new Rectangle();
        K_balon.x = MathUtils.random(0, 800 - 64);
        K_balon.y = -200;
        K_balon.width = 64;
        K_balon.height = 64;
        kirmiziBalonlar.add(K_balon);
        kirmiziBalonSonDamlama = TimeUtils.nanoTime();
    }

    private void yesilBalonUret () {
        Rectangle Y_balon = new Rectangle();
        Y_balon.x = MathUtils.random(0, 800 - 64);
        Y_balon.y = -200;
        Y_balon.width = 64;
        Y_balon.height = 64;
        yesilBalonlar.add(Y_balon);
        sonDamlama = TimeUtils.nanoTime();
    }

    private void sariBalonUret () {
        Rectangle S_balon = new Rectangle();
        S_balon.x = MathUtils.random(0, 800 - 64);
        S_balon.y = MathUtils.random(0,300);
        S_balon.width = 64;
        S_balon.height = 64;
        sariBalonlar.add(S_balon);
        sonDamlama = TimeUtils.nanoTime();
    }

    private int rastGeleHareket(int a ){
        float b;

        if (a%2==0) {
            b = MathUtils.random(-200,0) * Gdx.graphics.getDeltaTime();
        }else {
            b= MathUtils.random(0,400)* Gdx.graphics.getDeltaTime();
        }
        return (int)b;
    }


   @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {

    }
  @Override
    public void resize(int width, int height) { }


}
