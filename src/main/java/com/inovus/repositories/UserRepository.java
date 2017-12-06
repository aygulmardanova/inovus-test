package com.inovus.repositories;

import com.inovus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findByUsernameIgnoreCase(String username);

}
