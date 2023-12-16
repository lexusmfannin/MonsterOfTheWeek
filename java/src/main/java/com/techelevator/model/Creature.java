package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Creature {
    private int id;
    @JsonProperty("character_name")
    private String name;
    private int image_id;
    public Creature(int id, String name, int image_id) {
        this.id = id;
        this.name = name;
        this.image_id = image_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Creature() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter_name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImage() {
        return null;
    }
}

