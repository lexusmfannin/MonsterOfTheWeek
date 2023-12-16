package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Character;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class JdbcCharacterDao implements CharacterDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(JdbcCharacterDao.class.getName());

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Character getCharacterById(int characterId) {
        String sql = "SELECT character_id, name, creature, class_profession, background, abilities, created_date, character_strength, character_dexterity, character_constitution, character_intelligence, character_wisdom, character_charisma, user_id, image_id " +
                     "FROM character WHERE character_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, characterId);

        if (result.next()) {
            return mapRowToCharacter(result);
        }

        return null;
    }

    @Override
    public Character createCharacter(Character character, int userId) {
        try {
            String characterSql = "INSERT INTO character (name, background, creature, class_profession, " +
                    "character_strength, character_dexterity, character_constitution, character_intelligence, " +
                    "character_wisdom, character_charisma, abilities, user_id, created_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE) RETURNING character_id";

            int newCharacterId = jdbcTemplate.queryForObject(
                    characterSql,
                    int.class,
                    character.getCharacter_name(),
                    character.getBackground(),
                    character.getCreature(),
                    character.getClass_profession(),
                    character.getCharacter_strength(),
                    character.getCharacter_dexterity(),
                    character.getCharacter_constitution(),
                    character.getCharacter_intelligence(),
                    character.getCharacter_wisdom(),
                    character.getCharacter_charisma(),
                    character.getAbilities(),
                    userId
            );
            return getCharacterById(newCharacterId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating character", e);
        }
    }

    @Override
    public Character updateCharacter(int characterId, Character updatedCharacter) {
        try {
            String sql = "UPDATE character SET name = ?, creature=?, class_profession=?, background=?, abilities=?, character_strength=?, " +
                         "character_dexterity=?, character_constitution=?, character_intelligence=?, character_wisdom=?, character_charisma=? WHERE character_id=?";

            int rowsUpdated = jdbcTemplate.update(
                    sql,
                    updatedCharacter.getCharacter_name(),
                    updatedCharacter.getCreature(),
                    updatedCharacter.getClass_profession(),
                    updatedCharacter.getBackground(),
                    updatedCharacter.getAbilities(),
                    updatedCharacter.getCharacter_strength(),
                    updatedCharacter.getCharacter_dexterity(),
                    updatedCharacter.getCharacter_constitution(),
                    updatedCharacter.getCharacter_intelligence(),
                    updatedCharacter.getCharacter_wisdom(),
                    updatedCharacter.getCharacter_charisma(),
                    characterId
            );

            if (rowsUpdated > 0) {
                return updatedCharacter;
            } else {
                throw new RuntimeException("Character not found or not updated");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating character", e);
        }
    }


    @Override
    public int deleteCharacterById(int id) {
        String sql = "DELETE FROM character WHERE character_id = ?;";
        jdbcTemplate.update(sql, id);
        return id;
    }
    @Override
    public List<Character> getCharactersByUserId(int userId) {
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT  character_id,name, creature, class_profession, background, abilities, character_strength, " +
                     "character_dexterity, character_constitution, character_intelligence, character_wisdom, character_charisma, user_id FROM character WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Character character = mapRowToCharacter(results);
                characters.add(character);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return characters;
    }

    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>();
        String sql = "SELECT character_id, name, creature, class_profession, background, abilities, character_strength, " +
                "character_dexterity, character_constitution, character_intelligence, character_wisdom, character_charisma, user_id FROM character;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Character character = mapRowToCharacter(results);
                characters.add(character);
            }
            System.out.println(characters);
            return characters;
        } catch (CannotGetJdbcConnectionException e) {
            System.err.println("Error getting JDBC connection");
            e.printStackTrace();
            throw new DaoException("Unable to connect to the database", e);
        } catch (Exception e) {
            System.err.println("Unexpected error while fetching characters");
            e.printStackTrace();
            throw new DaoException("Unexpected error while fetching characters", e);
        }
    }


    private Character mapRowToCharacter(SqlRowSet rowSet) {
        Character character = new Character();
        character.setId(rowSet.getInt("character_id"));
        character.setName(rowSet.getString("name"));
        character.setBackground(rowSet.getString("background"));
        character.setCreature(rowSet.getString("creature"));
        character.setClass_profession(rowSet.getString("class_profession"));
        character.setCharacter_strength(rowSet.getInt("character_strength"));
        character.setCharacter_dexterity(rowSet.getInt("character_dexterity"));
        character.setCharacter_constitution(rowSet.getInt("character_constitution"));
        character.setCharacter_intelligence(rowSet.getInt("character_intelligence"));
        character.setCharacter_wisdom(rowSet.getInt("character_wisdom"));
        character.setCharacter_charisma(rowSet.getInt("character_charisma"));
        character.setAbilities(rowSet.getString("abilities"));
        character.setUser_id(rowSet.getInt("user_id"));
        return character;
    }
}