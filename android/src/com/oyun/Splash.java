package com.oyun;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by murat yaman on 10.05.2016.
 * muratymn.72@gmail.com
 */
public class Splash extends Activity  {

   ImageView img;
    //Animation animFadein;
    private static final String TAG = "_Splash";
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        MediaPlayer mp ;

        //animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        // set animation listener
       // animFadein.setAnimationListener(this);


       // img.setVisibility(View.VISIBLE);
        // start the animation
        //img.startAnimation(animFadein);


        Uri path= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.intro);
        mp =MediaPlayer.create(Splash.this,path);
        mp.start();

        startHandler();



    }
    public void startHandler(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // img.startAnimation(animFadein);

                Intent intentMain = new Intent(Splash.this, MainMenu.class);
                startActivity(intentMain);


                Log.i(TAG, "Finish Splash Activity");
                finish();
            }
        }, SPLASH_TIME_OUT);

    }//handler end


/*
    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation
        // check for fade in animation
        if (animation == animFadein) {
            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }
*/


}
