package com.techelevator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class MonsterService {
    private final String MONSTER_API_BASE_URL = "https://www.dnd5eapi.co/api/monsters";

    private RestTemplate restTemplate = new RestTemplate(); /** Allows for HTTP request and receive HTTP responses **/
    private List<Map<String, Object>> allMonsters = new ArrayList<>(); /** declares 'allmonsters' a list which holds maps. Each map represents a monster with attributes **/

    public void fetchMonsters() {
        try {
            Map<String, Object> monstersMap = restTemplate.getForObject(MONSTER_API_BASE_URL, Map.class);/** Sends the GET request to the MONSTER_API_BASE_URL **/

            if (monstersMap != null) {
                List<Map<String, Object>> monstersList = (List<Map<String, Object>>) monstersMap.get("results");/** Takes the results out the HTTP response **/

                if (monstersList != null) {
                    allMonsters.addAll(monstersList);/** Adds all monsters from the "monsterlist" to the new "allMonsters" list**/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching monsters: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> getAllMonsters() {/** Returns a new array list of the "allmonsters" list**/
        return new ArrayList<>(allMonsters);
    }

    public Map<String, Object> getRandomMonsterFromList() {/** Goes to get a monster from "allmonsters" at random **/
        if (allMonsters.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return allMonsters.get(random.nextInt(allMonsters.size()));
    }

    public Map<String, Object> fetchMonsterDetails(String index) {/** This sends a GET request to get a single monsters details using the index of the monsters in the list **/
        String monsterDetailsUrl = MONSTER_API_BASE_URL + "/" + index;
        try {
            return restTemplate.getForObject(monsterDetailsUrl, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching monster details: " + e.getMessage());
            return null;
        }
    }
}
