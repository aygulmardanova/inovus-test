package com.inovus.services.interfaces;

import com.inovus.models.User;

public interface UserService {

    User getUserByUsername(String username);

    void saveUser(String username, String password);

    boolean isCorrectUser(String username, String password);

    void updateLastVisit(String username, String newVisitIp);

}
