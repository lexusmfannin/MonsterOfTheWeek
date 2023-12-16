package com.techelevator.model;

import java.util.List;

public class MonsterDto {
    private String name;
    private String size;
    private int challengeRating;
    private List<String> specialAbilities;

    public MonsterDto(String name, String size, int challengeRating, List<String> specialAbilities) {
        this.name = name;
        this.size = size;
        this.challengeRating = challengeRating;
        this.specialAbilities = specialAbilities;

    }
    public MonsterDto(){};
    public String getName() {
        return name;
    }
    public String getSize() {
        return size;
    }
    public int getChallengeRating() {
        return challengeRating;
    }
    public List<String> getSpecialAbilities() {
        return specialAbilities;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setChallengeRating(int challengeRating) {
        this.challengeRating = challengeRating;
    }
    public void setSpecialAbilities(List<String> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }
}
