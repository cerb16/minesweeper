package com.deviget.minesweeperapi.repository;

import com.deviget.minesweeperapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
