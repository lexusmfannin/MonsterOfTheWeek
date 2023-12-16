package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.dao.PartyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Character;
import com.techelevator.model.Party;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/party")
@PreAuthorize("isAuthenticated()")
public class PartyController {

    private CharacterDao characterDao;
    private PartyDao partyDao;
    private UserDao userDao;

    public PartyController(CharacterDao characterDao, PartyDao partyDao, UserDao userDao) {
        this.characterDao = characterDao;
        this.partyDao = partyDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Party> getAllParties() {
        try {
            return partyDao.getAllParties();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/createParty", method = RequestMethod.POST)
    public Party createParty(@Valid @RequestBody Party party) {
        try {
            return partyDao.createParty(party);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @RequestMapping(path = "", method = RequestMethod.GET)
//    public Party getPartyById() {
//        try {
//             return partyDao.getPartyById().getPartyId();
//        } catch (DaoException e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    private int getCurrentPartyId(){
//        return partyDao.getPartyById().getPartyId();
//    }
}


