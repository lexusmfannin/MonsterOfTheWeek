package com.techelevator.dao;

import com.techelevator.model.Monster;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcMonsterDao implements MonsterDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcMonsterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //We will need for archive for later
    @Override
    public List<Monster> getAllMonsters() {
        String sql = "SELECT monster_id, monster_name, challenge_rating, special_abilities, monster_size, image_id FROM monster";

        List<Monster> monsters = jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Monster monster = new Monster();
            monster.setIndex(resultSet.getString("index"));
            monster.setName(resultSet.getString("name"));
            monster.setUrl(resultSet.getString("url"));
            return monster;
        });
        return monsters;
    }
}

