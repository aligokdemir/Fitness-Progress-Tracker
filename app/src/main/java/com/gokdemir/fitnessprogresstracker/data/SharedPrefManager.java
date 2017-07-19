package com.gokdemir.fitnessprogresstracker.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokde on 17.07.2017.
 */

public  class SharedPrefManager {
    private final String prefName = "exercises";
    private final String key = "exercise";

    public SharedPrefManager(){}

    public ArrayList<Exercise> readSharedPref(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String response = sharedPreferences.getString(key, null);

        ArrayList<Exercise> lstExerciseList = gson.fromJson(response, new TypeToken<ArrayList<Exercise>>(){}.getType());

        return lstExerciseList;
    }
}
