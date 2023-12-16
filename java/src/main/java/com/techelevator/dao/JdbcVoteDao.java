package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Party;
import com.techelevator.model.Vote;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class JdbcVoteDao implements VoteDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(JdbcVoteDao.class.getName());

    public JdbcVoteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Vote submitVote(Vote vote) {
        String sql = "INSERT INTO vote(user_id, party_id, monster_id, vote_date) " +
                     "VALUES (?, ?, ?, CURRENT_DATE) RETURNING vote_id;";
        try{
            int newVoteId = jdbcTemplate.queryForObject(
                    sql,
                    int.class,
                    vote.getUserId(),
                    vote.getPartyId(),
                    vote.getMonsterId()
                    );
            System.out.println(newVoteId);
            return getVoteByVoteId(newVoteId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error submitting vote", e);
        }
    }

    @Override
    public String getVoteResults() {
        //This is a method that returns the party name that appears the most frequently in the table
        String winner;

        String sql = "SELECT vote.party_id, party_name, COUNT(*) AS vote_count FROM vote " +
                "JOIN partygroup ON partygroup.party_id = vote.party_id " +
                "GROUP BY vote.party_id, partygroup.party_name " +
                "ORDER BY vote_count DESC LIMIT 1;";
        try {
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            winner = results.getString("party_name");
            return winner;
        }

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating character", e);
        }
        return null;
    }

    @Override
    public Vote getVoteByVoteId(int voteId) {
        String sql = "SELECT vote_id, user_id, party_id, monster_id, vote_date " +
                     "FROM vote WHERE vote_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, voteId);

        if (result.next()) {
            return mapRowToVote(result);
        }

        return null;
    }


    private Vote mapRowToVote(SqlRowSet rowSet) {
        Vote vote = new Vote();
        vote.setVoteId(rowSet.getInt("vote_id"));
        vote.setUserId(rowSet.getInt("user_id"));
        vote.setPartyId(rowSet.getInt("party_id"));
        vote.setMonsterId(rowSet.getInt("monster_id"));
        vote.setDate(rowSet.getDate("vote_date"));
        return vote;
    }
}
