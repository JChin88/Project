package edu.gatech.cs2340.cs2340project.domain.repository;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import io.reactivex.Observable;

public interface UserRepository {

    Observable<String> addUser(String userName, String userEmail, String userPassword, UserRights userRights);

    String getCurrentUserID();

    Observable<User> getUser(String id);

    //void addUser(User user);

    Observable<List<User>> getUsers();

    /**
     * Login
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @param email The email used to sign in.
     * @param password The passwrod used to sign in.
     */
    Observable<String> login(final String email, final String password);

    User getCurrentUserTest();
}
