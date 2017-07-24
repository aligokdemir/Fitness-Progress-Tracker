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

        // if-else to determine the ImageView in the list view depending on the exercise type
        // so that the user sees which exercise make which specific areas work.

        if(exercise.getName().equals("Squat")
        || exercise.getName().equals("Leg press")
        || exercise.getName().equals("Lunge")
        || exercise.getName().equals("Leg extension")
        || exercise.getName().equals("Leg curl")
        || exercise.getName().equals("Standing calf raise")
        || exercise.getName().equals("Seated calf raise")
        || exercise.getName().equals("Hip abductor")
        || exercise.getName().equals("Front Squat" )){
            imageView.setImageResource(R.drawable.legicon);
        } else if(exercise.getName().equals("Deadlift")
               || exercise.getName().equals("Pull-down")
               || exercise.getName().equals("Pull-up")
               || exercise.getName().equals("Bent-over row")
               || exercise.getName().equals("Back extension")
               || exercise.getName().equals("Dumbbell Row")
               || exercise.getName().equals("Barbell Row")
               || exercise.getName().equals("Seated Row")){
            imageView.setImageResource(R.drawable.backicon);
        } else if(exercise.getName().equals("Bench press")
               || exercise.getName().equals("Chest fly")
               || exercise.getName().equals("Push-up")
               || exercise.getName().equals("Dumbbell Fly")
               || exercise.getName().equals("Dumbbell Press")
               || exercise.getName().equals("Incline Bench Press")
               || exercise.getName().equals("Incline Dumbbell Press")
               || exercise.getName().equals("Incline Dumbbell Fly")){
            imageView.setImageResource(R.drawable.chesticon);
        } else if(exercise.getName().equals("Upright row")
               || exercise.getName().equals("Shoulder fly")
               || exercise.getName().equals("Shoulder Press")
               || exercise.getName().equals("Overhead Press")
               || exercise.getName().equals("Lateral Raise")
               || exercise.getName().equals("Shoulder Shrug")){
            imageView.setImageResource(R.drawable.shouldericon);
        } else if(exercise.getName().equals("Push down")
               || exercise.getName().equals("Triceps extension")
               || exercise.getName().equals("Dips")
               || exercise.getName().equals("Skull Crusher")
               || exercise.getName().equals("Close-Grip Bench Press")
               || exercise.getName().equals("Overhead Dumbbell Extension")
               || exercise.getName().equals("Cable Push Down")){
            imageView.setImageResource(R.drawable.tricepsicon);
        } else if(exercise.getName().equals("Biceps curl")
               || exercise.getName().equals("Dumbbell Curl")
               || exercise.getName().equals("Standing Barbell Curl")
               || exercise.getName().equals("Cable Curl")){
            imageView.setImageResource(R.drawable.bicepsicon);
        } else if(exercise.getName().equals("Abdominal Crunch")
               || exercise.getName().equals("Sit-Up")
               || exercise.getName().equals("Leg Raise")
               || exercise.getName().equals("Plank")
               || exercise.getName().equals("Russian twist")){
            imageView.setImageResource(R.drawable.abdominalicon);
        }else //default image of view.
            imageView.setImageResource(R.drawable.listviewimage);


        textViewExerciseName.setText(exercise.getName());
        textViewWeight.setText("Last weight you lift: "+exercise.getLastWeight());

        return rowView;
    }
}
