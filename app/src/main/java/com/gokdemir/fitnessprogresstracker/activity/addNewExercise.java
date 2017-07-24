package com.gokdemir.fitnessprogresstracker.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gokdemir.fitnessprogresstracker.R;
import com.gokdemir.fitnessprogresstracker.data.Constants;
import com.gokdemir.fitnessprogresstracker.data.Exercise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class addNewExercise extends AppCompatActivity {
    Button buttonSaveExercise;
    Button buttonDisplayExercise;
    AutoCompleteTextView editTextExerciseName;
    EditText editTextCurrentWeight;

    ArrayList<Exercise> exerciseList = new ArrayList<>();
    final String key = "exercise";
    final String prefName = "exercises";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_exercise);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Constants.workouts);

        buttonSaveExercise = (Button) findViewById(R.id.buttonSaveExercise);
        buttonDisplayExercise = (Button) findViewById(R.id.buttonDisplayExercise);

        editTextExerciseName = (AutoCompleteTextView) findViewById(R.id.editTextExerciseName);
        editTextExerciseName.setAdapter(adapter);

        editTextCurrentWeight = (EditText) findViewById(R.id.editTextCurrentWeight);

        buttonSaveExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewExercise();
            }
        });

        buttonDisplayExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { displayData(); }
        });
    }

    private void addNewExercise(){

        Exercise exercise = new Exercise(editTextExerciseName.getText().toString(), editTextCurrentWeight.getText().toString());
        exerciseList.add(exercise);


        SharedPreferences sharedPreferences = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        Gson gson = new Gson();

        String prev = sharedPreferences.getString(key,null);
        if (prev != null){
            exerciseList.addAll((ArrayList<Exercise>)gson.fromJson(prev, new TypeToken<ArrayList<Exercise>>(){}.getType()));
        }

        String json = gson.toJson(exerciseList);
        editor = sharedPreferences.edit();
        editor.remove(key).commit();
        editor.putString(key, json);
        boolean isSuccessful = editor.commit();



        if(isSuccessful){
            Toast.makeText(this, "Successfully saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not saved. Please try again!", Toast.LENGTH_SHORT).show();
        }

        exerciseList.clear();
        startActivity(new Intent(addNewExercise.this, MainActivity.class));

    }

    public void displayData(){

        SharedPreferences sharedPreferences = getSharedPreferences(prefName, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String response = sharedPreferences.getString(key, "");
        ArrayList<Exercise> lstExerciseList = gson.fromJson(response, new TypeToken<List<Exercise>>(){}.getType());

        String size = new String();
        size = size.valueOf(lstExerciseList.size());
        Log.i("size", size);

    }
}