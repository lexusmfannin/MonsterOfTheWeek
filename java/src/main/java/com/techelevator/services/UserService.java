package com.techelevator.services;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getUserIdByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            return user.getId();
        } else {
            // Handle the case where the user is not found, for example, throw an exception
            throw new RuntimeException("User not found for username: " + username);
        }
    }
}
