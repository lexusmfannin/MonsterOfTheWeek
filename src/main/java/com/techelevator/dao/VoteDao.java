package com.techelevator.dao;

import com.techelevator.model.CharacterDto;
import com.techelevator.model.Character;
import com.techelevator.model.Vote;


import java.util.List;

public interface VoteDao {

    Vote submitVote(Vote vote);

    String getVoteResults();

    Vote getVoteByVoteId(int voteId);

}
