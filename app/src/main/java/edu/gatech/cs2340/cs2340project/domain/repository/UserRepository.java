package edu.gatech.cs2340.cs2340project.domain.repository;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;

/**
 * @author Hoa V Luu
 */
public interface UserRepository {

    /**
     * Add user to fire store
     * @param userName user name
     * @param userEmail user email
     * @param userPassword user password
     * @param userRights user rights
     */
    void addUser(String userName, String userEmail, String userPassword, UserRights userRights);

    /**
     * get current user from firestore
     */
    void getCurrentUser();

    /**
     *
     * @param id id of user wanted to get
     */
    void getUser(String id);

    /**
     * login to firestore
     * @param email user email
     * @param password user password
     */
    void login(String email, String password);

    //void addUser(User user);

    /**
     * Get all users from firestore
     */
    void getUsers();

    /**
     * Set interactor / indicate next action
     * @param interactor next action
     */
    void setInteractor(Interactor interactor);
}
