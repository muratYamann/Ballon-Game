package com.oyun;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by murat on 20.08.2015.
 */
public class InternalReadFileScore {

    private static final String TAG = "InternalReadFile";

    String line = "";
    public ArrayList<String> liste;


    public  void ReadToFile(String fileName,Context context){

        Log.d(TAG, "ReadToFile method");
        liste=new ArrayList<String>();

        try {


            File myFile = new File(context.getFilesDir(), fileName);

            if (myFile.exists()){

                Log.i(TAG, "file exist");

                FileInputStream fIn = new FileInputStream(myFile);

                InputStreamReader inputStreamReader = new InputStreamReader(
                        fIn);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);

                while ((line = bufferedReader.readLine()) != null) {
                    liste.add(line);
                    Log.d(TAG, "Readed Internal Line: " + line);
                    // if (liste.size() > 18) {
                    //    liste.remove(18);
                    //}
                }
                Log.w(TAG,
                        "okuThread; Ten data was read from the file");
                fIn.close();

            }

        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "Dosya yok: " + e.toString());

        } catch (IOException e) {
            Log.e(TAG, "Dosya okunamiyor: " + e.toString());
        }


    }



    }



