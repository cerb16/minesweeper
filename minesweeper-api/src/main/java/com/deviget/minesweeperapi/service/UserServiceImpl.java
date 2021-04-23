package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.User;
import com.deviget.minesweeperapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public Long createUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public String deleteUser(Long userId) {
        if(getUserById(userId) != null){
            userRepository.deleteById(userId);
            return "User deleted successfully";
        }else{
            return "User does not exist";
        }

    }
}
