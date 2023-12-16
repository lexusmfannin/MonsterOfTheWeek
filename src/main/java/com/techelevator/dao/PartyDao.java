package com.techelevator.dao;

import com.techelevator.model.Party;

import java.util.List;

public interface PartyDao {

    Party createParty(Party party);

    List<Party> getAllParties();

    Party getPartyByPartyId(int partyId);

    Party deleteParty();

}
