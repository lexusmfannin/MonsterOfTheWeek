package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.dao.VoteDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Character;
import com.techelevator.model.Party;
import com.techelevator.model.User;
import com.techelevator.model.Vote;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vote")
@PreAuthorize("isAuthenticated()")
public class VoteController {

    private VoteDao voteDao;
    private UserDao userDao;


    public VoteController(VoteDao voteDao, UserDao userDao) {
        this.voteDao = voteDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Vote submitVote(@RequestBody Vote vote, Principal principal){
        try {
            vote.setUserId(getCurrentUserId(principal));
            return voteDao.submitVote(vote);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/results", method = RequestMethod.GET)
    public String getVoteResults() {
        try {
            return voteDao.getVoteResults();
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getCurrentUserId(Principal principal){
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
