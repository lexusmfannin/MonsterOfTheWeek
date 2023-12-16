package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Vote {
    private int voteId;
    private int userId;
    private int partyId;
    private int monsterId;
    private Date date;

    public Vote(int userId, int partyId, Date date, int voteId, int monsterId){
        this.userId = userId;
        this.partyId = partyId;
        this.date = date;
        this.voteId = voteId;
        this.monsterId = monsterId;
    }

    public Vote(){};

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPartyId() {
        return partyId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getVoteId() {
       return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }
}
