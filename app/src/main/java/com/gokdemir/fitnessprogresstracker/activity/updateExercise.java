package com.gokdemir.fitnessprogresstracker.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.gokdemir.fitnessprogresstracker.R;
import com.gokdemir.fitnessprogresstracker.data.Exercise;
import com.gokdemir.fitnessprogresstracker.data.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class updateExercise extends AppCompatActivity {
    int exercisePosition;

    EditText lastWeight;

    Button updateWeight;

    Gson gson;

    ArrayList<Exercise> exercises;

    SharedPreferences sharedPreferences;

    SharedPrefManager sharedPrefManager = new SharedPrefManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sharedPreferences = updateExercise.this.getSharedPreferences(sharedPrefManager.getPrefName(), Context.MODE_PRIVATE);

        gson = new Gson();
        String response = sharedPreferences.getString(sharedPrefManager.getKey(), null);

        exercises = gson.fromJson(response, new TypeToken<ArrayList<Exercise>>(){}.getType());


        lastWeight = (EditText) findViewById(R.id.editTextLastWeight);
        updateWeight = (Button) findViewById(R.id.buttonUpdateWeight);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                //do nothing
            } else{
                exercisePosition = extras.getInt("exerciseIndex");

            }
        } else{
            exercisePosition = (int) savedInstanceState.getSerializable("exerciseIndex");
        }

        //setting toolbar title acc. to exercise user clicked
        toolbar.setTitle(exercises.get(exercisePosition).getName());


        updateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercises.get(exercisePosition).addWeight(lastWeight.getText().toString());
                SharedPreferences.Editor editor;

                String json = gson.toJson(exercises);
                editor = sharedPreferences.edit();
                //editor.remove(sharedPrefManager.getKey());
                editor.putString(sharedPrefManager.getKey(), json);

                startActivity(new Intent(updateExercise.this, MainActivity.class));

            }
        });

    }

}
