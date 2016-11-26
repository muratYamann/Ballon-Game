package com.oyun;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by murat yaman on 10.05.2016.
 * muratymn.72@gmail.com
 */
public class Score extends Activity {


    WriteInternalAppendScore wr;
    InternalReadFileScore rd;
    Context mContext ;
    String fieName="score.csv";

    TextView t1,t2,t3;
    ArrayList<String>liste;

    int skorlar[];

    SharedPreferences sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);


        wr =new WriteInternalAppendScore();
        rd =new InternalReadFileScore();
        mContext =getApplicationContext();

        t1= (TextView)findViewById(R.id.tv1Sonuc);
        t2= (TextView)findViewById(R.id.tv2Sonuc);
        t3= (TextView)findViewById(R.id.tv3Sonuc);

        sp =getSharedPreferences("prefs", Context.MODE_PRIVATE);
        int score = sp.getInt("score", 10);

        wr.WriteFile(fieName,String.valueOf(score),mContext);

        rd.ReadToFile(fieName, mContext);
        liste=rd.liste;

         skorlar  =new int[liste.size()];

        for (int i=0;i<liste.size();i++){
            skorlar[i]= Integer.parseInt(liste.get(i));
        }


        int takas=0;
        for(int i=0; i<skorlar.length;i++)
        {
            for(int k=0; k<skorlar.length;k++)
            {
                if(skorlar[k]<skorlar[i])
                {
                    takas  = skorlar[i];
                    skorlar[i]= skorlar[k];
                    skorlar[k]=takas;
                }
            }
        }

        if (liste.size()>=3){
            t1.setText(String.valueOf(skorlar[0]));
            t2.setText(String.valueOf(skorlar[1]));
            t3.setText(String.valueOf(skorlar[2]));
        }else if (liste.size()==2){
            t1.setText(String.valueOf(skorlar[0]));
            t2.setText(String.valueOf(skorlar[1]));
        }else if (liste.size()==1){
            t1.setText(String.valueOf(skorlar[0]));
        }else {
            t1.setText("for Score please play Game");
        }



    }



}
