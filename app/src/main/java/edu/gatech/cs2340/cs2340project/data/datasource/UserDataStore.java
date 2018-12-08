package edu.gatech.cs2340.cs2340project.data.datasource;

import io.reactivex.Observable;

public interface UserDataStore {

    /**
     * Get an {@link Observable} which will emit a user id by its email and password.
     *
     * @param email The email to retrieve user id.
     */
    Observable<String> userStringID(final String email, final String password);
}
