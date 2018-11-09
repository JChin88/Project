package edu.gatech.cs2340.cs2340project.domain.repository;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;

public interface UserRepository {

    void addUser(String userName, String userEmail, String userPassword, UserRights userRights);

    void getCurrentUser();

    void getUser(String id);

    void login(String email, String password);

    //void addUser(User user);

    void getUsers();

    void setInteractor(Interactor interactor);
}
