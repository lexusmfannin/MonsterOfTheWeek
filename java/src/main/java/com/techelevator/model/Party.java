package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private int partyId;
    private List<Integer> characterId = new ArrayList<>();
    private String partyName;

    public Party() {
    }

    public Party(int partyId, List<Integer> characterId, String partyName) {
        this.partyId = partyId;
        this.characterId = characterId;
        this.partyName = partyName;
    }

    public Party(List<Integer> characterId, String partyName){
        this.characterId = characterId;
        this.partyName = partyName;
    }

    public Party(int partyId, String partyName, ArrayList<Integer> integers) {

    }

    public int getPartyId() {
        return partyId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }

    public List<Integer> getCharacterId() {
        return characterId;
    }

    public void setCharacterId(List<Integer> characterId) {
        this.characterId = characterId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }
}

