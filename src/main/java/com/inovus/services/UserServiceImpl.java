package com.inovus.services;

import com.inovus.models.User;
import com.inovus.repositories.UserRepository;
import com.inovus.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public void saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public boolean isCorrectUser(String username, String password) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public void updateLastVisit(String username, String newVisitIp) {
        User user = userRepository.findByUsernameIgnoreCase(username);

        user.setLastVisitTime(user.getNewVisitTime());
        user.setLastVisitIp(user.getNewVisitIp());

        user.setNewVisitTime(new Timestamp((new Date()).getTime()));
        user.setNewVisitIp(newVisitIp);
        userRepository.save(user);
    }
}
