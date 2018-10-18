package edu.gatech.cs2340.cs2340project.domain.repository;


import java.util.HashMap;

import edu.gatech.cs2340.cs2340project.domain.model.User;

public interface UserRepository {

    User getUser(String id);

}
