package com.techelevator.model;

import java.util.List;

public class CharacterDto {
    private byte[] portrait;
    private String background;
    private List<String> abilities;
    private String name;
    private String creature;
    private String profession;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;


    public byte[] getPortrait(){
        return portrait;
    }

    public String getBackground(){
        return background;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public String getName() {
        return name;
    }

    public String getCreature() {
        return creature;
    }

    public String getProfession() {
        return profession;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreature(String creature) {
        this.creature = creature;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
}
