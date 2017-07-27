package com.gokdemir.fitnessprogresstracker.activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gokdemir.fitnessprogresstracker.R;
import com.gokdemir.fitnessprogresstracker.data.Exercise;
import com.gokdemir.fitnessprogresstracker.data.SharedPrefManager;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class graphActivity extends AppCompatActivity {

    GraphView graph;

    TextView textViewGraphExp;

    SharedPrefManager sharedPrefManager = new SharedPrefManager();

    int exercisePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getting the exercise position from caller activity and proceeding accordingly...
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                //do nothing
            } else{
                exercisePosition = extras.getInt("position");

            }
        } else{
            exercisePosition = (int) savedInstanceState.getSerializable("position");
        }

        graph = (GraphView) findViewById(R.id.graph);
        textViewGraphExp = (TextView) findViewById(R.id.textViewGraphExp);


        ArrayList<Exercise> exercises= sharedPrefManager.readSharedPref(this);

        //getting the exercise from exercises array list so that the specific graph for the exercise can be shown.
        Exercise exercise = exercises.get(exercisePosition);
        textViewGraphExp.setText("The graph for " + exercise.getName() + "is below.");

        DataPoint[] dataPoints = new DataPoint[30];

        for(int x = 0; x <= exercise.getWeights().size(); x++){
            dataPoints[x] = new DataPoint(x, Integer.valueOf(exercise.getWeights().get(x)));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);

        graph.addSeries(series);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
