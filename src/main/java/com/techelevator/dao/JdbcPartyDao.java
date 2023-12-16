package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Party;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class JdbcPartyDao implements PartyDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPartyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Party createParty(Party party) {
        try {
            String partyGroupSql = "INSERT INTO partygroup(party_name) VALUES (?) RETURNING party_id;";
            // jdbcTemplate.queryForObject(partyGroupSql, party.getPartyName());

            String selectPartyIdSql = "SELECT party_id FROM partygroup WHERE party_name = ?;";
            int newPartyId = jdbcTemplate.queryForObject(partyGroupSql, int.class, party.getPartyName());
            System.out.println("Party Group SQL: " + partyGroupSql);
            System.out.println("New Party ID: " + newPartyId);

            String partySql = "INSERT INTO party(party_id, character_id) VALUES (?, ?)";
            for (int characterId : party.getCharacterId()) {
                System.out.println("Character ID: " + characterId);
                jdbcTemplate.update(partySql, newPartyId, characterId);
                System.out.println("Number of Characters: " + party.getCharacterId().size());
            }

            return getPartyByPartyId(newPartyId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating party", e);
        }
    }

    @Override
    public Party getPartyByPartyId (int partyId) {
        String sql = "SELECT party_name FROM partygroup WHERE party_id=?;";

        return null;
        }

    public List<Party> getAllParties() {
        try {
            List<Party> parties = new ArrayList<>();
            String sql = "SELECT party_id, party_name " +
                    "FROM partygroup;";

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Party party = mapRowToParty(results);
                parties.add(party);
            }
            return parties;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    @Override
    public Party deleteParty() {
        return null;
    }

    //in characterDao, add a method of getAllCharactersByPartyId()

    private Party mapRowToParty(SqlRowSet rowSet) {
        int partyId = rowSet.getInt("party_id");
        String partyName = rowSet.getString("party_name");
        return new Party(partyId, partyName, new ArrayList<Integer>());
    }

    }
