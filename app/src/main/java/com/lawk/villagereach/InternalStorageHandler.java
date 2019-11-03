package com.lawk.villagereach;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.lang.reflect.Type;
import java.util.HashMap;


public class InternalStorageHandler {

    private final String TAG = "Internal Storage Says:";
    //    private String fileString;
    private Context context;
    private static InternalStorageHandler instance;
    private String fileName = "tokenFile.txt";

    public InternalStorageHandler(Context context) {
        this.context = context;
    }

    public static synchronized InternalStorageHandler getInstance(Context context){
        if(instance == null){
            instance = new InternalStorageHandler(context);
        }
        return instance;
    }

    // to do: serialize passed in object to json string
    public String readFile(String filename) {
        try {
            FileInputStream fileInputStream = context.openFileInput(filename);
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

    public void writePodOrderableToFile(Orderable podOrderable) {
        Gson gson = new Gson();
        if (readFile("podOrderableMap") == "File Not Read") {
            HashMap<String, Object> podOrderableMap = new HashMap<String, Object>();
            podOrderableMap.put(podOrderable.id, podOrderable);
            String podOrderableMapString = gson.toJson(podOrderableMap);
            writeToFile(podOrderableMapString, "podOrderableMap");
        } else {
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
            String podOrderableMapString = readFile("podOrderableMap");
            HashMap<String, Object> podOrderableMap = gson.fromJson(podOrderableMapString, type);
            podOrderableMap.put(podOrderable.id, podOrderable);
            String newPodOrderableMapString = gson.toJson(podOrderableMap);
            writeToFile(newPodOrderableMapString, "podOrderableMap");
        }
    }

    public void writeOrderToFile(Order order) {
        Gson gson = new Gson();
        if (readFile("orderMap") == "File Not Read") {
            HashMap<String, Object> orderMap = new HashMap<String, Object>();
            orderMap.put(order.id, order);
            String orderMapString = gson.toJson(orderMap);
            writeToFile(orderMapString, "orderMap");
        } else {
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
            String orderMapString = readFile("orderMap");
            HashMap<String, Object> orderMap = gson.fromJson(orderMapString, type);
            orderMap.put(order.id, order);
            String newOrderMapString = gson.toJson(orderMap);
            writeToFile(newOrderMapString, "orderMap");
        }
    }

    public void writeShipmentLineItemToFile(LineItem lineItem) {
        Gson gson = new Gson();
        if (readFile("shipmentLineItemMap") == "File Not Read") {
            HashMap<String, Object> shipmentLineItemMap = new HashMap<String, Object>();
            shipmentLineItemMap.put(lineItem.id, lineItem);
            String shipmentLineItemMapString = gson.toJson(shipmentLineItemMap);
            writeToFile(shipmentLineItemMapString, "shipmentLineItemMap");
        } else {
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
            String shipmentLineItemMapString = readFile("shipmentLineItemMap");
            HashMap<String, Object> shipmentLineItemMap = gson.fromJson(shipmentLineItemMapString, type);
            shipmentLineItemMap.put(lineItem.id, lineItem);
            String newShipmentLineItemMapString = gson.toJson(shipmentLineItemMap);
            writeToFile(newShipmentLineItemMapString, "shipmentLineItemMap");
        }
    }

    public void writePodLineItemToFile(LineItem lineItem) {
        Gson gson = new Gson();
        if (readFile("podLineItemMap") == "File Not Read") {
            HashMap<String, Object> podLineItemMap = new HashMap<String, Object>();
            podLineItemMap.put(lineItem.id, lineItem);
            String podLineItemMapString = gson.toJson(podLineItemMap);
            writeToFile(podLineItemMapString, "podLineItemMap");
        } else {
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
            String podLineItemMapString = readFile("podLineItemMap");
            HashMap<String, Object> podLineItemMap = gson.fromJson(podLineItemMapString, type);
            podLineItemMap.put(lineItem.id, lineItem);
            String newPodLineItemMapString = gson.toJson(podLineItemMap);
            writeToFile(newPodLineItemMapString, "podLineItemMap");
        }
    }

    public void writeShipmentToFile(Shipment shipment) {
        Gson gson = new Gson();
        if (readFile("shipmentMap") == "File Not Read") {
            HashMap<String, Object> shipmentMap = new HashMap<String, Object>();
            shipmentMap.put(shipment.id, shipment);
            String shipmentMapString = gson.toJson(shipmentMap);
            writeToFile(shipmentMapString, "shipmentMap");
        } else {
            Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
            String shipmentMapString = readFile("shipmentMap");
            HashMap<String, Object> shipmentMap = gson.fromJson(shipmentMapString, type);
            shipmentMap.put(shipment.id, shipment);
            String newShipmentMapString = gson.toJson(shipmentMap);
            writeToFile(newShipmentMapString, "shipmentMap");
        }
    }
    public void writeToFile(String dataToBeStored, String filename) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
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
