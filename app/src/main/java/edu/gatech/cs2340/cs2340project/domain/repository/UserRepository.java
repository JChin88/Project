package edu.gatech.cs2340.cs2340project.domain.repository;


import java.util.HashMap;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public interface UserRepository {

    User getUser(String id);

    String getCurrentUID();

    String getMessage();

    void login(String email, String password);

    //void addUser(User user);

    List<User> getUsers();
}
