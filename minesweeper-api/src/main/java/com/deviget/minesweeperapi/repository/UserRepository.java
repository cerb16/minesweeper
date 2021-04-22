package com.deviget.minesweeperapi.repository;

import com.deviget.minesweeperapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
