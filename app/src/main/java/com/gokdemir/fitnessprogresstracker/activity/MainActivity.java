package com.gokdemir.fitnessprogresstracker.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gokdemir.fitnessprogresstracker.R;
import com.gokdemir.fitnessprogresstracker.data.Exercise;
import com.gokdemir.fitnessprogresstracker.data.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView exerciseList;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Exercises");

        listViewPopulator();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonAddNewExercise);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addNewExercise.class));
            }
        });


        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent updateExerciseIntent = new Intent(MainActivity.this, updateExercise.class);
                updateExerciseIntent.putExtra("exerciseIndex", position);
                startActivity(updateExerciseIntent);
            }
        });

        exerciseList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete exercise");
                builder.setMessage("Are you sure you want to delete?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPrefManager sharedPrefManager = new SharedPrefManager();
                        SharedPreferences sharedPreferences= MainActivity.this.getSharedPreferences(sharedPrefManager.getPrefName(), Context.MODE_PRIVATE);
                        Gson gson = new Gson();
                        String response = sharedPreferences.getString(sharedPrefManager.getKey(), null);

                        ArrayList<Exercise> exercises = gson.fromJson(response, new TypeToken<ArrayList<Exercise>>(){}.getType());
                        exercises.remove(position);

                        SharedPreferences.Editor editor;

                        String json = gson.toJson(exercises);
                        editor = sharedPreferences.edit();
                        editor.putString(sharedPrefManager.getKey(), json);
                        editor.commit();

                        adapter.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void listViewPopulator(){
        exerciseList = (ListView) findViewById(R.id.exerciseList);
        SharedPrefManager manager = new SharedPrefManager();
        ArrayList<Exercise> exercises = manager.readSharedPref(this);
        adapter = new ListViewAdapter(this, exercises);
        exerciseList.setAdapter(adapter);
    }

}
