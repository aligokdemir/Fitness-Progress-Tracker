package com.gokdemir.fitnessprogresstracker.activity;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokdemir.fitnessprogresstracker.R;
import com.gokdemir.fitnessprogresstracker.data.Exercise;

import java.util.ArrayList;

/**
 * Created by gokde on 18.07.2017.
 */

public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater       inflater;
    private ArrayList<Exercise>  exerciseArrayList;


    public ListViewAdapter(Activity activity, ArrayList<Exercise> exercises){
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        exerciseArrayList = exercises;
    }

    @Override
    public int getCount() {
        if(exerciseArrayList == null || exerciseArrayList.isEmpty())
            return 0;
        else
            return exerciseArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = inflater.inflate(R.layout.item_exercise, null);

        TextView textViewExerciseName = (TextView) rowView.findViewById(R.id.textViewExerciseName);
        TextView textViewWeight = (TextView) rowView.findViewById(R.id.textViewWeight);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewExercise);

        Exercise exercise = exerciseArrayList.get(position);

        imageView.setImageResource(R.drawable.listviewimage);

        textViewExerciseName.setText(exercise.getName());
        textViewWeight.setText("Last weight you lift: "+exercise.getLastWeight());

        return rowView;
    }
}
