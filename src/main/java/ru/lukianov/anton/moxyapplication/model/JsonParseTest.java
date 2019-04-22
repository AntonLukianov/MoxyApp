package ru.lukianov.anton.moxyapplication.model;

import android.graphics.Bitmap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class JsonParseTest {

    public JsonParseTest(JSONObject jsonObject) {




    }

    public static JSONArray JsonParseView(JSONObject jsonObject) {
        JSONArray view;
        view = (JSONArray) jsonObject.get("view");


        return view;
    }

    public static JSONArray JsonParseData(JSONObject jsonObject){
        JSONArray data;
        data = (JSONArray) jsonObject.get("data");


        return data;
    }

    public static Bitmap getJsonPicture (JSONArray jsonArray){

        Iterator i = jsonArray.iterator();
        Bitmap picture = null;
        while (i.hasNext()){
            JSONObject innerObj = (JSONObject) i.next();
            if (innerObj.get("name").equals("picture")){

                JSONObject dataObject = (JSONObject) innerObj.get("data");
                String text = (String) dataObject.get("text");
                String url = (String) dataObject.get("url");
                DownloadImageTask downloadImageTask = new DownloadImageTask();

                try {
                    picture = downloadImageTask.execute(url).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }
        return picture;
    }

    public static String getJsonText (JSONArray jsonArray){

        Iterator i = jsonArray.iterator();
        String text = "";
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            if (innerObj.get("name").equals("hz")){

                JSONObject dataObject = (JSONObject) innerObj.get("data");
                text = (String) dataObject.get("text");


            }

        }
        return text;
    }

    public static List<String> getJsonSelector (JSONArray jsonArray){
        Iterator i = jsonArray.iterator();
        List<String> list = new LinkedList<>();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();

            if (innerObj.get("name").equals("selector")){

                JSONObject dataObject = (JSONObject) innerObj.get("data");
                Integer text = new Integer(String.valueOf(dataObject.get("selectedId")));
                JSONArray variant = (JSONArray) dataObject.get("variants");
                Iterator v = variant.iterator();


               while (v.hasNext()){
                   JSONObject variantObj = (JSONObject) v.next();
                    Long vId = (Long) variantObj.get("id");
                    String vText = (String) variantObj.get("text");
                    list.add(vText);
                }

            }

        }

        return list;
    }



}
