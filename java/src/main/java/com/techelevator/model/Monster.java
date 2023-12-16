package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Creature {

    private String url;
    private String index;
    private String size;
    private double challengeRating;
    public List<String> specialAbilities;


    public Monster(int id, String name, Byte image, String url, String index, String size, double challengeRating, List<String> specialAbilities) {
        super(id, name, image);
        this.url = url;
        this.index = index;
        this.size = size;
        this.challengeRating = challengeRating;
        this.specialAbilities = specialAbilities;
    }

    public Monster(){};

    public String getIndex() {
        return index;
    }

    public String getUrl() {
        return url;
    }

    public String getSize(){
        return size;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public List<String> getSpecialAbilities(){
        return specialAbilities;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public void setSpecialAbilities(List<String> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }
}
