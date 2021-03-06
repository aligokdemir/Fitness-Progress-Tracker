package com.gokdemir.fitnessprogresstracker.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by gokde on 13.07.2017.
 */

public class Exercise {
    private String name;
    private ArrayList<String> weights;
    private String lastDone; //this variable will hold the date the exercise is last done.

    public Exercise(String exerciseName, String weight){
        this.name = new String(exerciseName);
        this.weights = new ArrayList<>();
        weights.add(weight);
        this.lastDone = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public void setName(String name){
        this.name = new String(name);
    }

    public String getName(){ return this.name; }

    public ArrayList<String> getWeights(){
        return this.weights;
    }

    public void addWeight(String weight){
        this.weights.add(weight);
    }

    public void modifyWeight(String newValue, int position){
        this.weights.set(position, newValue);
    }

    public String getLastWeight(){ return weights.get(weights.size() -  1); }

    public void deleteWeight(){ //deletes the last weight that has been lifted.
        this.weights.remove(weights.size() - 1);
    }

    public void setLastDone(){ this.lastDone = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); }

    public String getLastDone(){ return lastDone; }
}
