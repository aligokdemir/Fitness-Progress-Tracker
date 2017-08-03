package com.gokdemir.fitnessprogresstracker.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by gokde on 13.07.2017.
 */

public class Exercise {
    private String name;
    private ArrayList<String> weights;
    private Date lastDone;

    public Exercise(String exerciseName, String weight){
        this.name = new String(exerciseName);
        this.weights = new ArrayList<String>();
        weights.add(weight);
        //date initialization will be added later on.
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

}
