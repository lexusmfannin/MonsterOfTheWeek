package com.techelevator.controller;

import com.techelevator.services.MonsterService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/monsters")
@CrossOrigin
public class MonsterController {
    private MonsterService monsterService;

    public MonsterController(MonsterService monsterService) { /** Injects Monster Service into the Monster Controller #DependancyInjection **/
        this.monsterService = monsterService;
    }
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllMonsters() { /** Mapping "/all" endpoint handling #CRUD OPS : calls the fetchMonster method from monster Service to update the list of monsters **/
        monsterService.fetchMonsters();
        return monsterService.getAllMonsters();
    }
    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public Map<String, Object> randomMonster() { /** Mapping "/random" endpoint handling #CRUD OPS : calls the fetchMonster method from monster Service : Retrieves the index of the random monster **/
        monsterService.fetchMonsters();
        Map<String, Object> randomMonster = monsterService.getRandomMonsterFromList();

        if (randomMonster != null) {
            String index = (String) randomMonster.get("index");
            Map<String, Object> monsterDetails = monsterService.fetchMonsterDetails(index);

            if (monsterDetails != null) {
                randomMonster.put("details", monsterDetails);
            }
        }
        return randomMonster;
    }
    @GetMapping("/{index}")
    public Map<String, Object> getMonsterDetails(@PathVariable(name = "index") String index) {
        return monsterService.fetchMonsterDetails(index);
    }
}


