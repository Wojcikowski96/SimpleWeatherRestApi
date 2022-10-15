package com.example.task.utils;

import com.google.gson.Gson;
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
        String location = json.getString("name");
        String main = json.getJSONArray("weather").getJSONObject(0).getString("main");
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        Double temperature = json.getJSONObject("main").getDouble("temp");
        Integer cloudsPercentage = json.getJSONObject("clouds").getInt("all");

        result.put("location", location);
        result.put("main", main);
        result.put("description", description);
        result.put("temperature", temperature);
        result.put("cloudsPercentage", cloudsPercentage);


        return result;


    }

}
