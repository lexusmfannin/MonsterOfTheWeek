package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Character;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/characters")
@PreAuthorize("isAuthenticated()")
public class CharacterController {

    private final CharacterDao characterDao;
    private UserDao userDao;

    public CharacterController(CharacterDao characterDao, UserDao userDao) {
        this.characterDao = characterDao;
        this.userDao = userDao;

    }
    @RequestMapping(path = "/{characterId}", method = RequestMethod.GET)
    public Character getCharactersId(@PathVariable int characterId ) {
        try {
            return characterDao.getCharacterById(characterId);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Character> getCharactersByUserId(Principal principal) {
        try {
            return characterDao.getCharactersByUserId(getCurrentUserId(principal));
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Character createCharacter(@Valid @RequestBody Character character, Principal principal) {
        try {
            return characterDao.createCharacter(character, getCurrentUserId(principal));
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "/{characterId}", method = RequestMethod.PUT)
    public Character update(@PathVariable int characterId, @Valid @RequestBody Character character) {
        try {
            return characterDao.updateCharacter(characterId, character);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{characterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter( @PathVariable int characterId) {
        try {
            characterDao.deleteCharacterById(characterId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting character", e);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Character>> getAllCharacters() {
        try {
            List<Character> characters = characterDao.getAllCharacters();
            return ResponseEntity.ok(characters);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting all characters", e);
        }
    }



    private int getCurrentUserId(Principal principal){
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}