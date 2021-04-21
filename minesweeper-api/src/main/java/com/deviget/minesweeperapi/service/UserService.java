package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.User;

import java.util.List;

public interface UserService {
    Long createUser(User user);

    Long updateUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getByUserName(String userName);

    String deleteUser(Long userId);
}
