package com.example.task.utils;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.*;

public class JsonUtils {
    public static JSONObject objectToJSONObject(Object response) throws ParseException {
        String jsonString = new Gson().toJson(response, LinkedHashMap.class);
        return new JSONObject(jsonString);
    }
    public static Map<Object, Object> getSiplifiedJson(JSONObject json){
        Map<Object, Object> result = new HashMap<>();
        //Pobieram główny opis i drugi opis
//        String location = json.getString("name");
//        String main = json.getJSONArray("weather").getJSONObject(0).getString("main");
//        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
//        Double temperature = json.getJSONObject("main").getDouble("temp");
//        Integer cloudsPercentage = json.getJSONObject("clouds").getInt("all");

        String location = json.getString("timezone");
        String main = json.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("main");
        String description = json.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description");
        Double temperature = json.getJSONObject("current").getDouble("temp");
        Integer cloudsPercentage = json.getJSONObject("current").getInt("clouds");

        JSONArray weathers = json.getJSONArray("hourly");

        calculatePrecipation(result, weathers);

        result.put("location", location);
        result.put("main", main);
        result.put("description", description);
        result.put("temperature", temperature);
        result.put("cloudsPercentage", cloudsPercentage);


        return result;


    }

    private static void calculatePrecipation(Map<Object, Object> result, JSONArray weathers) {
        for(int i = 0; i < 3; i++){
            if(weathers.getJSONObject(i).isNull("rain")){
                result.put("rainInNext3h", 0);

            }else{
                result.put("rainInNext3h", 1);
                break;
            }
        }
        for(int i = 0 ; i < 3; i++){
            if(weathers.getJSONObject(i).isNull("snow")){
                result.put("snowInNext3h", 0);
            }else{
                result.put("snowInNext3h", 1);
                break;
            }
        }
    }

}
