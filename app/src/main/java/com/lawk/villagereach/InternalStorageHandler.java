package com.lawk.villagereach;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;


public class InternalStorageHandler {

    private final String TAG = "Internal Storage Says:";
<<<<<<< HEAD
    //    private String fileString;
=======
//    private String fileString;
>>>>>>> SG_requestTokenAltForAM
    private Context context;
    private static InternalStorageHandler instance;
    private String fileName = "tokenFile.txt";

    public InternalStorageHandler(Context context) {
        this.context = context;
    }

    public static synchronized InternalStorageHandler getInstance(Context context){
<<<<<<< HEAD
        if(instance == null){
            instance = new InternalStorageHandler(context);
        }
        return instance;
=======
      if(instance == null){
          instance = new InternalStorageHandler(context);
      }
      return instance;
>>>>>>> SG_requestTokenAltForAM
    }

    // to do: serialize passed in object to json string

    public String readFile(Context context, String fileName) {
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e("Read File", "File Not Found" + e.toString());
        } catch (IOException e) {
            Log.e("Read File", "File Read Failed" + e.toString());
        }
        return "File Not Read";
    }

    public void writeToFile(String dataToBeStored, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(this.fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(dataToBeStored);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



    public JSONObject fileStringToJSONObject(String fileString) throws JSONException {
        return new JSONObject(fileString);
    }

//    public Context getContext() {
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }



}
