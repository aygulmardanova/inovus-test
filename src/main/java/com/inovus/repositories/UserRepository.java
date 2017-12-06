package com.inovus.repositories;

import com.inovus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aygulmardanova on 01.12.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findByUsernameIgnoreCase(String username);

    User findByUsernameAndPassword(String username, String password);

}
