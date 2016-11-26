package com.oyun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by murat yaman on 10.05.2016.
 * muratymn.72@gmail.com
 */
public class MainMenu  extends Activity{



    ImageButton yeniOyun,hakkimizda,cikis,highScore;
    SharedPreferences   sp;
    SharedPreferences.Editor editor;

    CheckBox checkBox;
    String sesAc;
    String sesKapa;

    MediaPlayer mpButon ;
    MediaPlayer mpMusic ;
    Boolean ses=false ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        setupVariable();


        sp = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = sp.edit();


        editor.putBoolean("ses", true);
        editor.commit();


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setButtonDrawable(R.drawable.speaker);
                    ses = true;
                    editor.putBoolean("ses", true);
                } else {
                    checkBox.setButtonDrawable(R.drawable.ses_n);
                    ses = false;
                    editor.putBoolean("ses", false);
                }
                editor.commit();

            }
        });

        if (ses == true) {
            mpMusic.start();
            mpMusic.setLooping(true);
        }
        onClick();


}

    public  void  setupVariable(){



        yeniOyun =(ImageButton)findViewById(R.id.yeni_oyun);
        hakkimizda=(ImageButton)findViewById(R.id.hakkimizda);
        cikis=(ImageButton)findViewById(R.id.cikis);
        highScore =(ImageButton)findViewById(R.id.scoree);
        checkBox =(CheckBox)findViewById(R.id.checkBox);


        Uri path2= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.balon_patlat);
        mpButon =MediaPlayer.create(MainMenu.this, path2);

        Uri path= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.music);
        mpMusic =MediaPlayer.create(MainMenu.this, path);

    }

    public  void onClick(){



        yeniOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainMenu.this,AndroidLauncher.class);
                startActivity(i);
                if (ses ==true){
                    mpButon.start();
                }

            }
        });


        hakkimizda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (ses ==true){
                    mpButon.start();
                }
             Intent i =new Intent(getApplicationContext(),Hakkimizda.class);
                startActivity(i);

            }
        });

        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ses ==true){
                    mpButon.start();
                }
                Intent i = new Intent(getApplicationContext(), Score.class);
                startActivity(i);
            }
        });


        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ses ==true){
                    mpButon.start();
                }
                finish();
            }
        });



    }








    /**iconlar için class*/
    public class ArrayAdapterWithIcon extends ArrayAdapter<String> {

        private List<Integer> images;

        public ArrayAdapterWithIcon(Context context, List<String> items, List<Integer> images) {
            super(context, android.R.layout.select_dialog_item, items);
            this.images = images;
        }

        public ArrayAdapterWithIcon(Context context, String[] items, Integer[] images) {
            super(context, android.R.layout.select_dialog_item, items);
            this.images = Arrays.asList(images);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setCompoundDrawablesWithIntrinsicBounds(images.get(position), 0, 0, 0);
            textView.setCompoundDrawablePadding(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getContext().getResources().getDisplayMetrics()));
            return view;
        }

    }






}
